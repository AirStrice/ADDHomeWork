import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Lesson_7_junit_5.Factorial;
import Lesson_7_junit_5.TriangleArea;
import Lesson_7_junit_5.Calculator;
import Lesson_7_junit_5.NumberComparator;

public class AllTest {

    @DisplayName("факториал числа 1")
    @Test
    void factorialOfZero() {
        assertEquals(1, Factorial.calculate(0));
    }
    @DisplayName("факториал числа 120")
    @Test
    void factorialOfPositive() {
        assertEquals(120, Factorial.calculate(5));
    }

    @DisplayName("площадь треугольника")
    @Test
    void triangleAreaValid() {
        assertEquals(6.0, TriangleArea.calculate(3, 4, 5), 0.0001);
    }

    @DisplayName("сложение")
    @Test
    void calculatorAdd() {
        assertEquals(5, Calculator.add(2, 3));
    }

    @DisplayName("вычитание")
    @Test
    void calculatorSubtract() {
        assertEquals(1, Calculator.subtract(3, 2));
    }

    @DisplayName("умножение")
    @Test
    void calculatorMultiply() {
        assertEquals(6, Calculator.multiply(2, 3));
    }

    @DisplayName("деление")
    @Test
    void calculatorDivide() {
        assertEquals(2, Calculator.divide(6, 3));
    }

    @DisplayName("Сравнение >")
    @Test
    void comparatorGreater() {
        assertEquals("5 > 3", NumberComparator.compare(5, 3));
    }
    @DisplayName("Сравнение <")
    @Test
    void comparatorLess() {
        assertEquals("3 < 5", NumberComparator.compare(3, 5));
    }
    @DisplayName("Сравнение =")
    @Test
    void comparatorEqual() {
        assertEquals("5 == 5", NumberComparator.compare(5, 5));
    }
}