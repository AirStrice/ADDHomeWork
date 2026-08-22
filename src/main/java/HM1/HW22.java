package HM1;

public class HW22 {

        public static void main(String[] args) {
            System.out.println("1. printThreeWords:");
            printThreeWords();

            System.out.println("\n2. checkSumSign:");
            checkSumSign();

            System.out.println("\n3. printColor:");
            printColor();

            System.out.println("\n4. compareNumbers:");
            compareNumbers();

            System.out.println("\n5. sum in range 10..20");
            System.out.println(isSumInRange(5, 10));

            System.out.println("\n6. number sign:");
            printNumberSign(5);

            System.out.println("\n7. isNegative:");
            System.out.println(isNegative(-1));

            System.out.println("\n8. print string N times:");
            printStringTimes("123", 3);

            System.out.println("\n9. leap year:");
            System.out.println(isLeapYear(2000));

            System.out.println("\n10. invert binary array:");
            int[] binary = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
            invertArray(binary);
            System.out.println(java.util.Arrays.toString(binary));

            System.out.println("\n11. 1..100:");
            int[] hundred = fillArray100();
            System.out.println(java.util.Arrays.toString(hundred));

            System.out.println("\n12. numbers < 6 by 2:");
            int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
            multiplyLessThanSix(arr);
            System.out.println(java.util.Arrays.toString(arr));

            System.out.println("\n13. matrix 5x5:");
            printDiagonalMatrix(5);

            System.out.println("\n14. array len=5, initialValue=7:");
            System.out.println(java.util.Arrays.toString(createArray(5, 7)));
        }

        public static void printThreeWords() {
            System.out.println("Orange");
            System.out.println("Banana");
            System.out.println("Apple");
        }

        public static void checkSumSign() {
            int a = 5;
            int b = -10;
            if (a + b >= 0) {
                System.out.println("Сумма положительная");
            } else {
                System.out.println("Сумма отрицательная");
            }
        }

        public static void printColor() {
            int value = 50;
            if (value <= 0) {
                System.out.println("Красный");
            } else if (value > 0 && value <= 100) {
                System.out.println("Желтый");
            } else {
                System.out.println("Зеленый");
            }
        }

        public static void compareNumbers() {
            int a = 10;
            int b = 5;
            if (a >= b) {
                System.out.println("a >= b");
            } else {
                System.out.println("a < b");
            }
        }

        public static boolean isSumInRange(int a, int b) {
            int sum = a + b;
            return sum >= 10 && sum <= 20;
        }

        public static void printNumberSign(int number) {
            if (number >= 0) {
                System.out.println(number + " - положительное");
            } else {
                System.out.println(number + " - отрицательное");
            }
        }

        public static boolean isNegative(int number) {
            return number < 0;
        }

        public static void printStringTimes(String s, int times) {
            for (int i = 0; i < times; i++) {
                System.out.println(s);
            }
        }

        public static boolean isLeapYear(int year) {
            return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        }

        public static void invertArray(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (arr[i] == 0) ? 1 : 0;
            }
        }

        public static int[] fillArray100() {
            int[] arr = new int[100];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1;
            }
            return arr;
        }

        public static void multiplyLessThanSix(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] < 6) {
                    arr[i] *= 2;
                }
            }
        }

        public static void printDiagonalMatrix(int size) {
            int[][] matrix = new int[size][size];
            for (int i = 0; i < size; i++) {
                matrix[i][i] = 1;
                matrix[i][size - 1 - i] = 1;
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        public static int[] createArray(int len, int initialValue) {
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = initialValue;
            }
            return arr;
        }
    }
