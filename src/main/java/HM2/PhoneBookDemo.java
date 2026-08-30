public class PhoneBookDemo {
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();

        pb.add("Иванов", "+7-900-111-22-33");
        pb.add("Петров", "+7-900-444-55-66");
        pb.add("Иванов", "+7-901-777-88-99");
        pb.add("Сидоров", "+7-902-000-11-22");

        System.out.println("Весь справочник");
        pb.printAll();

        System.out.println("\nПоиск через get()");
        pb.get("Иванов");
        pb.get("Петров");
        pb.get("Синичкин");
    }
}