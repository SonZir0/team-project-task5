import java.util.ArrayDeque;

public class SortEvenFieldOnly implements Testable {
    public void sort(BusList originalList) {
        if (originalList == null) throw new NullPointerException(Messages.NULL_REFERENCE_AS_ARGUMENT.getMessage());
        MergeSort ms = new MergeSort();
        BusList evenValSubList = new BusList();
        ArrayDeque<Integer> indicesToReplace = new ArrayDeque<>();

        for (int i = 0; i < originalList.size(); i++)
            if ((originalList.get(i).getMileage() & 1) == 0) {
                evenValSubList.add(originalList.get(i));
                indicesToReplace.add(i);
            }

        ms.mergeSort(evenValSubList);
        for (Bus bus : evenValSubList)
            originalList.set(indicesToReplace.pollFirst(), bus);
    }

    @Override
    public void runAllTests() {
        System.out.println("\nЗапускаем тесты в классе SortEvenFieldOnly:");
        System.out.println("\tТест неизменности позиций элементов с нечетными полями: " + SortEvenFieldOnly.Tests.testOddElemPos());
    }

    static class Tests {
        static boolean testOddElemPos() {
            BusList testList = new BusList();
            SortEvenFieldOnly evenFieldSort = new SortEvenFieldOnly();
            // Объекты Bus создаются в уже отсортированном порядке. В коллекцию они будут занесены по другому
            Bus testBus1 = new Bus.Builder()
                    .setNumber("1")
                    .setModel("RF-12345")
                    .setMileage(100).build();
            Bus testBus2 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("AXX-9999")
                    .setMileage(100).build();
            Bus testBus3 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("RF-12345")
                    .setMileage(0).build();
            Bus testBus4 = new Bus.Builder()    // останется на своем месте из-за нечетного mileage
                    .setNumber("124К")
                    .setModel("RF-1234567890")
                    .setMileage(1).build();
            Bus testBus5 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("RF-12345")
                    .setMileage(100).build();
            Bus testBus6 = new Bus.Builder()
                    .setNumber("124К0000")
                    .setModel("RF-12345")
                    .setMileage(100).build();
            Bus testBus7 = new Bus.Builder()    // останется на своем месте из-за нечетного mileage
                    .setNumber("124К")
                    .setModel("RF-12345")
                    .setMileage(9999).build();

            testList.add(testBus6);
            testList.add(testBus5);
            testList.add(testBus2);
            testList.add(testBus4);
            testList.add(testBus3);
            testList.add(testBus1);
            testList.add(testBus7);
            evenFieldSort.sort(testList);
            // проверяем позиции элементов с нечетными полями, т.к правильность MergeSort проверяется в тестах MergeSort
            return testBus7 == testList.get(6) &&
                    testBus4 == testList.get(3);
        }
    }
}
