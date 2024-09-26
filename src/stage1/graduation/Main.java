package stage1.graduation;

import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;
import stage1.graduation.strategy.*;

public class Main {
    static final int MIN_LENGTH = 1;
    static final int MAX_LENGTH = 10;
    static final String YES = "yes";
    static final String NO = "no";

    public static void main(String[] args) {
        String userAnswer = YES;
        boolean isCorrectInput;

        do {
            if (YES.equals(userAnswer)) {
                int type = askType();
                int length = askLength();
                int inputMethod = askInputMethod();

                if (type == 1) { // Автобус
                    handleInputAndOutput(Bus.class, length, inputMethod);
                } else if (type == 2) { // Пользователь
                    handleInputAndOutput(User.class, length, inputMethod);
                } else if (type == 3) { // Студент
                    handleInputAndOutput(Student.class, length, inputMethod);
                }

                isCorrectInput = true;
            } else {
                isCorrectInput = false;
            }
            userAnswer = askToContinue(isCorrectInput).toLowerCase();
        } while (!NO.equals(userAnswer));

        Console.println("\n=== Завершение программы ===");
        Console.close();
    }

    private static <T> void handleInputAndOutput(Class<T> clazz, int length, int inputMethod) {
        InputStrategy<T> strategy = null;

        switch (inputMethod) {
            case 1:
                strategy = new ManualInputStrategy<>();
                break;
            case 2:
                strategy = new GeneratorInputStrategy<>();
                break;
            case 3:
                strategy = new FileInputStrategy<>(getFilePathForType(clazz));
                break;
        }


        T[] objects = strategy.input(clazz, length);

        Console.println("\n=== Выведенные объекты ===");
        for (T obj : objects) {
            Console.println(obj.toString());
        }
        Console.println("==========================\n");
    }

    private static int askType() {
        Console.println("\nВыберите тип объекта:");
        Console.println("1 - Автобус");
        Console.println("2 - Пользователь");
        Console.println("3 - Студент");
        Console.print("Введите тип объекта: ");
        return inputItem(1, 3);
    }

    private static int inputItem(int minBound, int maxBound) {
        int item;
        boolean isWrongInput = false;
        do {
            if (isWrongInput) {
                Console.printf("Ошибка! Введите число от %d до %d: ", minBound, maxBound);
            }
            item = Console.readInt();
            isWrongInput = true;
        } while (item < minBound || item > maxBound);
        return item;
    }

    private static int askLength() {
        Console.printf("\nВведите длину массива от %d до %d: ", MIN_LENGTH, MAX_LENGTH);
        return inputItem(MIN_LENGTH, MAX_LENGTH);
    }

    private static int askInputMethod() {
        Console.println("\nВыберите способ ввода данных:");
        Console.println("1 - Ручной ввод");
        Console.println("2 - Генерация");
        Console.println("3 - Из файла");
        Console.print("Введите способ ввода данных: ");
        return inputItem(1, 3);
    }

    private static String askToContinue(boolean isCorrectInput) {
        Console.print(isCorrectInput ? "\nХотите продолжить? [yes/no]: " :
                "Введите корректный ответ [yes/no]: ");
        return Console.readString();
    }

    // Возвращает путь к файлу для выбранного типа объекта
    private static String getFilePathForType(Class<?> classType) {
        if (classType.equals(Bus.class)) {
            return "resources/bus.txt";
        } else if (classType.equals(User.class)) {
            return "resources/user.txt";
        } else if (classType.equals(Student.class)) {
            return "resources/student.txt";
        } else {
            throw new IllegalArgumentException("Неверный тип объекта");
        }
    }
}
