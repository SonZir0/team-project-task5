import java.io.*;
import java.util.Optional;

/**
 * Класс для получения инпута через консоль.
 * Публичные методы getXXX выполняются в цикле до тех пор, пока не будет получен валидный результат.
 * При не корректном инпуте со стороны пользователя, все методы должны выводить сообщение о типе ожидаемого инпута.
 * По завершению работы требуется вызвать close().
 */
public class ConsoleInputProcessor implements Testable {

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
            if (temp.isPresent() && temp.get() >= 0)
                return temp.get();
            System.out.println("Не корректный ввод. Пожалуйста, введите -ЦЕЛОЕ НЕОТРИЦАТЕЛЬНОЕ ЧИСЛО-");
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
    @Override
    public  void runAllTests() {
        System.out.println("Запускаем тесты в классе ConsoleInputProcessor:");
        System.out.println("\tТест метода получения непустой строки: " + ConsoleInputProcessor.Tests.testGetNonNullString());
        System.out.println("\tТест метода получения целого числа: " + ConsoleInputProcessor.Tests.testTryToGetInteger());
    }

    static class Tests {
        static boolean testGetNonNullString() {
            // testData имеет строки оканчивающиеся на пробелы. Все что находится до /s является частью строки
            String testData = """
                    \t\t  \t
                          \s
                        \t    12  \s
                    \t    \t    \t
                    
                    Success?""";
            InputStream is = new ByteArrayInputStream(testData.getBytes());
            ConsoleInputProcessor cip = new ConsoleInputProcessor(is);
            // читаем первую "обрезанную" от нечитаемых символов непустую строку
            return cip.getNonEmptyString().equals("12") &&
                    cip.getNonEmptyString().equals("Success?");
        }

        /* Т.к. getInteger и getPositiveInteger выводят сообщения об ожидаемом инпуте в консоль (при тестах это
        * не надо), то тестестировать будем метод, на который они полагаются - tryToGetInteger()     */
        static boolean testTryToGetInteger() {
            // testData имеет строки оканчивающиеся на пробелы. Все что находится до /s является частью строки
            String testData = """
                    \t\t  \t
                    Test3
                    \t\t+12\t\t
                    -3-3
                    ++9
                    82-
                    101.213
                    Something
                        \t    -20  \s
                    Success?""";
            InputStream is = new ByteArrayInputStream(testData.getBytes());
            ConsoleInputProcessor cip = new ConsoleInputProcessor(is);
            // Обращаю внимание, что все идущие подряд пустые строки будут "забраны" из потока за один вызов
            return cip.tryToGetInteger().isEmpty() &&
                    cip.tryToGetInteger().get() == 12 &&
                    cip.tryToGetInteger().isEmpty() &&
                    cip.tryToGetInteger().isEmpty() &&
                    cip.tryToGetInteger().isEmpty() &&
                    cip.tryToGetInteger().isEmpty() &&
                    cip.tryToGetInteger().isEmpty() &&
                    cip.tryToGetInteger().get() == -20 &&
                    cip.tryToGetInteger().isEmpty();
        }
    }
}