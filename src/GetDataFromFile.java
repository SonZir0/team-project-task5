import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GetDataFromFile implements GetData {
    private String filePath = "resources\\park.txt";

    @Override
    public Bus getOneObject() {
        Bus bus=null;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            bus = readBus(br);
            System.out.println(bus);

        } catch (IOException e){
            e.printStackTrace();
        }
        return bus;
    }

    @Override
    public List<Bus> getNObjects(int N) {
        List<Bus> park=null;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            park = Stream.generate(() -> readBus(br))
                    .takeWhile(bus -> bus != null) // Остановить поток, когда bus равен null
                    .limit(N) // Ограничиваем количество записей до n
                    .collect(Collectors.toList());

            // Выводим результат
            park.forEach(System.out::println);
        }catch(IOException e){
            e.printStackTrace();
        }
        return park;
    }


    private static Bus readBus(BufferedReader br){
        try {
            String line = br.readLine();
            if (line == null) return null; // Если  конец файла

            String[] parts = line.split(" ");
            if (!isValidFormat(parts)) {
                System.err.println("Неверный формат строки: " + line);
                return null; // Пропуск строки с неверным форматом
            }

            String model = parts[0].trim();
            String number = parts[1].trim();
            int mileage = parseMileage(parts[2].trim());
            if (mileage<0) {
                System.err.println("Некорректное значение пробега: " + parts[2]);
                return null; // Пропуск строки с неверным форматом
            }
            Bus bus=new Bus.Builder()
                    .setModel(model)
                    .setNumber(number)
                    .setMileage(mileage)
                    .build();
            return  bus;
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

}

