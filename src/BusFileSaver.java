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
}









