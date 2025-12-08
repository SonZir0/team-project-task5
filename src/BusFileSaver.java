import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import model.Bus;

public class BusFileSaver {
    /**
     * Сохраняет список автобусов в текстовый файл (формат: номер,модель,пробег).
     * @param buses     список автобусов для сохранения
     * @param filename  имя файла (с путём, если нужно)
     * @throws IOException при ошибке записи
     */
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

            }

            System.out.printf("Сохранено %d записей в файл: %s%n", buses.size(), filename);
        } catch (IOException e) {
            System.err.printf("Ошибка при записи в файл %s: %s%n", filename, e.getMessage());
            throw e;
        }
    }
}
