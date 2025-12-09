package console_input_processing;

/**
 * Класс для проверки строкового инпута пользователя на соответствие определенным критерии.
 * Все методы-валидаторы принимают String, возвращают true, если строка подходит, в противном случае false.
 * <p>
 * Метод getNumFromString возвращает сохраненное значение последнего успешного вызова Integer.pasrseInt(), и вызывать
 * его надо следует ТОЛЬКО после успешной проверки на isInteger или isPositiveInteger. Это не самое элегантное решение
 * сделано для того, чтобы не парсить одну и ту же строку несколько раз. А именно:
 *          1)  в isInteger, для проверки число ли это
 *          2)  в IsPositiveInteger, для проверки на положительность
 *          3)  в самом InputProcessor, для возвращения результата
 * Попытка вызвать метод getNumFromString при ложном isInteger или isPositiveInteger приведет к NullPointerException.
 */
public class InputValidator {

    static private Integer numFromString = null;

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
        numFromString = null;
        return false;
    }

    public static boolean isPositiveInteger(String userInput) {
        if (isInteger(userInput) && (numFromString >= 0))
            return true;
        numFromString = null;
        return false;
    }

    public static Integer getNumFromString() {
        if (numFromString == null)
            throw new NullPointerException("Попытка получить недавно обработанный валидный числовой инпут провалилась.");
        return numFromString;
    }
}