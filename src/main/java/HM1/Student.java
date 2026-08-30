import java.util.*;

class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Имя не может быть пустым");
        if (group == null || group.isBlank()) throw new IllegalArgumentException("Группа не может быть пустой");
        if (course < 1 || course > 5) throw new IllegalArgumentException("Курс должен быть от 1 до 5");
        for (int g : grades) {
            if (g < 2 || g > 5) throw new IllegalArgumentException("Оценка " + g + " вне диапазона 2-5");
        }
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = new ArrayList<>(grades);
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int grade : grades) sum += grade;
        return (double) sum / grades.size();
    }

    public void promote() {
        if (course < 5) this.course++;
    }

    public String getName() { return name; }
    public int getCourse() { return course; }

    @Override
    public String toString() {
        return name + " | " + group + " | курс " + course + " | средний балл " + String.format("%.2f", getAverageGrade());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return course == s.course && Objects.equals(name, s.name) && Objects.equals(group, s.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group, course);
    }
}
