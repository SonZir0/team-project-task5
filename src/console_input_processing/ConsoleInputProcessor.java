package console_input_processing;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleInputProcessor {

    public static String getString(BufferedReader bf) {
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

    public static Integer getInteger(BufferedReader bf) {
        while (true) {
            try {
                if (InputValidator.isInteger(bf.readLine()))
                    return InputValidator.getNumFromString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Integer getPositiveInteger(BufferedReader bf) {
        while (true) {
            try {
                if (InputValidator.isPositiveInteger(bf.readLine()))
                    return InputValidator.getNumFromString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
