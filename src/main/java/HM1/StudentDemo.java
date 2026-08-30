import java.util.*;

public class StudentDemo {

    public static boolean shouldRemove(Student student) {
        return student.getAverageGrade() < 3 || student.getCourse() > 4;
    }

    public static void promoteStudent(Student student) {
        if (student.getAverageGrade() >= 3) {
            student.promote();
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты " + course + " курса:");
        boolean found = false;
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println("  " + s.getName());
                found = true;
            }
        }
        if (!found) System.out.println("  (нет)");
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>(Arrays.asList(
                new Student("Иванов", "ГР-101", 1, Arrays.asList(4, 5, 3, 4)),
                new Student("Петров", "ГР-101", 1, Arrays.asList(2, 2, 3, 2)),
                new Student("Сидоров", "ГР-104", 4, Arrays.asList(5, 5, 4, 5)),
                new Student("Кузнецов", "ГР-104", 5, Arrays.asList(3, 3, 4, 3))
        ));

        System.out.println("До обработки");
        students.forEach(System.out::println);

        for (Student s : students) {
            promoteStudent(s);
        }

        students.removeIf(s -> shouldRemove(s));

        System.out.println("\nПосле обработки");
        students.forEach(System.out::println);

        System.out.println();
        printStudents(students, 1);
        printStudents(students, 2);
        printStudents(students, 3);
        printStudents(students, 4);
    }
}