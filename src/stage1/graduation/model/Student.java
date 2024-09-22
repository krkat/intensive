package stage1.graduation.model;

public class Student implements Comparable<Student> {
    private final int groupNumber;
    private final double averageScore;
    private final int creditNumber;

    // Приватный конструктор для паттерна Builder
    private Student(int groupNumber, double averageScore, int creditNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.creditNumber = creditNumber;
    }

    // Статический метод для вызова билдера
    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    // Вложенный класс Builder
    public static class StudentBuilder {
        private int groupNumber;
        private double averageScore;
        private int creditNumber;

        public StudentBuilder groupNumber(int groupNumber) {
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder averageScore(double averageScore) {
            this.averageScore = averageScore;
            return this;
        }

        public StudentBuilder creditNumber(int creditNumber) {
            this.creditNumber = creditNumber;
            return this;
        }

        // Метод build() для создания объекта Student
        public Student build() {
            return new Student(groupNumber, averageScore, creditNumber);
        }
    }

    // Геттеры для доступа к полям, если требуется
    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    // Реализация метода compareTo для сортировки по полям
    @Override
    public int compareTo(Student other) {
        int groupComparison = Integer.compare(this.groupNumber, other.groupNumber);
        if (groupComparison != 0) {
            return groupComparison;
        }

        int scoreComparison = Double.compare(this.averageScore, other.averageScore);
        if (scoreComparison != 0) {
            return scoreComparison;
        }

        return Integer.compare(this.creditNumber, other.creditNumber);
    }

    // Переопределение метода toString для удобного вывода объекта
    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageScore=" + averageScore +
                ", creditNumber=" + creditNumber +
                '}';
    }
}
