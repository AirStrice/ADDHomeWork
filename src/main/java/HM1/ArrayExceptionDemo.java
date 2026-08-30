class ArrayExceptionDemo {
    public static void runDemo() {
        System.out.println("\n--- ArrayIndexOutOfBoundsException ---");
        int[] numbers = {10, 20, 30};
        try {
            int value = numbers[5];
            System.out.println(value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Поймано: " + e.getMessage());
        }
        System.out.println("Программа продолжает работу.");
    }
}