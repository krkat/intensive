package stage1.graduation.enums;

public enum UserNames {
    alexander,
    diana,
    viktoria,
    sergey,
    dmitriy,
    pavel,
    alexey,
    nikita,
    mihail,
    alexandra,
    mariya,
    valentin;

    // Метод для получения случайного имени
    public static UserNames getRandom() {
        UserNames[] values = values();
        int length = values.length;
        int randomIndex = (int) (Math.random() * length);
        return values[randomIndex];
    }
}
