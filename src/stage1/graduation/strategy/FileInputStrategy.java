package stage1.graduation.strategy;

import stage1.graduation.model.Autobus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;

public class FileInputStrategy<T> implements InputStrategy<T> {
    private final String filePath;  // Путь к файлу, из которого будут загружаться данные

    public FileInputStrategy(String filePath) {
        this.filePath = filePath;  // Инициализация пути к файлу через конструктор
    }

    @Override
    public T[] input(Class<T> clazz, int arrayLength) {
        // Создаем массив объектов типа T
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Читаем файл построчно
            for (int i = 0; i < arrayLength; i++) {
                String line = reader.readLine();  // Читаем строку
                if (line == null) {
                    break;  // Если строка null, заканчиваем чтение
                }
                // Разделяем строку на компоненты, используя запятую как разделитель
                String[] data = line.split(",");

                // Создаем объект в зависимости от типа clazz
                if (clazz == Autobus.class) {
                    // Преобразуем строковые данные в нужные типы и создаем объект Autobus
                    int number = Integer.parseInt(data[0]);
                    String model = data[1];
                    int mileage = Integer.parseInt(data[2]);
                    array[i] = clazz.cast(Autobus.builder().number(number).model(model).mileage(mileage).build());
                } else if (clazz == User.class) {
                    // Преобразуем строковые данные для User
                    String name = data[0];
                    String password = data[1];
                    String email = data[2];
                    array[i] = clazz.cast(User.builder().name(name).password(password).email(email).build());
                } else if (clazz == Student.class) {
                    // Преобразуем строковые данные для Student
                    int groupNumber = Integer.parseInt(data[0]);
                    double gpa = Double.parseDouble(data[1]);
                    String studentId = data[2];
                    array[i] = clazz.cast(Student.builder().groupNumber(groupNumber).averageScore(gpa).creditNumber(Integer.parseInt(studentId)).build());
                }
            }
        } catch (Exception e) {
            // Обрабатываем любые ошибки чтения файла
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return array;  // Возвращаем массив объектов
    }
}
