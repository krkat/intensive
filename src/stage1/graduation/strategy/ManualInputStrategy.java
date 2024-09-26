package stage1.graduation.strategy;

import stage1.graduation.Console;
import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;

import java.lang.reflect.Array;

public class ManualInputStrategy<T> implements InputStrategy<T> {
    @Override
    public T[] input(Class<T> classType, int arrayLength) {
        T[] array = (T[]) Array.newInstance(classType, arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            Console.println("====================================");
            if (classType == Bus.class) {
                array[i] = classType.cast(inputBus(i));
            } else if (classType == User.class) {
                array[i] = classType.cast(inputUser(i));
            } else if (classType == Student.class) {
                array[i] = classType.cast(inputStudent(i));
            }
            Console.println("====================================\n");
        }
        return array;
    }

    // Ввод данных для автобусов
    private Bus inputBus(int busIndex) {
        Console.printf("Введите данные для автобуса %d:\n", busIndex + 1);
        String model = askForBusModel();
        int number = askForBusNumber();
        int mileage = askForBusMileage();
        return new Bus.BusBuilder(model, number).setMileage(mileage).build();
    }

    private User inputUser(int userIndex) {
        Console.printf("Введите данные для пользователя %d:\n", userIndex + 1);
        String name = askForUserName();
        String password = askForUserPassword();
        String email = askForUserEmail();
        return new User.UserBuilder(name).setPassword(password).setEmail(email).build();
    }

    private Student inputStudent(int studentIndex) {
        Console.printf("Введите данные для студента %d:\n", studentIndex + 1);
        int recordBookNumber = askForStudentRecordBookNumber();
        int groupNumber = askForStudentGroupNumber();
        float averageScore = askForStudentAverageScore();
        return new Student.StudentBuilder(recordBookNumber)
                .setGroupNumber(groupNumber)
                .setAverageMark(averageScore)
                .build();
    }

    private String askForBusModel() {
        Console.print("Введите модель автобуса: ");
        return Console.readString();
    }

    private int askForBusNumber() {
        Console.print("Введите номер автобуса: ");
        return Console.readInt();
    }

    private int askForBusMileage() {
        Console.print("Введите пробег автобуса: ");
        return Console.readInt();
    }

    private String askForUserName() {
        Console.print("Введите имя пользователя: ");
        return Console.readString();
    }

    private String askForUserPassword() {
        Console.print("Введите пароль пользователя: ");
        return Console.readString();
    }

    private String askForUserEmail() {
        Console.print("Введите email пользователя: ");
        return Console.readString();
    }

    private int askForStudentRecordBookNumber() {
        Console.print("Введите номер зачётной книжки студента: ");
        return Console.readInt();
    }

    private int askForStudentGroupNumber() {
        Console.print("Введите номер группы студента: ");
        return Console.readInt();
    }

    private float askForStudentAverageScore() {
        Console.print("Введите средний балл студента: ");
        return Console.readFloat();
    }
}
