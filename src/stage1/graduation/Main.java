package stage1.graduation;

import stage1.graduation.enums.EntityType;
import stage1.graduation.enums.InputMethod;
import stage1.graduation.model.Bus;
import stage1.graduation.model.Student;
import stage1.graduation.model.User;
import stage1.graduation.strategy.InputData;

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
                EntityType entityType = askEntityType();
                int length = askLength();
                InputMethod inputMethod = askInputMethod();

                InputData strategy = new InputData(inputMethod);
                Class classType = EntityType.getClassType(entityType);
                Object[] userObjects = strategy.input(classType, length);

                Console.println("\n====================================");
                Console.println("Массив введенных объектов:");
                Console.print(userObjects);
                //Comparable[] comparableObjects = new Comparable[]{Comparable.class.cast(userObjects)};

                if (entityType == EntityType.BUS) {
                    Bus[] buses = castToBus(userObjects);
                    Arrays.quicksort(buses, 0, length - 1);
                    Console.println("\n====================================");
                    Console.println("Массив после сортировки по всем полям:");
                    Console.print(buses);

                    Console.println("\n====================================");
                    Console.println("Введите объект, который хотите найти:");
                    Bus bus = Bus.inputBus();
                    Console.println("Поиск объекта:");
                    int index = Arrays.binarySearch(buses, bus);
                    if (index == -1) {
                        Console.println("Объект не найден.");
                    } else {
                        Console.printf("Индекс объекта: %d%n", index);
                    }

                    Console.println("\n====================================");
                    Console.println("Дополнительная сортировка толькл классов с четными значениями полей:");
                    Console.println("Автобусы сортируются по номерам:");
                    Arrays.sortByEvens(buses, Bus.compareByEvenNumber());
                    Console.println("Объекты после сортировки четных полей:");
                    Console.print(buses);
                } else if (entityType == EntityType.STUDENT) {
                    Student[] students = castToStudent(userObjects);
                    Arrays.quicksort(students, 0, userObjects.length - 1);
                    Console.println("\n====================================");
                    Console.println("Массив после сортировки по всем полям:");
                    Console.print(students);

                    Console.println("\n====================================");
                    Console.println("Введите объект, который хотите найти:");
                    Student student = Student.inputStudent();
                    Console.println("Поиск объекта:");
                    int index = Arrays.binarySearch(students, student);
                    if (index == -1) {
                        Console.println("Объект не найден.");
                    } else {
                        Console.printf("Индекс объекта: %d%n", index);
                    }
                    Console.println("\n====================================");
                    Console.println("Дополнительная сортировка толькл классов с четными значениями полей:");

                    Console.println("Студенты сортируются по номерам зачетных книжек:");
                    Arrays.sortByEvens(students, Student.compareByEvenRecordBookNumber());

                    Console.println("Объекты после сортировки четных полей:");
                    Console.print(students);

                } else if (entityType == EntityType.USER) {
                    User[] users = castToUser(userObjects);
                    Arrays.quicksort(users, 0, userObjects.length - 1);
                    Console.println("\n====================================");
                    Console.println("Массив после сортировки по всем полям:");
                    Console.print(userObjects);

                    Console.println("\n====================================");
                    Console.println("Введите объект, который хотите найти:");
                    User user = User.inputUser();
                    Console.println("Поиск объекта:");
                    int index = Arrays.binarySearch(users, user);
                    if (index == -1) {
                        Console.println("Объект не найден.");
                    } else {
                        Console.printf("Индекс объекта: %d%n", index);
                    }
                }
                isCorrectInput = true;
            } else {
                isCorrectInput = false;
            }
            userAnswer = askToContinue(isCorrectInput).toLowerCase();
        } while (!NO.equals(userAnswer));
        Console.println("Завершение программы.");
        Console.close();
    }

    private static EntityType askEntityType() {
        Console.println("1 - Автобус, 2 - Пользователь, 3 - Студент");
        Console.print("Введите тип объекта, с которым хотите работать: ");
        return EntityType.getType(inputItem(1, 3));
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

    private static InputMethod askInputMethod() {
        Console.println("1 - Ручной ввод, 2 - Генерация, 3 - Из файла");
        Console.print("Укажите способ ввода данных: ");
        return InputMethod.getMethod(inputItem(1, 3));
    }

    private static String askToContinue(boolean isCorrectInput) {
        Console.print(isCorrectInput ? "Хотите продолжить? [yes/no]: " :
                "Введите корректный ответ [yes/no]: ");
        return Console.readString();
    }

    private static Bus[] castToBus(Object[] objects) {
        Bus[] buses = new Bus[objects.length];
        for (int i = 0; i < objects.length; i++) {
            buses[i] = (Bus) objects[i];
        }
        return buses;
    }

    private static User[] castToUser(Object[] objects) {
        User[] users = new User[objects.length];
        for (int i = 0; i < objects.length; i++) {
            users[i] = (User) objects[i];
        }
        return users;
    }

    private static Student[] castToStudent(Object[] objects) {
        Student[] students = new Student[objects.length];
        for (int i = 0; i < objects.length; i++) {
            students[i] = (Student) objects[i];
        }
        return students;
    }
}