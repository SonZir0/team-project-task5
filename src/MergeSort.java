public class MergeSort implements Testable{

    public void mergeSort(BusList originalList) {
        if (originalList == null) throw new NullPointerException("Ошибка: вместо листа для сортировки получили null");
        if (originalList.size() < 2)
            return;

        BusList leftList = new BusList();
        BusList rightList = new BusList();
        int center = originalList.size() / 2;

        for (int i = 0; i < center; i++)
            leftList.add(originalList.get(i));

        for (int i = center; i < originalList.size(); i++)
            rightList.add(originalList.get(i));

        mergeSort(leftList);
        mergeSort(rightList);
        merge(leftList,rightList,originalList);
    }

    private void merge(BusList leftList, BusList rightList, BusList originalList) {
        int leftIndex = 0;
        int rightIndex = 0;
        int originalIndex = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {

            if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0) {
                originalList.set(originalIndex, leftList.get(leftIndex));
                leftIndex++;
            } else {
                originalList.set(originalIndex, rightList.get(rightIndex));
                rightIndex++;
            }
            originalIndex++;
        }

        while (leftIndex < leftList.size()) {
            originalList.set(originalIndex, leftList.get(leftIndex));
            originalIndex++;
            leftIndex++;
        }
        while (rightIndex < rightList.size()) {
            originalList.set(originalIndex, rightList.get(rightIndex));
            originalIndex++;
            rightIndex++;
        }
    }

    public void runAllTests() {
        System.out.println("Запускаем тесты в классе MergeSort:");
        System.out.println("\tТест правильности сортировки: " + Tests.testSorting());
        System.out.println("\tТест стабильности сортировки: " + Tests.testStability());
    }

    static class Tests {

        static boolean testSorting() {
            MergeSort ms = new MergeSort();
            BusList testList = new BusList();
            // Объекты Bus создаются в отсортированном порядке. В коллекцию они будут занесены по другому
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
            Bus testBus4 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("RF-12345")
                    .setMileage(100).build();
            Bus testBus5 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("RF-12345")
                    .setMileage(9999).build();
            Bus testBus6 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("RF-1234567890")
                    .setMileage(100).build();
            Bus testBus7 = new Bus.Builder()
                    .setNumber("124К0000")
                    .setModel("RF-12345")
                    .setMileage(100).build();

            testList.add(testBus6);
            testList.add(testBus7);
            testList.add(testBus4);
            testList.add(testBus2);
            testList.add(testBus3);
            testList.add(testBus1);
            testList.add(testBus5);
            ms.mergeSort(testList);
            for (int i = 0; i < testList.size() - 1; i++)
                if ( testList.get(i).compareTo(testList.get(i + 1)) > 0)
                    return false;
            return true;
        }

        static boolean testStability() {
            MergeSort ms = new MergeSort();
            BusList testList = new BusList();
            // Объекты Bus создаются в отсортированном порядке. В коллекцию они будут занесены по другому
            // Объекты № 3,4,5 создаются с одинаковыми параметрами. Проверяем сохранность порядка вставки в лист
            Bus testBus1 = new Bus.Builder()
                    .setNumber("1")
                    .setModel("RF-12345")
                    .setMileage(100).build();
            Bus testBus2 = new Bus.Builder()
                    .setNumber("10")
                    .setModel("RF-12345")
                    .setMileage(100).build();
            Bus testBus3 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("AXX-9999")
                    .setMileage(100).build();
            Bus testBus4 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("AXX-9999")
                    .setMileage(100).build();
            Bus testBus5 = new Bus.Builder()
                    .setNumber("124К")
                    .setModel("AXX-9999")
                    .setMileage(100).build();
            Bus testBus6 = new Bus.Builder()
                    .setNumber("124К-5678A")
                    .setModel("AXX-9999")
                    .setMileage(1000).build();
            Bus testBus7 = new Bus.Builder()
                    .setNumber("124К-5678A")
                    .setModel("RF-12345678")
                    .setMileage(1000).build();

            testList.add(testBus7);
            testList.add(testBus4);
            testList.add(testBus6);
            testList.add(testBus2);
            testList.add(testBus3);
            testList.add(testBus1);
            testList.add(testBus5);
            ms.mergeSort(testList);
            // добавляемые в BusList объекты копируются поверхностно, поэтому определяем правильность по ссылке на объект
            return (testBus4 == testList.get(2) &&
                    testBus3 == testList.get(3) &&
                    testBus5 == testList.get(4));
        }
    }
}
