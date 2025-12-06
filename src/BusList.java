import java.util.AbstractList;

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
    public boolean add(String s) {
        if (size + 1 > capacity) resize();

        data[size] = s;
        size++;
        return true;
    }

    @Override
    public String get(int index) {
            return data[index];
    }

    @Override
    public int size() {
        return this.size;
    }
}
