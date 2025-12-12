import java.util.AbstractList;
import java.util.Arrays;
import java.util.Objects;

public class BusList extends AbstractList<Bus> {

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
        if (size + 1 > capacity) resize();

        data[size] = obj;
        size++;
        return true;
    }

    @Override
    public Bus get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Запрашиваемый индекс выходит за фактический размер массива");
        return data[index];
    }

    @Override
    public Bus set(int index, Bus bus) {
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
        sb.append(String.format("Size: %d\tCapacity %d\n", size, capacity));
        for (int i = 0; i < size; i++) {
            sb.append(data[i].toString())
                    .append("\n");
        }
        return sb.toString();
    }
}
