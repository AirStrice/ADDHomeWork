import org.testng.annotations.Test;
import static org.testng.Assert.*;
import Lesson_7_junit_5NG.Factorial;
import Lesson_7_junit_5NG.TriangleArea;
import Lesson_7_junit_5NG.Calculator;
import Lesson_7_junit_5NG.NumberComparator;

public class AllTest {

    @Test(description = "факториал числа")
    void factorialOfZero() {
        assertEquals(Factorial.calculate(0), 1);
    }

    @Test(description = "факториал числа")
    void factorialOfPositive() {
        assertEquals(Factorial.calculate(5), 120);
    }

    @Test(description = "площадь треугольника")
    void triangleAreaValid() {
        assertEquals(TriangleArea.calculate(3, 4, 5), 6.0, 0.0001);
    }

    @Test(description = "сложение")
    void calculatorAdd() {
        assertEquals(Calculator.add(2, 3), 5);
    }

    @Test(description = "вычитание")
    void calculatorSubtract() {
        assertEquals(Calculator.subtract(3, 2), 1);
    }

    @Test(description = "умножение")
    void calculatorMultiply() {
        assertEquals(Calculator.multiply(2, 3), 6);
    }

    @Test(description = "деление")
    void calculatorDivide() {
        assertEquals(Calculator.divide(6, 3), 2);
    }

    @Test(description = "Сравнение >")
    void comparatorGreater() {
        assertEquals(NumberComparator.compare(5, 3), "5 > 3");
    }

    @Test(description = "Сравнение <")
    void comparatorLess() {
        assertEquals(NumberComparator.compare(3, 5), "3 < 5");
    }

    @Test(description = "Сравнение =")
    void comparatorEqual() {
        assertEquals(NumberComparator.compare(5, 5), "5 == 5");
    }
}