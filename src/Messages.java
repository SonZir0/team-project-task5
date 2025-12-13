public enum Messages {
    MAIN_MENU_MESSAGE("""
            Выберите действие:
            \t1) Установить размер коллекции
            \t2) Задать данные для работы
            \t3) Показать текущую коллекцию
            \t4) Сортировка по 3-м полям
            \t5) Сортировка элементов с четным полем
            \t6) Сохранить коллекцию в файл
            \t7) Посчитать кол-во элементов с полем N
            \t-1) Выйти из программы"""),

    INPUT_STRATEGY_MENU_MESSAGE("""
            Задать данные для работы через:
            \t1) консоль
            \t2) файл
            \t3) случайную генерацию
            \t0) назад"""),

    SET_DATA_MENU_MESSAGE("""
            Выберите действие:
            \t1) добавить один элемент в текущую коллекцию
            \t2) добавить N элементов в текущую коллекцию
            \t0) назад"""),

    COLLECTION_IS_EMPTY("Коллекция пуста!"),
    DEFAULT_SWITCH_MESSAGE("Такого варианта нет, попробуйте еще раз.\n"),
    NULL_REFERENCE_AS_ARGUMENT("Ошибка: вместо листа для сортировки получили null");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
