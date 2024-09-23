package stage1.graduation.strategy;

import stage1.graduation.Console;
import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;

public class ManualInputStrategy<T> implements InputStrategy<T> {
    @Override
    public T[] input(Class<T> clazz, int arrayLength) {
        T[] array = (T[]) Array.newInstance(clazz, arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            Console.println("====================================");
            if (clazz == Bus.class) {
                Console.printf("Введите данные для автобуса %d:\n", i + 1);
                int number = askForBusNumber(i + 1);
                String model = askForBusModel(i + 1);
                int mileage = askForBusMileage(i + 1);
                array[i] = clazz.cast(new Bus.BusBuilder(model, number).setMileage(mileage).build());
            } else if (clazz == User.class) {
                Console.printf("Введите данные для пользователя %d:\n", i + 1);
                String name = askForUserName(i + 1);
                String password = askForUserPassword(i + 1);
                String email = askForUserEmail(i + 1);
                array[i] = clazz.cast(new User.UserBuilder(name).setPassword(password).setEmail(email).build());
            } else if (clazz == Student.class) {
                Console.printf("Введите данные для студента %d:\n", i + 1);
                int groupNumber = askForStudentGroupNumber(i + 1);
                float averageScore = askForStudentAverageScore(i + 1);
                int recordBookNumber = askForStudentRecordBookNumber(i + 1);
                array[i] = clazz.cast(new Student.StudentBuilder(recordBookNumber)
                        .setGroupNumber(groupNumber)
                        .setAverageMark(averageScore)
                        .build());
            }
            Console.println("====================================\n");
        }
        return array;
    }

    // Методы для ввода данных для автобусов
    private int askForBusNumber(int busIndex) {
        Console.print("Введите номер автобуса: ");
        return Console.readInt();
    }

    private String askForBusModel(int busIndex) {
        Console.print("Введите модель автобуса: ");
        return Console.readString();
    }

    private int askForBusMileage(int busIndex) {
        Console.print("Введите пробег автобуса: ");
        return Console.readInt();
    }

    // Методы для ввода данных для пользователей
    private String askForUserName(int userIndex) {
        Console.print("Введите имя пользователя: ");
        return Console.readString();
    }

    private String askForUserPassword(int userIndex) {
        Console.print("Введите пароль пользователя: ");
        return Console.readString();
    }

    private String askForUserEmail(int userIndex) {
        Console.print("Введите email пользователя: ");
        return Console.readString();
    }

    // Методы для ввода данных для студентов
    private int askForStudentGroupNumber(int studentIndex) {
        Console.print("Введите номер группы студента: ");
        return Console.readInt();
    }

    private float askForStudentAverageScore(int studentIndex) {
        Console.print("Введите средний балл студента: ");
        return Console.readFloat();
    }

    private int askForStudentRecordBookNumber(int studentIndex) {
        Console.print("Введите номер зачётной книжки студента: ");
        return Console.readInt();
    }
}
