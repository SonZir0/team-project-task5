package console_input_processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс для получения инпута через консоль.
 * Все методы выполняются в цикле до тех пор, пока не будет получен валидный результат.
 * При не корректном инпуте со стороны пользователя, все методы выводят сообщение о типе ожидаемых входных данных.
 * Как будет не нужен, не забудьте вызвать метод close().
 */
public class ConsoleInputProcessor {

    private static final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static String getNonEmptyString() {
        String temp;
        while (true) {
            try {
                temp = bf.readLine();
                if (InputValidator.isNonEmptySting(temp))
                    return temp.trim();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Integer getInteger() {
        while (true) {
            try {
                if (InputValidator.isInteger(bf.readLine()))
                    return InputValidator.getNumFromString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Не корректный ввод. Пожалуйста, введите -ЦЕЛОЕ ЧИСЛО-");
        }
    }

    public static Integer getPositiveInteger() {
        while (true) {
            try {
                if (InputValidator.isPositiveInteger(bf.readLine()))
                    return InputValidator.getNumFromString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Не корректный ввод. Пожалуйста, введите -ЦЕЛОЕ ПОЛОЖИТЕЛЬНОЕ ЧИСЛО-");
        }
    }

    public static void close() {
        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
