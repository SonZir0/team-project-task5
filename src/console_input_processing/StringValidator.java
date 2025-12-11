package console_input_processing;
import java.util.regex.Pattern;

/**
 * Класс для проверки строк на соответствие определенным критериям.
 * Все методы-валидаторы принимают String, возвращают true, если строка подходит, в противном случае false.
 */
public class StringValidator {

    static Pattern intRegEx = Pattern.compile("^\\s*([+-]?[1-9]\\d*|0)\\s*$");

    public static boolean isNonEmptySting(String userInput) {
        return userInput != null && !userInput.isBlank();
    }

    public static boolean isInteger(String userInput) {
        return userInput != null && intRegEx.matcher(userInput).matches();
    }
}