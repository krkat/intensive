package stage1.graduation.strategy;

import stage1.graduation.model.Autobus;
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
    public T[] input(Class<T> clazz, int arrayLength) {
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < arrayLength; i++) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] data = line.split(","); // Данные разделены запятыми
                if (clazz == Autobus.class) {
                    int number = Integer.parseInt(data[0]);
                    String model = data[1];
                    int mileage = Integer.parseInt(data[2]);
                    array[i] = clazz.cast(Autobus.builder().number(number).model(model).mileage(mileage).build());
                } else if (clazz == User.class) {
                    String name = data[0];
                    String password = data[1];
                    String email = data[2];
                    array[i] = clazz.cast(User.builder().name(name).password(password).email(email).build());
                } else if (clazz == Student.class) {
                    int groupNumber = Integer.parseInt(data[0]);
                    double gpa = Double.parseDouble(data[1]);
                    String studentId = data[2];
                    array[i] = clazz.cast(Student.builder().groupNumber(groupNumber).averageScore(gpa).creditNumber(Integer.parseInt(studentId)).build());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return array;
    }
}
