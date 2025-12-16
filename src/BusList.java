import java.util.*;

public class BusList extends AbstractList<Bus> implements Testable {

    private final int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private Bus[] data = new Bus[INITIAL_CAPACITY];


    private void resize() {
        if (capacity == 0)  capacity = 1;
        else capacity = capacity << 1;
        Bus[] temp = new Bus[capacity];

        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }

    public void setSize(int newCapacity) {
        if (newCapacity < 0)
            throw new IllegalArgumentException("Ошибка, вместимость коллекции не может быть отрицательной");
        if (capacity == newCapacity) return;

        Bus[] temp = new Bus[newCapacity];
        capacity = newCapacity;
        size = Math.min(size, capacity);

        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }

    @Override
    public boolean add(Bus obj) {
        Objects.requireNonNull(obj, "Добавляемый в автопарк автобус не может быть 'null'");
        if (size == capacity) resize();
        data[size] = obj;
        size++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Bus> collection) {
        if (collection.isEmpty()) return false;

        Bus[] objArr = collection.toArray(new Bus[0]);
        if (objArr.length > this.capacity - this.size)
            this.setSize((this.size + objArr.length) << 1);

        System.arraycopy(objArr, 0, this.data, this.size, objArr.length);
        this.size += objArr.length;
        return true;
    }

    @Override
    public Bus get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Messages.BUS_LIST_OUT_OF_BOUNDS.getMessage());
        return data[index];
    }

    @Override
    public Bus set(int index, Bus bus) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Messages.BUS_LIST_OUT_OF_BOUNDS.getMessage());
        Bus oldValue = data[index];
        data[index] = bus;
        return oldValue;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder innerSB = new StringBuilder();
        sb.append(String.format("Size: %d\tCapacity %d\n", size, capacity));
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%6s  ", innerSB.append(i+1).append(".")))
                    .append(data[i].toString())
                    .append("\n");
            innerSB.setLength(0);
        }
        return sb.toString();
    }

    @Override
    public void runAllTests() {
        System.out.println("Запускаем тесты в классе BusList:");
        System.out.println("\tТест добавления 1-го элемента: " + BusList.Tests.testAddOneObject());
        System.out.println("\tТест метода setSize (установление capacity): " + BusList.Tests.testSetSize());
        System.out.println("\tТест авторасширения capacity: " + BusList.Tests.testAutoResize());
        System.out.println("\tТест добавления объектов Bus из другой коллекции: " + BusList.Tests.testAddAll());
    }

    static class Tests {
        static Bus testBus = new Bus.Builder()
                .setNumber("12K")
                .setModel("Something")
                .setMileage(0)
                .build();

        static boolean testAddOneObject() {
            BusList temp = new BusList();
            return temp.size == 0 && temp.capacity == 10 &&
                    temp.add(testBus) &&
                    temp.add(testBus) &&
                    temp.size == 2 && temp.capacity == 10;
        }

        static boolean testSetSize() {
            BusList temp = new BusList();
            temp.add(testBus);
            temp.add(testBus);
            temp.add(testBus);
            if (temp.capacity != 10 && temp.size != 3) return false;
            temp.setSize(100);
            if (temp.capacity != 100 && temp.size != 3) return false;
            temp.setSize(2);
            if (temp.capacity != 2 && temp.size != 2) return false;
            temp.setSize(0);
            return (temp.capacity == 0 && temp.size == 0);
        }

        static boolean testAutoResize() {
            BusList temp = new BusList();
            temp.setSize(1);
            temp.add(testBus);
            if (temp.capacity != 1 && temp.size != 1) return false;
            temp.add(testBus);
            if (temp.capacity != 2 && temp.size != 2) return false;
            temp.add(testBus);
            if (temp.capacity != 4 && temp.size != 3) return false;
            temp.add(testBus);
            temp.add(testBus);
            if (temp.capacity != 8 && temp.size != 5) return false;
            temp.setSize(0);
            temp.add(testBus);
            return (temp.capacity == 1 && temp.size == 1);
        }

        static boolean testAddAll() {
            BusList temp = new BusList();
            BusList secondList = new BusList();
            temp.add(testBus);                  temp.add(testBus);
            temp.add(testBus);                  temp.add(testBus);
            secondList.add(testBus);            secondList.add(testBus);
            secondList.add(testBus);            secondList.add(testBus);

            temp.addAll(secondList);
            secondList.addAll(temp);
            return temp.size == 8 &&
                    temp.capacity == 10 &&
                    secondList.size == 12 &&
                    secondList.capacity == 24;
        }
    }
}
