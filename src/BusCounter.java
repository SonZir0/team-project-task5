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

    public static void runAllTests() {
        System.out.println("Запускаем тесты в классе BusCounter:");
        System.out.println("\tТест метода подсчета: " + BusCounter.Tests.testCountByMileage());
    }

    static class Tests {
        static boolean testCountByMileage() {
            Bus bus1 = new Bus.Builder().setNumber("25A")
                    .setModel("Whatever").setMileage(9999).build();
            Bus bus2 = new Bus.Builder().setNumber("25A")
                    .setModel("Whatever").setMileage(0).build();
            Bus bus3 = new Bus.Builder().setNumber("25A")
                    .setModel("Whatever").setMileage(25).build();
            Bus bus4 = new Bus.Builder().setNumber("25A")
                    .setModel("Whatever").setMileage(19).build();
            Bus bus5 = new Bus.Builder().setNumber("25A")
                    .setModel("Whatever").setMileage(11).build();
            BusList tempList = new BusList();
            tempList.add(bus1);
            tempList.add(bus2);
            tempList.add(bus3);
            tempList.add(bus1);
            tempList.add(bus5);
            tempList.add(bus4);
            tempList.add(bus3);
            tempList.add(bus2);
            tempList.add(bus1);
            try {
            return countByMileage(tempList, 9999, 4) == 3 &&
                    countByMileage(tempList, 0, 4) == 2 &&
                    countByMileage(tempList, 25, 4) == 2 &&
                    countByMileage(tempList, 19, 4) == 1 &&
                    countByMileage(tempList, 11, 4) == 1 &&
                    countByMileage(tempList, 1520, 4) == 0;
            } catch (InterruptedException e) {
                System.err.println("Тест метода подсчета был прерван!");
                return false;
            }
        }
    }
}
