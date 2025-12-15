public class BusCounter {

    public static int countByMileage(BusList list, int mileage, int threads)
            throws InterruptedException {

        if (list == null) throw new IllegalArgumentException("list is null");
        if (threads <= 0) throw new IllegalArgumentException("threads must be > 0");
        if (list.isEmpty()) return 0;

        int size = list.size();
        int realThreads = Math.min(threads, size);

        BusCountTask[] tasks = new BusCountTask[realThreads];
        Thread[] workers = new Thread[realThreads];

        int chunk = (size + realThreads - 1) / realThreads;

        for (int t = 0; t < realThreads; t++) {
            int from = t * chunk;
            int to = Math.min(from + chunk, size);

            tasks[t] = new BusCountTask(list, from, to, mileage);
            workers[t] = new Thread(tasks[t]);
            workers[t].start();
        }

        int total = 0;
        for (int t = 0; t < realThreads; t++) {
            workers[t].join();
            total += tasks[t].getCount();
        }

        return total;
    }
}
