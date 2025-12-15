import java.util.*;

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
}
