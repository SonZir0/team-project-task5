public class Main {

    public static void main(String[] args) {

        BusList busList = new BusList();
        MergeSort mergeSort = new MergeSort();
        SortEvenFieldOnly sortEvenObj = new SortEvenFieldOnly();
        ConsoleInputProcessor inputProcessor = new ConsoleInputProcessor();
        Integer menuUserInput = 0;

        while (menuUserInput != -1) {
            System.out.println(Messages.MAIN_MENU_MESSAGE.getMessage());
            menuUserInput = inputProcessor.getInteger();

            switch (menuUserInput) {
                case (1):
                    System.out.print(Messages.SET_COLLECTION_SIZE_MESSAGE.getMessage());
                    busList.setSize( inputProcessor.getPositiveInteger() );
                    break;
                case (2):
                    while (menuUserInput != 0) {
                        System.out.println(Messages.INPUT_STRATEGY_MENU_MESSAGE.getMessage());
                        menuUserInput = inputProcessor.getInteger();
                        switch (menuUserInput) {
                            case (1):
                                dummyFunction();
                                break;
                            case (2):
                                dummyFunction();
                                DataGetter dataGetter = new DataGetter(new GetDataFromFile());
                                System.out.println("Результат работы getOneObject");
                                dataGetter.getOneObject();
                                System.out.println("Результат работы getNObjects");
                                dataGetter.getNObjects(3);
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
                    mergeSort.mergeSort(busList);
                    break;
                case (4):
                    sortEvenObj.sort(busList);
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
        inputProcessor.close();
    }

    static void dummyFunction() {
        System.out.println("Пока еще не сделано.\n");
    }
}