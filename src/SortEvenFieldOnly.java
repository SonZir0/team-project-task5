import java.util.ArrayDeque;

public class SortEvenFieldOnly {
    public void sort(BusList originalList) {
        MergeSort ms = new MergeSort();
        BusList evenValSubList = new BusList();
        ArrayDeque<Integer> indicesToReplace = new ArrayDeque<Integer>();

        for (int i = 0; i < originalList.size(); i++)
            if ((originalList.get(i).getMileage() & 1) == 0) {
                evenValSubList.add(originalList.get(i));
                indicesToReplace.add(i);
            }

        ms.mergeSort(evenValSubList);
        for (Bus bus : evenValSubList)
            originalList.set(indicesToReplace.pollFirst(), bus);
    }
}
