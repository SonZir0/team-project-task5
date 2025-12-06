public class Bus {
    private final String number;
    private final String model;
    private final int mileage;

    private Bus(String number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public static class Builder {
        private String number;
        private String model;
        private int mileage;

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
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
            if (number == null || number.trim().isEmpty()) {
                throw new IllegalArgumentException("Номер автобуса не может быть пустым");
            }
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Модель автобуса не может быть пустой");
            }
            return new Bus(number, model, mileage);
        }
    }

    // Геттеры
    public String getNumber() { return number; }
    public String getModel() { { return model; }
    public int getMileage() { return mileage; }

    @Override
    public String toString() {
        return "Bus{number='" + number + "', model='" + model + "', mileage=" + mileage + "}";
    }
}
