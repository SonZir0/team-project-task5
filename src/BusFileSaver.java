package ;

import Bus;
import java.io.*;
import java.util.List;
//готов к добавлению в main
public class BusFileSaver {
    /**
     * Сохраняет коллекцию автобусов в файл в режиме добавления
     * @param buses список автобусов
     * @param filename имя файла
     * @return true, если сохранение успешно
     */
    public boolean appendToFile(List<Bus> buses, String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (Bus bus : buses) {
             writer.write(String.format("%d,%s,%d", 
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







