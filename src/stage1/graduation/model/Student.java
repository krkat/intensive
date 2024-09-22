package stage1.graduation.model;

public class Student implements Comparable<Student> {
    private int groupNumber;
    private double averageScore;
    private int creditNumber;

    private Student(StudentBuilder studentBuilder) {
        groupNumber = studentBuilder.groupNumber;
        averageScore = studentBuilder.averageScore;
        creditNumber = studentBuilder.creditNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getCreditNumber() {
        return creditNumber;
    }

    public static class StudentBuilder {
        private int groupNumber;
        private double averageScore;
        private int creditNumber;

        public StudentBuilder(int groupNumber, double averageScore, int creditNumber) {
            this.groupNumber = groupNumber;
            this.averageScore = averageScore;
            this.creditNumber = creditNumber;
        }

        public Student build() {
            return new Student(this);
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

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageScore=" + averageScore +
                ", creditNumber=" + creditNumber +
                '}';
    }
}
