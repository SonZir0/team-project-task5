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
            } catch (NumberFormatException _) {}
        }
        return false;
    }

    public static boolean isPositiveInteger(String userInput) {
        return (isInteger(userInput) && (numFromString >= 0));
    }

    public static Integer getNumFromString() {
        return numFromString;
    }
}

