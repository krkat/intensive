package stage1.graduation;

import java.util.Comparator;

public class QuickSort {

    public static <T> void quicksort(T[] arr, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pi = partition(arr, low, high, comparator);
            quicksort(arr, low, pi - 1, comparator);
            quicksort(arr, pi + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] arr, int low, int high, Comparator<T> comparator) {
        T pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(arr[j], pivot) < 0) {
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

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