public interface Shape {
    String getFillColor();
    String getBorderColor();

    double getArea();

    default double getPerimeter() {
        return 0.0;
    }

    default void printInfo() {
        System.out.println("[" + getPerimeter() + ", " + getArea() + ", " + getFillColor() + ", " + getBorderColor() + "]");
    }
}
