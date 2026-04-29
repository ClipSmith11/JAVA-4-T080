public class FoodItem {

    private String name;
    private double protein;
    private double carbs;
    private double fats;
    private double calories;
    private double price;
    private boolean isVegetarian;

    public FoodItem(String name, double protein, double carbs, double fats, double calories, double price, boolean isVegetarian) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
        this.calories = calories;
        this.price = price;
        this.isVegetarian = isVegetarian;
    }

    public String getName() {
        return name;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    public double getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }
}