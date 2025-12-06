import java.util.AbstractList;

public class BusList extends AbstractList<String> {

    private String[] data = new String[10];

    @Override
    public String get(int index) {
            return data[index];
    }

    @Override
    public int size() {
        return this.data.length;
    }
}
