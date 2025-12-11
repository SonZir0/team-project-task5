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

    public static void runAllTests() {
        System.out.println("Запускаем тесты в классе InputValidator:");
        System.out.println("\tТест валидатора непустой строку: " + Tests.testIsNonEmptyString());
        System.out.println("\tТест валидатора интеджеров: " + Tests.testIsInteger());
    }

    static class Tests {
        static boolean testIsNonEmptyString() {
            return !isNonEmptySting(null) &&
                    !isNonEmptySting("") &&
                    isNonEmptySting("  a  ");
        }

        static boolean testIsInteger() {
            return !isInteger("    ") &&
                    !isInteger("-12-12") &&
                    !isInteger("abc") &&
                    isInteger("  +5\n") &&
                    isInteger("-12");
        }
    }
}