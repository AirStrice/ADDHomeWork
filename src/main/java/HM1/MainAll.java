public class MainAll {

    public static void main(String[] args) {
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Хлопушка", "15.03.2025", "FunToys", "Китай", 5.50, false);
        productsArray[1] = new Product("Мороженое", "10.08.2025", "ColdDelight", "Россия", 3.00, true);
        productsArray[2] = new Product("Воздушный шарик", "01.05.2025", "BalloonMagic", "Турция", 2.50, false);
        productsArray[3] = new Product("Хот-дог", "20.08.2025", "FastFood Inc.", "Беларусь", 4.50, false);
        productsArray[4] = new Product("Мягкая игрушка", "05.06.2025", "SoftToys", "Китай", 12.00, true);

        System.out.println("Товары");
        for (Product product : productsArray) {
            product.printInfo();
            System.out.println();
        }

        Park park = new Park("Центральный парк", 10);
        park.addAttraction("Карусель", "10:00 - 20:00", 15);
        park.addAttraction("Американские горки", "11:00 - 22:00", 25);
        park.addAttraction("Колесо обозрения", "09:00 - 23:00", 20);
        park.addAttraction("Бамперные машинки", "10:00 - 21:00", 10);

        System.out.println("Аттракционы");
        park.printAllAttractions();
    }
}