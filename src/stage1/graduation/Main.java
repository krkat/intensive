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
                EntityType type = askEntityType();
                int length = askLength();
                InputMethod inputMethod = askInputMethod();

                InputData strategy = new InputData(inputMethod);
                Object[] userObjects = strategy.input(EntityType.getClassType(type), length);

                Console.println("\n====================================");
                Console.println("Массив введенных объектов:");
                Console.print(userObjects);

                /*QuickSort.quicksort(userObjects, 0, userObjects.length - 1, Bus.compareByEvenMileage());
                Console.println("\n====================================");
                Console.println("Массив после сортировки по всем полям:");
                Console.print(userObjects);

                Console.println("\n====================================");
                Console.println("Введите объект, который хотите найти:");
                Object object = askObject(type);
                Console.println("Поиск объекта:");
                int index = BinarySearch.binarySearch(userObjects, object);
                if (index == -1) {
                    Console.println("Объект не найден.");
                } else {
                    Console.printf("Индекс объекта: %d%n", index);
                }

                if (type != EntityType.USER) {
                    Console.println("\n====================================");
                    Console.println("Дополнительная сортировка толькл классов с четными значениями полей:");
                    if (type == EntityType.BUS) {
                        Console.println("Автобусы сортируются по номерам:");
                        QuickSort.sortByEvens(userObjects, Bus.compareByEvenNumber());
                    }
                    if (type == EntityType.STUDENT) {
                        Console.println("Студенты сортируются по номерам зачетных книжек:");
                        QuickSort.sortByEvens(userObjects, Student.compareByEvenRecordBookNumber());
                    }
                    Console.println("Объекты после сортировки четных полей:");
                    Console.print(userObjects);
                }*/
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

    private static Object askObject(EntityType type) {
        switch (type) {
            case BUS: return inputBus(0);
            case USER: return inputUser(0);
            case STUDENT: return inputStudent(0);
        }
        return null;
    }

    private static Bus inputBus(int busIndex) {
        Console.printf("Введите данные для автобуса %d:\n", busIndex + 1);
        String model = askForBusModel();
        int number = askForBusNumber();
        int mileage = askForBusMileage();
        return new Bus.BusBuilder(model, number).setMileage(mileage).build();
    }

    private static User inputUser(int userIndex) {
        Console.printf("Введите данные для пользователя %d:\n", userIndex + 1);
        String name = askForUserName();
        String password = askForUserPassword();
        String email = askForUserEmail();
        return new User.UserBuilder(name).setPassword(password).setEmail(email).build();
    }

    private static Student inputStudent(int studentIndex) {
        Console.printf("Введите данные для студента %d:\n", studentIndex + 1);
        int recordBookNumber = askForStudentRecordBookNumber();
        int groupNumber = askForStudentGroupNumber();
        float averageScore = askForStudentAverageScore();
        return new Student.StudentBuilder(recordBookNumber)
                .setGroupNumber(groupNumber)
                .setAverageMark(averageScore)
                .build();
    }

    private static String askForBusModel() {
        Console.print("Введите модель автобуса: ");
        return Console.readString();
    }

    private static int askForBusNumber() {
        Console.print("Введите номер автобуса: ");
        return Console.readInt();
    }

    private static int askForBusMileage() {
        Console.print("Введите пробег автобуса: ");
        return Console.readInt();
    }

    private static String askForUserName() {
        Console.print("Введите имя пользователя: ");
        return Console.readString();
    }

    private static String askForUserPassword() {
        Console.print("Введите пароль пользователя: ");
        return Console.readString();
    }

    private static String askForUserEmail() {
        Console.print("Введите email пользователя: ");
        return Console.readString();
    }

    private static int askForStudentRecordBookNumber() {
        Console.print("Введите номер зачётной книжки студента: ");
        return Console.readInt();
    }

    private static int askForStudentGroupNumber() {
        Console.print("Введите номер группы студента: ");
        return Console.readInt();
    }

    private static float askForStudentAverageScore() {
        Console.print("Введите средний балл студента: ");
        return Console.readFloat();
    }
}