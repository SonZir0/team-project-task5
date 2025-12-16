import java.io.*;
public class BusFileSaver {
    /**
     * Сохраняет коллекцию автобусов в файл в режиме добавления
     * @param buses список автобусов
     * @param toAppend true - добавлять к файлу, false - перезаписывать
     * @return true, если сохранение успешно
     */
    public static boolean appendToFile(BusList buses, boolean toAppend) {
       return appendToFile(buses, "resources//savedCollection.txt", toAppend);
    }

    private static boolean appendToFile(BusList buses, String pathToFile, boolean toAppend) {
        try (BufferedWriter writer = new BufferedWriter((new FileWriter(pathToFile, toAppend)))) { // true = режим добавления
            for (Bus bus : buses) {
                writer.write(String.format("%-15s,%-20s,%d",
                        bus.getNumber(), bus.getModel(), bus.getMileage()));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            return false;
        }
    }

    public static void runAllTests() {
        System.out.println("\nЗапускаем тесты в классе BusFileSaver:");
        System.out.printf(
                Messages.TEST_RESULT_FORMAT_STRING.getMessage(),
                "Тест данных в сохраненном файле:",
                BusFileSaver.Tests.testSavedData());
    }

    static class Tests {
        static boolean testSavedData() {
            String pathToTestFile = "resources//testBusFileSaver.txt";
            Bus bus1 = new Bus.Builder().setNumber("12K")
                    .setModel("Something").setMileage(0).build();
            Bus bus2 = new Bus.Builder().setNumber("25A")
                    .setModel("Whatever").setMileage(9999).build();
            BusList tempList = new BusList();

            tempList.add(bus1);
            tempList.add(bus2);
            appendToFile(tempList, pathToTestFile, false);
            BusList loadedList = new GetDataFromFile(pathToTestFile)
                    .getNObjects(2).get();

            for (int i = 0; i < tempList.size(); i++)
                if (tempList.get(i).compareTo(loadedList.get(i)) != 0)
                    return false;
            return true;
        }
    }
}









