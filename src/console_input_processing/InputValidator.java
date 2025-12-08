package console_input_processing;

public class InputValidator {

    static private Integer numFromString;

    public static boolean isNonEmptySting(String userInput) {
        return userInput != null && !userInput.isBlank();
    }

    public static boolean isInteger(String userInput) {
        if (isNonEmptySting(userInput)) {
            try {
                numFromString = Integer.parseInt(userInput.trim());
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Не корректный ввод. Пожалуйста, введите -ЦЕЛОЕ ЧИСЛО-");
            }
        }
        return false;
    }

    public static boolean isPositiveInteger(String userInput) {
        if (isInteger(userInput)) {
            if (numFromString >= 0) return true;
            System.out.println("Не корректный ввод. Пожалуйста, введите целое -ПОЛОЖИТЕЛЬНОЕ- число");
        }
        return false;
    }

    public static Integer getNumFromString() {
        return numFromString;
    }
}

