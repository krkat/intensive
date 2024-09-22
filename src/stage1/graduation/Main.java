package stage1.graduation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static final int MIN_LENGTH = 1;
    static final int MAX_LENGTH = 10;
    static final String POSITIVE_ANSWER = "yes";
    static final String NEGATIVE_ANSWER = "no";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userAnswer = POSITIVE_ANSWER;
        boolean isWrongAnswer;
        do {
            if (POSITIVE_ANSWER.equals(userAnswer)) {
                int type = askType(scanner);
                int length = askLength(scanner);
                int inputMethod = askInputMethod(scanner);

                // Ввод данных с использованием стратегии
                /*InputData strategy = new InputData(inputMethod);
                Comparable[] userObjects = strategy.input(userObjects);*/

                // сортировка данных
                //QuickSort.sort(userObjects);

                // поиск объекта
                /*System.out.println("Введите объект, который хотите найтиЖ");
                // Ввод объекта...
                Object object;
                int index = BinarySearch.search(object);
                if (index == -1) {
                    System.out.println("Объект не найден");
                } else {
                    System.out.println("Индекс объекта: ", index);
                }*/

                // дополнительное задание - сортировка по числовому полю
                // вывод отсортированного массива

                isWrongAnswer = false;
            } else {
                isWrongAnswer = true;
            }
            userAnswer = askToContinue(scanner, isWrongAnswer).toLowerCase();
        } while (!NEGATIVE_ANSWER.equals(userAnswer));
        System.out.println("Завершение программы.");
        scanner.close();
    }

    private static int askType(Scanner scanner) {
        System.out.println("1 - Автобус, 2 - Пользователь, 3 - Студент");
        System.out.print("Введите тип объекта, с которым хотите работать: ");
        return input(scanner, 1, 3);
    }

    private static int input(Scanner scanner, int minBound, int maxBound) {
        int inputNumber;
        boolean isWrongAnswer = false;
        do {
            if (isWrongAnswer) {
                System.out.printf("Ошибка! Введите число от %d до %d: ", minBound, maxBound);
            }
            inputNumber = inputInt(scanner);
            isWrongAnswer = true;
        } while (inputNumber < minBound || inputNumber > maxBound);
        return inputNumber;
    }

    private static int inputInt(Scanner scanner) {
        int item;
        do {
            try {
                item = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите корректное целое число: ");
                continue;
            } finally {
                scanner.nextLine();
            }
            break;
        } while (true);
        return item;
    }

    private static int askLength(Scanner scanner) {
        System.out.printf("Введите длину массива от %d до %d: ", MIN_LENGTH, MAX_LENGTH);
        int length = input(scanner, MIN_LENGTH, MAX_LENGTH);
        return length;
    }

    private static int askInputMethod(Scanner scanner) {
        System.out.println("1 - Ручной ввод, 2 - Генерация, 3 - Из файла");
        System.out.print("Укажите способ ввода данных: ");
        return input(scanner, 1, 3);
    }

    private static String askToContinue(Scanner scanner, boolean isWrongAnswer) {
        System.out.print(!isWrongAnswer ? "Хотите продолжить? [yes/no]: " :
                "Введите корректный ответ [yes/no]: ");
        return scanner.nextLine();
    }
}