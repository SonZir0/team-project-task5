import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BusFileSaver {
    public static void saveToFile(List<Bus> buses, String filename) throws IOException {
        // Проверка входных данных
        if (buses == null) {
            throw new IllegalArgumentException("Список автобусов не может быть null");
        }
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым");
        }

        try (FileWriter writer = new FileWriter(filename)) {
            for (Bus bus : buses) {
                 writer.write(String.format("%d,%s,%.1f\n",
                        bus.getNumber(), bus.getModel(), bus.getMileage()));
            }

            System.out.printf("Сохранено %d записей в файл: %s%n", buses.size(), filename);
        } catch (IOException e) {
            System.err.printf("Ошибка при записи в файл %s: %s%n", filename, e.getMessage());
            throw e;
        }
    }
}


