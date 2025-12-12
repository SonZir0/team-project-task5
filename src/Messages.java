public enum Messages {
    MAIN_MENU_MESSAGE("""
            Выберите действие:
            \t1) Установить размер коллекции
            \t2) Задать данные для работы
            \t3) Сортировка по 3-м полям
            \t4) Сортировка элементов с четным полем
            \t5) Сохранить коллекцию в файл
            \t6) Посчитать кол-во элементов с полем N
            \t-1) Выйти из программы"""),

    INPUT_STRATEGY_MENU_MESSAGE("""
            Задать данные для работы через:
            \t1) консоль
            \t2) файл
            \t3) случайную генерацию
            \t0) назад"""),

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
