public class Main {

    static BusList busList;
    static ConsoleInputProcessor inputProcessor;

    public static void main(String[] args) {
        busList = new BusList();
        inputProcessor = new ConsoleInputProcessor();
        Integer menuUserInput = 0;

        while (menuUserInput != -1) {
            System.out.println(Messages.MAIN_MENU_MESSAGE.getMessage());
            menuUserInput = inputProcessor.getInteger();
            switch (menuUserInput) {
                case (1):
                    System.out.print("Размер коллекции: ");
                    busList.setSize( inputProcessor.getPositiveInteger() );
                    break;
                case (2):
                    setDataStrategyMenu();
                    break;
                case (3):
                    System.out.println(busList);
                    break;
                case (4):
                    if (busList.isEmpty())  System.out.println(Messages.COLLECTION_IS_EMPTY.getMessage());
                    else {
                        MergeSort.mergeSort(busList);
                        System.out.println("Результат сортировки по 3-м полям:");
                        System.out.println(busList);
                    }
                    break;
                case (5):
                    if (busList.isEmpty())  System.out.println(Messages.COLLECTION_IS_EMPTY.getMessage());
                    else {
                        SortEvenFieldOnly.sort(busList);
                        System.out.println("Результат сортировки элементов с четными полям:");
                        System.out.println(busList);
                    }
                    break;
                case (6):
                    dummyFunction();
                    break;
                case (7):
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

    private static void setDataStrategyMenu() {
        while (true) {
            System.out.println(Messages.INPUT_STRATEGY_MENU_MESSAGE.getMessage());
            switch (inputProcessor.getInteger()) {
                case (1):
                    setDataMenu(new DataGetter(new GetDataFromInput(inputProcessor)));
                    break;
                case (2):
                    dummyFunction();
//                    dataGetter = new DataGetter(new GetDataFromFile());
//                    System.out.println("Результат работы getOneObject");
//                    dataGetter.getOneObject();
//                    System.out.println("Результат работы getNObjects");
//                    dataGetter.getNObjects(3);
                    break;
                case (3):
                    setDataMenu(new DataGetter(new GetDataRandom()));
                    break;
                case (0):
                    return;
                default:
                    System.out.println(Messages.DEFAULT_SWITCH_MESSAGE.getMessage());
                    break;
            }
        }
    }

    private static void setDataMenu(DataGetter dataGetter) {
        while (true) {
            System.out.println(Messages.SET_DATA_MENU_MESSAGE.getMessage());
            switch (inputProcessor.getInteger()) {
                case (1):
                    busList.add(dataGetter.getOneObject());
                    return;
                case (2):
                    System.out.print("Введите количество элементов(N): ");
                    busList.addAll(dataGetter.getNObjects(inputProcessor.getPositiveInteger()));
                    return;
                case (0):
                    return;
                default:
                    System.out.println(Messages.DEFAULT_SWITCH_MESSAGE.getMessage());
                    break;
            }
        }
    }

    static void dummyFunction() {
        System.out.println("Пока еще не сделано.\n");
    }
}