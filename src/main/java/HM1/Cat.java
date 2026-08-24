public class Cat extends Animal {
    private boolean isFull = false;
    private static int catCount = 0;

    public Cat(String name) {
        super(name, 200, 0);
        catCount++;
    }

    public void eat(Bowl bowl, int amount) {
        if (isFull) {
            System.out.println(name + " уже сыт.");
            return;
        }
        if (bowl.decreaseFood(amount)) {
            isFull = true;
            System.out.println(name + " съел " + amount + " ед. еды и теперь сыт.");
        } else {
            System.out.println(name + " не стал есть — еды в миске недостаточно.");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}
