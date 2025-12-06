import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BusList extends AbstractList<String> {

    private final int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    private int size = 0;
    private String[] data = new String[INITIAL_CAPACITY];


    public void resize() {
        capacity = (int) (capacity * 1.5);
        String[] temp = new String[capacity];
        System.arraycopy(data, 0, temp, 0, data.length);
        data = temp;
    }

    @Override
    public boolean add(String obj) {
        Objects.requireNonNull(obj, Messages.BUS_LIST_NULL_POINTER_EXCEPTION.getMessage());
        if (size + 1 > capacity) resize();

        data[size] = obj;
        size++;
        return true;
    }

    @Override
    public String get(int index) {
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
}
