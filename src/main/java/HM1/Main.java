public class Main {

    public static int processArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr == null || arr.length != 4) {
            throw new MyArraySizeException("Массив должен быть 4x4. Фактический размер: "
                    + (arr == null ? "null" : arr.length));
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || arr[i].length != 4) {
                throw new MyArraySizeException("Строка " + i + " имеет размер "
                        + (arr[i] == null ? "null" : arr[i].length) + " вместо 4");
            }
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(
                            "Неверные данные в ячейке [" + i + "][" + j + "]: '" + arr[i][j] + "'"
                    );
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[][] correct = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            System.out.println("Сумма: " + processArray(correct));
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        String[][] wrongSize = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };
        try {
            System.out.println("Сумма: " + processArray(wrongSize));
        } catch (Exception e) {
            System.err.println("Ошибка размера: " + e.getMessage());
        }

        String[][] wrongData = {
                {"1", "2", "3", "4"},
                {"5", "6", "abc", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            System.out.println("Сумма: " + processArray(wrongData));
        } catch (Exception e) {
            System.err.println("Ошибка данных: " + e.getMessage());
        }

        try {
            ArrayExceptionDemo.generateError();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Поймано в main: " + e.getMessage());
        }
    }
}