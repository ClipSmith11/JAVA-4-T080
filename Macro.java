public class Macro {
    private double protein;
    private double carbs;
    private double fats;

    public Macro(double protein, double carbs, double fats) {
        this.protein = protein;
        this.carbs = carbs;
        this.fats = fats;
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

    public void displayMacros() {
        System.out.printf("Protein: %.2f g%n", protein);
        System.out.printf("Carbs: %.2f g%n", carbs);
        System.out.printf("Fats: %.2f g%n", fats);
    }
}
