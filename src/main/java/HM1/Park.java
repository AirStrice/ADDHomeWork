public class Park {

    private String parkName;
    private Attraction[] attractions;
    private int attractionCount;

    public Park(String parkName, int maxAttractions) {
        this.parkName = parkName;
        this.attractions = new Attraction[maxAttractions];
        this.attractionCount = 0;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public void addAttraction(String name, String workingHours, double price) {
        if (attractionCount < attractions.length) {
            attractions[attractionCount] = new Attraction(name, workingHours, price);
            attractionCount++;
        } else {
            System.out.println("Нет места для нового аттракциона!");
        }
    }

    public void printAllAttractions() {
        System.out.println("Аттракционы \"" + parkName + "\":");
        for (int i = 0; i < attractionCount; i++) {
            attractions[i].printInfo();
            System.out.println();
        }
    }

    public class Attraction {

        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWorkingHours() {
            return workingHours;
        }

        public void setWorkingHours(String workingHours) {
            this.workingHours = workingHours;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price);
        }
    }
}