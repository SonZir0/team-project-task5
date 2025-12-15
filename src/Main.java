import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

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
                        System.out.println("Результат сортировки по 3-м полям:\n" + busList);
                    }
                    break;
                case (5):
                    if (busList.isEmpty())  System.out.println(Messages.COLLECTION_IS_EMPTY.getMessage());
                    else {
                        SortEvenFieldOnly.sort(busList);
                        System.out.println("Результат сортировки элементов с четными полям:\n" + busList);
                    }
                    break;
                case (6):
                    saveDataMenu();
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
                    chooseFileToLoad();
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

    private static void chooseFileToLoad() {
        while (true) {
            System.out.println(Messages.CHOOSE_FILE_TO_LOAD_MENU_MESSAGE.getMessage());
            switch (inputProcessor.getInteger()) {
                case (1):
                    if (invokeSetDataMenuIfFileExists("resources\\correctFile.txt"))
                        return;
                    break;
                case (2):
                    if (invokeSetDataMenuIfFileExists("resources\\incorrectFile.txt"))
                        return;
                    break;
                case (3):
                    if (invokeSetDataMenuIfFileExists("resources\\savedCollection.txt"))
                        return;
                    break;
                case (0):
                    return;
                default:
                    System.out.println(Messages.DEFAULT_SWITCH_MESSAGE.getMessage());
                    break;
            }
        }
    }

    private static boolean invokeSetDataMenuIfFileExists(String filePathAsString) {
        Path filePath = Paths.get(filePathAsString);
        if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
            setDataMenu(new DataGetter(new GetDataFromFile(filePathAsString)));
            return true;
        }
        System.out.println("Выбранный файл не существует!");
        return false;
    }

    private static void setDataMenu(DataGetter dataGetter) {
        while (true) {
            System.out.println(Messages.SET_DATA_MENU_MESSAGE.getMessage());
            switch (inputProcessor.getInteger()) {
                case (1):
                    dataGetter.getOneObject().ifPresentOrElse(
                            (obj) -> busList.add(obj),
                            () -> System.err.println(Messages.GET_DATA_NULL_RESULT.getMessage()));
                    return;
                case (2):
                    System.out.print("Введите количество элементов(N): ");
                    int N = inputProcessor.getPositiveInteger();
                    dataGetter.getNObjects(N).ifPresentOrElse(
                            (resultList) ->
                                    checkSizeAndConfirm(resultList, N, (list) -> busList.addAll(list)),
                            () -> System.err.println(Messages.GET_DATA_NULL_RESULT.getMessage()));
                    return;
                case (0):
                    return;
                default:
                    System.out.println(Messages.DEFAULT_SWITCH_MESSAGE.getMessage());
                    break;
            }
        }
    }

    private static void checkSizeAndConfirm(BusList resultList, int N, Consumer<BusList> action) {
        System.out.println("Полученная в результате работы коллекция:\n" + resultList);
        while (true) {
            if (resultList.size() < N)
                System.out.println(Messages.CAUTION_POSSIBLE_ERROR_MESSAGE.getMessage());
            System.out.println(Messages.CONFIRM_ACTION_MESSAGE.getMessage());
            switch (inputProcessor.getInteger()) {
                case (1):
                    action.accept(resultList);  // выполнить данное действие
                    return;
                case (2):
                    System.out.println("Отмена...");
                    return;
                default:
                    System.out.println(Messages.DEFAULT_SWITCH_MESSAGE.getMessage());
                    break;
            }
        }
    }

    static void saveDataMenu() {
        while (true) {
            System.out.println(Messages.SAVE_DATA_MENU.getMessage());
            switch (inputProcessor.getInteger()) {
                case (1):
                    BusFileSaver.appendToFile(busList, true);
                    return;
                case (2):
                    BusFileSaver.appendToFile(busList, false);
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
