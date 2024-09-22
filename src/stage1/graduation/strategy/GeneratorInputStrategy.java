package stage1.graduation.strategy;

import stage1.graduation.model.Autobus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;
import java.util.Random;

public class GeneratorInputStrategy<T> implements InputStrategy<T> {
    private final Random random = new Random();

    @Override
    public T[] input(Class<User> clazz, int arrayLength) {
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            if (clazz == Autobus.class) {
                int number = random.nextInt(1000);
                String model = "Model" + random.nextInt(100);
                int mileage = random.nextInt(200000);
                array[i] = clazz.cast(Autobus.builder()
                        .number(number)
                        .model(model)
                        .mileage(mileage)
                        .build());
            } else if (clazz == User.class) {
                String name = "User" + random.nextInt(100);
                String password = "Pass" + random.nextInt(1000);
                String email = name.toLowerCase() + "@example.com";
                array[i] = clazz.cast(User.builder()
                        .name(name)
                        .password(password)
                        .email(email)
                        .build());
            } else if (clazz == Student.class) {
                int groupNumber = random.nextInt(10) + 1;
                double averageScore = random.nextDouble() * 4.0; // Средний балл от 0 до 4.0
                int creditNumber = random.nextInt(100); // Номер зачетной книжки
                array[i] = clazz.cast(Student.builder()
                        .groupNumber(groupNumber)
                        .averageScore(averageScore)
                        .creditNumber(creditNumber)
                        .build());
            }
        }
        return array;
    }
}
