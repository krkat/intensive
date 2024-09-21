package stage1.graduation.model;

public class Student implements Comparable<Student> {
    private final int groupNumber;
    private final double averageScore;
    private final int creditNumber;

    public Student(int groupNumber, double averageScore, int creditNumber) {
        this.groupNumber = groupNumber;
        this.averageScore = averageScore;
        this.creditNumber = creditNumber;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

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

        public Student builder() {
            return new Student(groupNumber, averageScore, creditNumber);
        }
    }

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
}
