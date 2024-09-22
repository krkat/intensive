package stage1.graduation;

import stage1.graduation.model.Autobus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;
import stage1.graduation.strategy.FileInputStrategy;
import stage1.graduation.strategy.GeneratorInputStrategy;
import stage1.graduation.strategy.InputStrategy;
import stage1.graduation.strategy.ManualInputStrategy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Выберите класс для работы:");
            System.out.println("1. Autobus");
            System.out.println("2. User");
            System.out.println("3. Student");
            System.out.println("0. Выход");
            int classChoice = scanner.nextInt();

            if (classChoice == 0) {
                running = false;
                break;
            }

            System.out.println("Выберите способ ввода данных:");
            System.out.println("1. Вручную");
            System.out.println("2. Сгенерировать случайные данные");
            System.out.println("3. Загрузить из файла");
            int inputChoice = scanner.nextInt();

            System.out.println("Введите количество элементов:");
            int arrayLength = scanner.nextInt();

            switch (classChoice) {
                case 1 -> {
                    InputStrategy<Autobus> inputStrategy = chooseInputStrategy(inputChoice, Autobus.class, scanner);
                    Autobus[] autobusArray = inputStrategy.input(Autobus.class, arrayLength);
                    System.out.println("Введенные данные для класса Autobus:");
                    Arrays.stream(autobusArray).forEach(System.out::println);
                }
                case 2 -> {
                    InputStrategy<User> inputStrategy = chooseInputStrategy(inputChoice, User.class, scanner);
                    User[] userArray = inputStrategy.input(User.class, arrayLength);
                    System.out.println("Введенные данные для класса User:");
                    Arrays.stream(userArray).forEach(System.out::println);
                }
                case 3 -> {
                    InputStrategy<Student> inputStrategy = chooseInputStrategy(inputChoice, Student.class, scanner);
                    Student[] studentArray = inputStrategy.input(Student.class, arrayLength);
                    System.out.println("Введенные данные для класса Student:");
                    Arrays.stream(studentArray).forEach(System.out::println);
                }
                default -> System.out.println("Неверный выбор класса.");
            }

            System.out.println("Хотите повторить? (1 - Да, 0 - Нет)");
            int repeat = scanner.nextInt();
            if (repeat == 0) {
                running = false;
            }
        }

        System.out.println("Программа завершена.");
    }

    // Универсальный метод для выбора InputStrategy
    private static <T> InputStrategy<T> chooseInputStrategy(int inputChoice, Class<T> clazz, Scanner scanner) {
        if (inputChoice == 1) {
            return new ManualInputStrategy<>();
        } else if (inputChoice == 2) {
            return new GeneratorInputStrategy<>();
        } else {
            System.out.println("Введите путь к файлу:");
            String filePath = scanner.next();
            return new FileInputStrategy<>(filePath);
        }
    }
}
