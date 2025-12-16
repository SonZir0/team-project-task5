import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GetDataFromFile implements GetData, Testable {
    private String filePath;

    GetDataFromFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Optional<Bus> getOneObject() {
        Bus bus = null;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            bus = readBus(br);
        } catch (IOException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(bus);
    }

    @Override
    public Optional<BusList> getNObjects(int N) {
        BusList park = null;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            park = Stream.generate(() -> readBus(br))
                    .takeWhile(bus -> bus != null) // Остановить поток, когда bus равен null
                    .limit(N) // Ограничиваем количество записей до n
                    .collect(Collectors.toCollection(BusList::new));

        }catch(IOException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(park);
    }


    private static Bus readBus(BufferedReader br){
        try {
            String line = br.readLine();
            if (line == null) return null; // Если  конец файла

            String[] parts = line.split(",");
            if (!isValidFormat(parts)) {
                System.out.println("Неверный формат строки: " + line);
                return null; // Пропуск строки с неверным форматом
            }

            String number = parts[0].trim();
            String model = parts[1].trim();
            int mileage = parseMileage(parts[2].trim());
            if (mileage<0) {
                System.out.println("Некорректное значение пробега: " + parts[2]);
                return null; // Пропуск строки с неверным форматом
            }
            return new Bus.Builder()
                    .setModel(model)
                    .setNumber(number)
                    .setMileage(mileage)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Валидация данных
    private static int parseMileage(String mileageStr) {
        try {
            return Integer.parseInt(mileageStr);
        } catch (NumberFormatException e) {
            return -1; // Возвращаем -1 для некорректого пробега
        }
    }

    private static boolean isValidFormat(String[] parts) {
        return parts.length == 3; // Проверка на количество элементов в строке (модель, номер, пробег = 3)
    }

    @Override
    public void runAllTests() {
        System.out.println("\nЗапускаем тесты в классе GetDataFromFile:");
        System.out.printf(
                Messages.TEST_RESULT_FORMAT_STRING.getMessage(),
                "Тест 1-го загруженного объекта:",
                GetDataFromFile.Tests.getOneObject());
        System.out.printf(
                Messages.TEST_RESULT_FORMAT_STRING.getMessage(),
                "Тест получения листа из N элементов:",
                GetDataFromFile.Tests.getNObjects());
    }

    static class Tests {
        static boolean getOneObject() {
            Bus temp = new GetDataFromFile("resources//correctFile.txt").getOneObject().get();
            Bus temp2 = new GetDataFromFile("resources//emptyFile.txt").getOneObject().orElse(null);
            return temp.getNumber().equals("124К-5678A") &&
                    temp.getModel().equals("RF-12345678") &&
                    temp.getMileage() == 1000 &&
                    temp2 == null;
        }

        static boolean getNObjects() {
            GetDataFromFile dataFromFile = new GetDataFromFile("resources//correctFile.txt");
            BusList temp = dataFromFile.getNObjects(5).get();
            BusList temp2 = dataFromFile.getNObjects(0).get();
            BusList temp3 = dataFromFile.getNObjects(101).get();
            BusList temp4 = new GetDataFromFile("resources//emptyFile.txt").getNObjects(10).get();
            return temp.size() == 5 &&
                    temp2.isEmpty() &&
                    temp3.size() == 20 &&
                    temp4.isEmpty();
        }
    }
}

