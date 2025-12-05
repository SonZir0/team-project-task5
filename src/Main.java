import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static private final String MENU_MESSAGE = """
            Выберите действие:
            \t1) Установить размер коллекции
            \t2) Задать данные для работы
            \t3) Сортировка по 3-м полям
            \t4) Сортировка элементов с четным полем
            \t5) Сохранить коллекцию в файл
            \t6) Посчитать кол-во элементов с полем N
            \t-1) Выйти из программы""";

    static private final String DEFAULT_SWITCH_MESSAGE = "Такого варианта нет, попробуйте еще раз.\n";

    public static void main(String[] args) {

        boolean exitFlag = false;
        Integer userInput;
        Scanner scanner = new Scanner(System.in);

        while (!exitFlag) {
            System.out.println(MENU_MESSAGE);

            try {
                userInput = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Не корректный инпут.\nПожалуйста, введите целое число, соответствующее выбранному действию.");
                continue;
            } finally {
                // и Scanner, и InputMismatch оставляют символы, мешающие читать int. Поэтому "забираем" остаток инпута
                scanner.nextLine();
            }

            switch (userInput) {
                case (1):
                    dummyFunction();
                    break;
                case (2):
                    dummyFunction();
                    break;
                case (3):
                    dummyFunction();
                    break;
                case (4):
                    dummyFunction();
                    break;
                case (5):
                    dummyFunction();
                    break;
                case (6):
                    dummyFunction();
                    break;
                case (-1):
                    exitFlag = true;
                    break;
                default:
                    System.out.println(DEFAULT_SWITCH_MESSAGE);
                    break;
            }
        }
        scanner.close();
    }

    static void dummyFunction() {
        System.out.println("Пока еще не сделано.\n");
    }
}