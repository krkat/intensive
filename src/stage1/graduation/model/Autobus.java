package stage1.graduation.model;

public class Autobus implements Comparable<Autobus> {
    private final int number;
    private final String model;
    private final int mileage;

    public Autobus(int number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public static AutobusBuilder builder() {
        return new AutobusBuilder();
    }

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

    @Override
    public int compareTo(Autobus other) {
        int numberComparison = Integer.compare(this.number, other.number);
        if (numberComparison != 0) {
            return numberComparison;
        }

        int modelComparison = this.model.compareTo(other.model);
        if (modelComparison != 0) {
            return modelComparison;
        }

        return Integer.compare(this.mileage, other.mileage);
    }
}
