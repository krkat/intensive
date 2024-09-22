package stage1.graduation.model;

public class Bus implements Comparable<Bus> {
    private int number;
    private String model;
    private int mileage;

    private Bus(BusBuilder busBuilder) {
        number = busBuilder.number;
        model = busBuilder.model;
        mileage = busBuilder.mileage;
    }

    public int getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public static class BusBuilder {
        private int number;
        private String model;
        private int mileage;

        public BusBuilder(int number, String model, int mileage) {
            this.number = number;
            this.model = model;
            this.mileage = mileage;
        }

        public Bus build() {
            return new Bus(this);
        }
    }

    @Override
    public int compareTo(Bus other) {
        int modelComparison = this.model.compareTo(other.model);
        if (modelComparison != 0) {
            return modelComparison;
        }

        int numberComparison = Integer.compare(this.number, other.number);
        if (numberComparison != 0) {
            return numberComparison;
        }

        return Integer.compare(this.mileage, other.mileage);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
