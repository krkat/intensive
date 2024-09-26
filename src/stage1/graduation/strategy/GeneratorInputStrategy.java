package stage1.graduation.strategy;

import stage1.graduation.enums.BusModelName;
import stage1.graduation.enums.UserNames;
import stage1.graduation.enums.MailDomains;
import stage1.graduation.model.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeneratorInputStrategy<T> implements InputStrategy<T> {
    private final Random random = new Random();
    private final Set<String> generatedEmails = new HashSet<>();
    private final Set<String> generatedUsernames = new HashSet<>();
    private final Set<Integer> generatedRecordBookNumbers = new HashSet<>();
    private final char[] symbols = {'-', '.', '_'};

    @Override
    public T[] input(Class<T> clazz, int arrayLength) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, arrayLength);

        for (int i = 0; i < arrayLength; i++) {
            if (clazz == Bus.class) {
                array[i] = clazz.cast(new Bus.BusBuilder(BusModelName.getRandom().getModel(), random.nextInt(1000))
                        .setMileage(random.nextInt(200000))
                        .build());
            } else if (clazz == User.class) {
                String username = generateUniqueUsername();
                String email = generateUniqueEmail(username);

                array[i] = clazz.cast(new User.UserBuilder(username)
                        .setPassword(generateRandomPassword())
                        .setEmail(email)
                        .build());
            } else if (clazz == Student.class) {
                int recordBookNumber = generateUniqueRecordBookNumber(); // Генерация уникального номера зачетки
                array[i] = clazz.cast(new Student.StudentBuilder(recordBookNumber)
                        .setGroupNumber(random.nextInt(9999) + 1) // Генерация номера группы от 1 до 9999
                        .setAverageMark(Math.round(random.nextFloat() * 5 * 100) / 100.0f) // Округление до 2-х знаков
                        .build());
            }
        }
        return array;
    }

    private String generateUniqueUsername() {
        String username;
        do {
            String baseName = UserNames.getRandom().name();
            char symbol = symbols[random.nextInt(symbols.length)];
            int number = random.nextInt(100);
            username = baseName + symbol + number;
        } while (generatedUsernames.contains(username));
        generatedUsernames.add(username);
        return username;
    }


    private String generateUniqueEmail(String username) {
        String email;
        do {
            char symbol = symbols[random.nextInt(symbols.length)];
            int number = random.nextInt(100);
            String domain = MailDomains.getRandom().getDomain();
            email = username + symbol + number + "@" + domain;
        } while (generatedEmails.contains(email));
        generatedEmails.add(email);
        return email;
    }

    private int generateUniqueRecordBookNumber() {
        int recordBookNumber;
        do {
            recordBookNumber = random.nextInt(10000); // Генерация номера зачетки от 0 до 9999
        } while (generatedRecordBookNumbers.contains(recordBookNumber)); // Проверка на уникальность
        generatedRecordBookNumbers.add(recordBookNumber);
        return recordBookNumber;
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 8 + random.nextInt(3); // Генерация длины пароля от 8 до 10 символов
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        return password.toString();
    }
}
