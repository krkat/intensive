package stage1.graduation.model;

import java.util.Comparator;

public class Bus implements Comparable<Bus> {
    private final String model;
    private final int number;
    private final int mileage;

    private Bus(BusBuilder busBuilder) {
        model = busBuilder.model;
        number = busBuilder.number;
        mileage = busBuilder.mileage;
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    public int getMileage() {
        return mileage;
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
                "model='" + model + '\'' +
                ", number=" + number +
                ", mileage=" + mileage +
                '}';
    }

    public static Comparator<Bus> compareByModel() {
        return Comparator.comparing(Bus::getModel);
    }

    public static Comparator<Bus> compareByNumber() {
        return Comparator.comparingInt(Bus::getNumber);
    }

    public static Comparator<Bus> compareByMileage() {
        return Comparator.comparingInt(Bus::getMileage);
    }

    public static class BusBuilder {
        private final String model;
        private int number;
        private int mileage;

        public BusBuilder(String model) {
            if (model == null || model.isEmpty()) {
                throw new RuntimeException("Модель автобуса не может быть null или пустой");
            }
            this.model = model;
        }

        public BusBuilder setNumber(int number) {
            if (number <= 0 || number > 999) {
                throw new RuntimeException("Номер автобуса должен быть больше 0 и меньше 1000");
            }
            this.number = number;
            return this;
        }

        public BusBuilder setMileage(int mileage) {
            if (mileage < 0) {
                throw new RuntimeException("Пробег не может быть отрицательным");
            }
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            if (this.number <= 0) {
                throw new RuntimeException("Должен быть задан номер автобуса, который больше 0");
            }
            return new Bus(this);
        }
    }
}
