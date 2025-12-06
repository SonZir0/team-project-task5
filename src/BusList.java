import java.util.AbstractList;
import java.util.Arrays;
import java.util.Objects;

public class BusList extends AbstractList<Bus> {

    private final int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private Bus[] data = new Bus[INITIAL_CAPACITY];


    public void resize() {
        capacity = (int) (capacity * 1.5);
        Bus[] temp = new Bus[capacity];
        System.arraycopy(data, 0, temp, 0, data.length);
        data = temp;
    }

    @Override
    public boolean add(Bus obj) {
        Objects.requireNonNull(obj, Messages.BUS_LIST_NULL_POINTER_EXCEPTION.getMessage());
        if (size + 1 > capacity) resize();

        data[size] = obj;
        size++;
        return true;
    }

    @Override
    public Bus get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Messages.BUS_LIST_INDEX_OUT_OF_BOUNDS_MESSAGE.getMessage());
        return data[index];
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
