import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Integer userInput = 0;
        Scanner scanner = new Scanner(System.in);

        while (userInput == null || (userInput != -1)) {
            System.out.println(Messages.MAIN_MENU_MESSAGE.getMessage());

            userInput = readNumberFromInput(scanner);
            if (userInput == null) continue;

            switch (userInput) {
                case (1):
                    dummyFunction();
                    break;
                case (2):
                    while (userInput == null || (userInput != 0)) {
                        System.out.println(Messages.INPUT_STRATEGY_MENU_MESSAGE.getMessage());
                        userInput = readNumberFromInput(scanner);
                        if (userInput == null) continue;
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
                            case (0):
                                break;
                            default:
                                System.out.println(Messages.DEFAULT_SWITCH_MESSAGE.getMessage());
                                break;
                        }
                    }
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
                    break;
                default:
                    System.out.println(Messages.DEFAULT_SWITCH_MESSAGE.getMessage());
                    break;
            }
        }
        scanner.close();
    }

    static void dummyFunction() {
        System.out.println("Пока еще не сделано.\n");
    }

    static Integer readNumberFromInput(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(Messages.INPUT_TYPE_MISMATCH_MESSAGE.getMessage());
            return null;
        } finally {
            // и Scanner, и InputMismatch оставляют символы, мешающие читать int. Поэтому "забираем" остаток инпута
            sc.nextLine();
        }
    }
}