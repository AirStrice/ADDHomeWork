public class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = Math.max(0, food);
    }

    public boolean decreaseFood(int amount) {
        if (amount <= 0) return false;
        if (food >= amount) {
            food -= amount;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("В миску наложили " + amount + " ед. еды. Теперь в миске: " + food);
        }
    }

    public int getFood() {
        return food;
    }
}