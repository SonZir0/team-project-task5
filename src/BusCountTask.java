public class BusCountTask implements Runnable {

    private final BusList list;
    private final int from;
    private final int to;
    private final int target;
    private int count;

    public BusCountTask(BusList list, int from, int to, int target) {
        this.list = list;
        this.from = from;
        this.to = to;
        this.target = target;
    }

    @Override
    public void run() {
        int local = 0;
        for (int i = from; i < to; i++) {
            if (list.get(i).getMileage() == target) {
                local++;
            }
        }
        count = local;
    }

    public int getCount() {
        return count;
    }
}
