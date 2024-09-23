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
    public T[] input(Class<T> clazz, int arrayLength) {
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < arrayLength; i++) {
                String line = reader.readLine();
                if (line == null) break;
                String[] data = line.split(",");
                if (clazz == Bus.class) {
                    array[i] = clazz.cast(new Bus.BusBuilder(data[1], Integer.parseInt(data[0])).setMileage(Integer.parseInt(data[2])).build());
                } else if (clazz == User.class) {
                    array[i] = clazz.cast(new User.UserBuilder(data[0]).setPassword(data[1]).setEmail(data[2]).build());
                } else if (clazz == Student.class) {
                    array[i] = clazz.cast(new Student.StudentBuilder(Integer.parseInt(data[2])).setGroupNumber(Integer.parseInt(data[0])).setAverageMark(Float.parseFloat(data[1])).build());
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return array;
    }
}
