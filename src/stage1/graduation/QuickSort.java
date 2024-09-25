package stage1.graduation;

import java.util.Comparator;

public class QuickSort {

    public static <T> void sortByEvens(T[] elements, Comparator<T> comparatorByEvens) {
        for (int i = 0; i < elements.length; i++) {
            T min = elements[i];
            int indexMin = i;
            for (int j = i; j < elements.length; j++) {
                if (comparatorByEvens.compare(min, elements[j]) > 0) {
                    min = elements[j];
                    indexMin = j;
                }
            }
            if (i != indexMin) {
                swap(elements, i, indexMin);
            }
        }
    }

    private static <T> void swap(T[] arr, int i, int indexMin) {
        T swap = arr[i];
        arr[i] = arr[indexMin];
        arr[indexMin] = swap;
    }
}