package stage1.graduation.strategy;

import stage1.graduation.model.Autobus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;
import java.util.Random;

public class GeneratorInputStrategy<T> implements InputStrategy<T> {
    private final Random random = new Random();  // Генератор случайных чисел

    @Override
    public T[] input(Class<T> clazz, int arrayLength) {
        // Создаем массив объектов типа T
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);

        // Генерация случайных данных для каждого объекта
        for (int i = 0; i < arrayLength; i++) {
            if (clazz == Autobus.class) {
                // Генерация случайных данных для класса Autobus
                int number = random.nextInt(1000);
                String model = "Model" + random.nextInt(100);
                int mileage = random.nextInt(200000);
                array[i] = clazz.cast(Autobus.builder().number(number).model(model).mileage(mileage).build());
            } else if (clazz == User.class) {
                // Генерация случайных данных для класса User
                String name = "User" + random.nextInt(100);
                String password = "Pass" + random.nextInt(1000);
                String email = name.toLowerCase() + "@example.com";
                array[i] = clazz.cast(User.builder().name(name).password(password).email(email).build());
            } else if (clazz == Student.class) {
                // Генерация случайных данных для класса Student
                int groupNumber = random.nextInt(10) + 1;
                double gpa = random.nextDouble() * 4.0;
                String studentId = "ID" + random.nextInt(10000);
                array[i] = clazz.cast(Student.builder().groupNumber(groupNumber).averageScore(gpa).creditNumber(random.nextInt(10000)).build());
            }
        }

        return array;  // Возвращаем массив объектов
    }
}
