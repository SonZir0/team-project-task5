import console_input_processing.ConsoleInputProcessor;

public class Main {

    public static void main(String[] args) {

        BusList busList = new BusList();
        MergeSort ms = new MergeSort();
        Integer menuUserInput = 0;

        while (menuUserInput != -1) {
            System.out.println(Messages.MAIN_MENU_MESSAGE.getMessage());
            menuUserInput = ConsoleInputProcessor.getInteger();

            switch (menuUserInput) {
                case (1):
                    System.out.print(Messages.SET_COLLECTION_SIZE_MESSAGE.getMessage());
                    busList.setSize( ConsoleInputProcessor.getPositiveInteger() );
                    break;
                case (2):
                    while (menuUserInput != 0) {
                        System.out.println(Messages.INPUT_STRATEGY_MENU_MESSAGE.getMessage());
                        menuUserInput = ConsoleInputProcessor.getInteger();
                        switch (menuUserInput) {
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
                    ms.mergeSort(busList);
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
        ConsoleInputProcessor.close();
    }

    static void dummyFunction() {
        System.out.println("Пока еще не сделано.\n");
    }
}