package stage1.graduation.strategy;

import stage1.graduation.model.Autobus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;
import java.util.Scanner;

public class ManualInputStrategy<T> implements InputStrategy<T> {
    private final Scanner scanner = new Scanner(System.in);  // Сканер для ввода данных

    @Override
    public T[] input(Class<T> clazz, int arrayLength) {
        // Создаем массив объектов типа T
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);

        // Цикл для ввода данных для каждого объекта
        for (int i = 0; i < arrayLength; i++) {
            if (clazz == Autobus.class) {
                // Ручной ввод данных для класса Autobus
                System.out.println("Введите данные для Autobus (номер, модель, пробег):");
                int number = scanner.nextInt();
                String model = scanner.next();
                int mileage = scanner.nextInt();
                array[i] = clazz.cast(Autobus.builder().number(number).model(model).mileage(mileage).build());
            } else if (clazz == User.class) {
                // Ручной ввод данных для класса User
                System.out.println("Введите данные для User (имя, пароль, email):");
                String name = scanner.next();
                String password = scanner.next();
                String email = scanner.next();
                array[i] = clazz.cast(User.builder().name(name).password(password).email(email).build());
            } else if (clazz == Student.class) {
                // Ручной ввод данных для класса Student
                System.out.println("Введите данные для Student (номер группы, средний балл, номер зачетки):");
                int groupNumber = scanner.nextInt();
                double gpa = scanner.nextDouble();
                String studentId = scanner.next();
                array[i] = clazz.cast(Student.builder().groupNumber(groupNumber).averageScore(gpa).creditNumber(Integer.parseInt(studentId)).build());
            }
        }

        return array;  // Возвращаем массив объектов
    }
}
