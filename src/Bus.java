import java.util.Objects;

public class Bus implements Comparable<Bus> {
    private final String number;
    private final String model;
    private final int mileage;

    // Приватный конструктор
    private Bus(String number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    // Builder-паттерн
    public static class Builder {
        private String number;
        private String model;
        private int mileage;

        public Builder setNumber(String number) {
            if (number == null || number.trim().isEmpty()) {
                throw new IllegalArgumentException("Номер не может быть пустым");
            }
            this.number = number.trim();
            return this;
        }

        public Builder setModel(String model) {
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Модель не может быть пустой");
            }
            this.model = model.trim();
            return this;
        }

        public Builder setMileage(int mileage) {
            if (mileage < 0) {
                throw new IllegalArgumentException("Пробег не может быть отрицательным");
            }
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(number, model, mileage);
        }
    }

    // Геттеры
    public String getNumber() { return number; }
    public String getModel() { return model; }
    public int getMileage() { return mileage; }

    @Override
    public String toString() {
        return String.format("Номер:\t%-12s\tМодель:\t%-16s\tПробег\t%d",
                number, model, mileage);
    }

    @Override
    public int compareTo(Bus secondBus) {
        if (this == secondBus) return 0;

        int temp = this.number.compareTo(secondBus.getNumber());
        if (temp != 0) return temp;

        temp = this.model.compareTo(secondBus.getModel());
        if (temp != 0) return temp;

        return Integer.compare(this.mileage, secondBus.getMileage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number, this.model, this.mileage);
    }

    public static void runAllTests() {
        System.out.println("\nЗапускаем тесты в классе Bus:");
        System.out.printf(
                Messages.TEST_RESULT_FORMAT_STRING.getMessage(),
                "Тест Bus.Builder:",
                Bus.Tests.testBuilder());
    }

    static class Tests {
        static boolean testBuilder() {
            String number = "12";
            String model = "Something";
            int mileage = 9999;
            Bus bus = new Bus.Builder()
                    .setNumber(number)
                    .setModel(model)
                    .setMileage(mileage)
                    .build();
            return bus.getNumber().equals(number) &&
                    bus.getModel().equals(model) &&
                    bus.getMileage() == mileage;
        }
    }
}
