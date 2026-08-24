// ==================== Main.java ====================
public class Main {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Шарик");
        Cat catBarsik = new Cat("Мурзик");

        dogBobik.run(150);
        dogBobik.run(600);
        dogBobik.swim(5);
        dogBobik.swim(15);

        catBarsik.run(100);
        catBarsik.run(250);
        catBarsik.swim(10);

        Dog dogSharik = new Dog("Пес2");
        Dog dogTuzik = new Dog("Пес3");

        Cat[] cats = {
                new Cat("Кот2"),
                new Cat("Кот3"),
                new Cat("Кот4"),
                new Cat("Кот5")
        };

        System.out.println("\nКоты и миска");
        Bowl bowl = new Bowl(25);

        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }

        System.out.println("\nСытость котов");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        System.out.println("\nОсталось еды в миске: " + bowl.getFood());

        bowl.addFood(20);

        for (Cat cat : cats) {
            if (!cat.isFull()) {
                cat.eat(bowl, 10);
            }
        }

        System.out.println("\nИтоговая сытость");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        System.out.println("\nВсего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
    }
}