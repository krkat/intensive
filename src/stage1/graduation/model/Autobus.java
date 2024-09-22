package stage1.graduation.model;

public class Autobus implements Comparable<Autobus> {
    private final int number;
    private final String model;
    private final int mileage;

    // Конструктор должен быть приватным для использования Builder
    private Autobus(int number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    // Статический метод для вызова билдера
    public static AutobusBuilder builder() {
        return new AutobusBuilder();
    }

    // Вложенный класс Builder
    public static class AutobusBuilder {
        private int number;
        private String model;
        private int mileage;

        public AutobusBuilder number(int number) {
            this.number = number;
            return this;
        }

        public AutobusBuilder model(String model) {
            this.model = model;
            return this;
        }

        public AutobusBuilder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Autobus build() {
            return new Autobus(number, model, mileage);
        }
    }

    // Геттеры для полей (по желанию, если необходимы)
    public int getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    // Реализация метода compareTo для сортировки по полям
    @Override
    public int compareTo(Autobus other) {
        // Сначала сравниваем по number
        int numberComparison = Integer.compare(this.number, other.number);
        if (numberComparison != 0) {
            return numberComparison;
        }

        // Если number одинаковы, сравниваем по model
        int modelComparison = this.model.compareTo(other.model);
        if (modelComparison != 0) {
            return modelComparison;
        }

        // Если model одинаковы, сравниваем по mileage
        return Integer.compare(this.mileage, other.mileage);
    }

    // Переопределение метода toString для вывода объектов
    @Override
    public String toString() {
        return "Autobus{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
