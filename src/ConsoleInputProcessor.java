import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * Класс для получения инпута через консоль.
 * Публичные методы getXXX выполняются в цикле до тех пор, пока не будет получен валидный результат.
 * При не корректном инпуте со стороны пользователя, все методы должны выводить сообщение о типе ожидаемого инпута.
 * По завершению работы требуется вызвать close().
 */
public class ConsoleInputProcessor {

    private final BufferedReader bf;

    public ConsoleInputProcessor() {
        this.bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public ConsoleInputProcessor(InputStream inputStream) {
        this.bf = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String getNonEmptyString() {
        while (true) {
            String tempStr = getInput();
            if (StringValidator.isNonEmptySting(tempStr))
                return tempStr.trim();
        }
    }

    public Integer getInteger() {
        while (true) {
            Optional<Integer> temp = tryToGetInteger();
            if (temp.isPresent())
                return temp.get();
            System.out.println("Не корректный ввод. Пожалуйста, введите -ЦЕЛОЕ ЧИСЛО-");
        }
    }

    public Integer getPositiveInteger() {
        while (true) {
            Optional<Integer> temp = tryToGetInteger();
            if (temp.isPresent() && temp.get() > 0)
                return temp.get();
            System.out.println("Не корректный ввод. Пожалуйста, введите -ЦЕЛОЕ ПОЛОЖИТЕЛЬНОЕ ЧИСЛО-");
        }
    }

    private String getInput() {
        try {
            return bf.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Integer> tryToGetInteger() {
        String tempStr = getNonEmptyString();
        if (StringValidator.isInteger(tempStr))
            return Optional.of(Integer.parseInt(tempStr));
        return Optional.empty();
    }

    public void close() {
        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}