public class MainAll {

    public static void main(String[] args) {
        Products[] productsArray = new Products[5];

        productsArray[0] = new Products("Хлопушка", "15.03.2025", "FunToys", "Китай", 5.50, false);
        productsArray[1] = new Products("Мороженое", "10.08.2025", "ColdDelight", "Россия", 3.00, true);
        productsArray[2] = new Products("Воздушный шарик", "01.05.2025", "BalloonMagic", "Турция", 2.50, false);
        productsArray[3] = new Products("Хот-дог", "20.08.2025", "FastFood Inc.", "Беларусь", 4.50, false);
        productsArray[4] = new Products("Мягкая игрушка", "05.06.2025", "SoftToys", "Китай", 12.00, true);

        System.out.println("=== Товары ===");
        for (Products product : productsArray) {
            product.printInfo();
            System.out.println();
        }

        Park park = new Park("Центральный парк");
        Park.Attraction carousel = park.new Attraction("Карусель", "10:00 - 20:00", 15);
        Park.Attraction rollerCoaster = park.new Attraction("Американские горки", "11:00 - 22:00", 25);
        Park.Attraction ferrisWheel = park.new Attraction("Колесо обозрения", "09:00 - 23:00", 20);
        Park.Attraction bumperCars = park.new Attraction("Бамперные машинки", "10:00 - 21:00", 10);

        System.out.println("=== Аттракционы ===");
        carousel.printInfo();
        System.out.println();
        rollerCoaster.printInfo();
        System.out.println();
        ferrisWheel.printInfo();
        System.out.println();
        bumperCars.printInfo();
    }
}