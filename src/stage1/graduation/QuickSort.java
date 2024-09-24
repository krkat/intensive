package stage1.graduation;

import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSort {
    static final int MIN_LENGTH = 1;
    static final int MAX_LENGTH = 10;
    static final String YES = "yes";
    static final String NO = "no";



    // Quicksort algorithm
    private static <T> void quicksort(T[] arr, int low, int high, Comparator<T> comparator) {
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

    private static Bus[] createBusArray(int length, int inputMethod) {
        Bus[] buses = new Bus[length];
        for (int i = 0; i < length; i++) {
            buses[i] = new Bus.BusBuilder("Model " + (i + 1), i + 1).setMileage((i + 1) * 10).build();
        }
        return buses;
    }

    private static User[] createUserArray(int length, int inputMethod) {
        User[] users = new User[length];
        for (int i = 0; i < length; i++) {
            users[i] = new User.UserBuilder("User " + (i + 1))
                    .setPassword("password" + (i + 1))
                    .setEmail("user" + (i + 1) + "@example.com")
                    .build();
        }
        return users;
    }

    private static Student[] createStudentArray(int length, int inputMethod) {
        Student[] students = new Student[length];
        for (int i = 0; i < length; i++) {
            students[i] = new Student.StudentBuilder(i + 1)
                    .setGroupNumber(100 + i)
                    .setAverageMark(3.5f + i * 0.1f)
                    .build();
        }
        return students;
    }
}
