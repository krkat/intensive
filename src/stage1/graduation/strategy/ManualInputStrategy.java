package stage1.graduation.strategy;

import stage1.graduation.model.Autobus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;
import java.util.Scanner;

public class ManualInputStrategy<T> implements InputStrategy<T> {
    @Override
    public T[] input(Class<User> clazz, int arrayLength) {
        Scanner scanner = new Scanner(System.in);
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);

        try {
            for (int i = 0; i < arrayLength; i++) {
                // Используем ввод в зависимости от класса
                if (clazz == Autobus.class) {
                    System.out.println("Введите номер, модель и пробег автобуса:");
                    int number = scanner.nextInt();
                    String model = scanner.next();
                    int mileage = scanner.nextInt();
                    array[i] = clazz.cast(Autobus.builder()
                            .number(number)
                            .model(model)
                            .mileage(mileage)
                            .build());
                } else if (clazz == User.class) {
                    System.out.println("Введите имя, пароль и почту пользователя:");
                    String name = scanner.next();
                    String password = scanner.next();
                    String email = scanner.next();
                    array[i] = clazz.cast(User.builder()
                            .name(name)
                            .password(password)
                            .email(email)
                            .build());
                } else if (clazz == Student.class) {
                    System.out.println("Введите номер группы, средний балл и номер зачетной книжки студента:");
                    int groupNumber = scanner.nextInt();
                    double averageScore = scanner.nextDouble();
                    int creditNumber = scanner.nextInt();
                    array[i] = clazz.cast(Student.builder()
                            .groupNumber(groupNumber)
                            .averageScore(averageScore)
                            .creditNumber(creditNumber)
                            .build());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        }
        return array;
    }
}
