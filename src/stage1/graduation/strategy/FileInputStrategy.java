package stage1.graduation.strategy;

import stage1.graduation.Console;
import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;

public class FileInputStrategy<T> implements InputStrategy<T> {
    public FileInputStrategy() {
    }

    @Override
    public T[] input(Class<T> classType, int arrayLength) {
        Console.print("Введите название файла: ");
        String filePath = Console.readString();
        T[] array = (T[]) Array.newInstance(classType, arrayLength);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < arrayLength; i++) {
                String line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    throw new RuntimeException("Недостаточно данных в файле!");
                }
                String[] data = line.split(",");

                // Вызываем методы создания объектов в зависимости от типа
                if (classType == Bus.class) {
                    array[i] = classType.cast(createBusFromData(data, i));
                } else if (classType == User.class) {
                    array[i] = classType.cast(createUserFromData(data, i));
                } else if (classType == Student.class) {
                    array[i] = classType.cast(createStudentFromData(data, i));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка чтения файла: " + e.getMessage());
        }
        return array;
    }

    private Bus createBusFromData(String[] data, int index) {
        Bus bus;
        try {
            int number = Integer.parseInt(data[0]);
            String model = data[1];
            int mileage = Integer.parseInt(data[2]);
            bus = new Bus.BusBuilder(model, number).setMileage(mileage).build();
        } catch (Exception e) {
            throw new RuntimeException("Ощибка при чтении " + index + " строки файла: " + e.getMessage());
        }
        return bus;
    }

    private User createUserFromData(String[] data, int index) {
        User user;
        try {
            String name = data[0];
            String password = data[1];
            String email = data[2];
            user = new User.UserBuilder(name).setPassword(password).setEmail(email).build();
        } catch (Exception e) {
            throw new RuntimeException("Ощибка при чтении " + index + " строки файла: " + e.getMessage());
        }
        return user;
    }

    private Student createStudentFromData(String[] data, int index) {
        Student student;
        try {
            int groupNumber = Integer.parseInt(data[0]);
            float averageScore = Float.parseFloat(data[1]);
            int recordBookNumber = Integer.parseInt(data[2]);
            student = new Student.StudentBuilder(recordBookNumber)
                    .setGroupNumber(groupNumber)
                    .setAverageMark(averageScore)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Ощибка при чтении " + index + " строки файла: " + e.getMessage());
        }
        return student;
    }
}
