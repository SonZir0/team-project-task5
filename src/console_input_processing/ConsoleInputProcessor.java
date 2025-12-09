package console_input_processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public void close() {
        try {
            bf.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
