package HM1;

public class MainAll {
    public static void main(String[] args) {

        System.out.println("Задание 1:");
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");

        System.out.println("\nЗадание 2:");
        int a1 = 5;
        int b1 = -10;
        if (a1 + b1 >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }

        System.out.println("\nЗадание 3:");
        int value = 50;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }

        System.out.println("\nЗадание 4:");
        int a2 = 10;
        int b2 = 5;
        if (a2 >= b2) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }

        System.out.println("\nЗадание 5:");
        int sum1 = 5 + 10;
        System.out.println(sum1 >= 10 && sum1 <= 20);
        int sum2 = 3 + 5;
        System.out.println(sum2 >= 10 && sum2 <= 20);

        System.out.println("\nЗадание 6:");
        int n1 = -5;
        if (n1 >= 0) {
            System.out.println(n1 + " - положительное");
        } else {
            System.out.println(n1 + " - отрицательное");
        }

        System.out.println("\nЗадание 7:");
        int n2 = -1;
        System.out.println(n2 < 0);

        System.out.println("\nЗадание 8:");
        String str = "Hello";
        int times = 3;
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }

        System.out.println("\nЗадание 9:");
        int year = 2024;
        System.out.println((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0));

        System.out.println("\nЗадание 10:");
        int[] binary = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (binary[i] == 0) ? 1 : 0;
        }
        for (int i = 0; i < binary.length; i++) {
            System.out.print(binary[i] + " ");
        }
        System.out.println();

        System.out.println("\nЗадание 11:");
        int[] hundred = new int[100];
        for (int i = 0; i < hundred.length; i++) {
            hundred[i] = i + 1;
        }
        for (int i = 0; i < hundred.length; i++) {
            System.out.print(hundred[i] + " ");
        }
        System.out.println();

        System.out.println("\nЗадание 12:");
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.println("\nЗадание 13:");
        int size = 5;
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

        System.out.println("\nЗадание 14:");
        int len = 5;
        int initialValue = 7;
        int[] filled = new int[len];
        for (int i = 0; i < len; i++) {
            filled[i] = initialValue;
        }
        for (int i = 0; i < filled.length; i++) {
            System.out.print(filled[i] + " ");
        }
        System.out.println();
    }
}
