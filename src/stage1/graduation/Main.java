package stage1.graduation;

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

                isCorrectInput = true;
            } else {
                isCorrectInput = false;
            }
            userAnswer = askToContinue(isCorrectInput).toLowerCase();
        } while (!NO.equals(userAnswer));
        Console.println("Завершение программы.");
        Console.close();
    }

    private static int askType() {
        Console.println("1 - Автобус, 2 - Пользователь, 3 - Студент");
        Console.print("Введите тип объекта, с которым хотите работать: ");
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
        Console.printf("Введите длину массива от %d до %d: ", MIN_LENGTH, MAX_LENGTH);
        return inputItem(MIN_LENGTH, MAX_LENGTH);
    }

    private static int askInputMethod() {
        Console.println("1 - Ручной ввод, 2 - Генерация, 3 - Из файла");
        Console.print("Укажите способ ввода данных: ");
        return inputItem(1, 3);
    }

    private static String askToContinue(boolean isCorrectInput) {
        Console.print(isCorrectInput ? "Хотите продолжить? [yes/no]: " :
                "Введите корректный ответ [yes/no]: ");
        return Console.readString();
    }
}