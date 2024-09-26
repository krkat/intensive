package stage1.graduation.strategy;

import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;

public class FileInputStrategy<T> implements InputStrategy<T> {
    private final String filePath;

    public FileInputStrategy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public T[] input(Class<T> classType, int arrayLength) {
        T[] array = (T[]) Array.newInstance(classType, arrayLength);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < arrayLength; i++) {
                String line = reader.readLine();
                if (line == null) break;
                String[] data = line.split(",");

                // Вызываем методы создания объектов в зависимости от типа
                if (classType == Bus.class) {
                    array[i] = classType.cast(createBusFromData(data));
                } else if (classType == User.class) {
                    array[i] = classType.cast(createUserFromData(data));
                } else if (classType == Student.class) {
                    array[i] = classType.cast(createStudentFromData(data));
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return array;
    }

    private Bus createBusFromData(String[] data) {
        int number = Integer.parseInt(data[0]);
        String model = data[1];
        int mileage = Integer.parseInt(data[2]);
        return new Bus.BusBuilder(model, number).setMileage(mileage).build();
    }

    private User createUserFromData(String[] data) {
        String name = data[0];
        String password = data[1];
        String email = data[2];
        return new User.UserBuilder(name).setPassword(password).setEmail(email).build();
    }

    private Student createStudentFromData(String[] data) {
        int groupNumber = Integer.parseInt(data[0]);
        float averageScore = Float.parseFloat(data[1]);
        int recordBookNumber = Integer.parseInt(data[2]);
        return new Student.StudentBuilder(recordBookNumber)
                .setGroupNumber(groupNumber)
                .setAverageMark(averageScore)
                .build();
    }
}
