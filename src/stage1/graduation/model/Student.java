package stage1.graduation.model;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    private final int groupNumber;
    private final float averageMark;
    private final int recordBookNumber;

    private Student(StudentBuilder studentBuilder) {
        groupNumber = studentBuilder.groupNumber;
        averageMark = studentBuilder.averageMark;
        recordBookNumber = studentBuilder.recordBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    @Override
    public int compareTo(Student other) {
        int groupComparison = Integer.compare(this.groupNumber, other.groupNumber);
        if (groupComparison != 0) {
            return groupComparison;
        }

        int scoreComparison = Double.compare(this.averageMark, other.averageMark);
        if (scoreComparison != 0) {
            return scoreComparison;
        }

        return Integer.compare(this.recordBookNumber, other.recordBookNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageMark=" + averageMark +
                ", recordBookNumber=" + recordBookNumber +
                '}';
    }

    // Компараторы для сортировки по отдельным полям
    public static Comparator<Student> compareByGroupNumber() {
        return Comparator.comparingInt(Student::getGroupNumber);
    }

    public static Comparator<Student> compareByAverageMark() {
        return Comparator.comparingDouble(Student::getAverageMark);
    }

    public static Comparator<Student> compareByRecordBookNumber() {
        return Comparator.comparingInt(Student::getRecordBookNumber);
    }

    //Реализация паттерна Builder
    public static class StudentBuilder {
        private int groupNumber;
        private float averageMark;
        private final int recordBookNumber;

        public StudentBuilder(int recordBookNumber) {
            if (recordBookNumber <= 0) {
                throw new IllegalArgumentException("Номер зачетной книжки должен быть больше 0");
            }
            this.recordBookNumber = recordBookNumber;
        }

        public StudentBuilder setGroupNumber(int groupNumber) {
            if (groupNumber <= 0) {
                throw new IllegalArgumentException("Номер группы должен быть больше 0");
            }
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder setAverageMark(float averageMark) {
            if (averageMark < 0.0f || averageMark > 5.0f) {
                throw new IllegalArgumentException("Средний балл должен быть в промежутке от 0 до 5");
            }
            this.averageMark = averageMark;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
