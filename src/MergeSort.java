public class MergeSort {

    public static void mergeSort(BusList originalList) {
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

    private static void merge(BusList leftList, BusList rightList, BusList originalList) {
        int leftIndex = 0;
        int rightIndex = 0;
        int originalIndex = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {

            if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) < 0) {
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
}
