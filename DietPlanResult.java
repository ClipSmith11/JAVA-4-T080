import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DietPlanResult {
    private final List<FoodItem> selectedFoods;
    private final double totalProtein;
    private final double totalCarbs;
    private final double totalFats;
    private final double totalSpent;
    private final double budget;
    private final double targetProtein;
    private final double targetCarbs;
    private final double targetFats;

    public DietPlanResult(
            List<FoodItem> selectedFoods,
            double totalProtein,
            double totalCarbs,
            double totalFats,
            double totalSpent,
            double budget,
            double targetProtein,
            double targetCarbs,
            double targetFats) {
        this.selectedFoods = new ArrayList<>(selectedFoods);
        this.totalProtein = totalProtein;
        this.totalCarbs = totalCarbs;
        this.totalFats = totalFats;
        this.totalSpent = totalSpent;
        this.budget = budget;
        this.targetProtein = targetProtein;
        this.targetCarbs = targetCarbs;
        this.targetFats = targetFats;
    }

    public List<FoodItem> getSelectedFoods() {
        return Collections.unmodifiableList(selectedFoods);
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getTotalFats() {
        return totalFats;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public double getBudget() {
        return budget;
    }

    public double getRemainingBudget() {
        return budget - totalSpent;
    }

    public double getTargetProtein() {
        return targetProtein;
    }

    public double getTargetCarbs() {
        return targetCarbs;
    }

    public double getTargetFats() {
        return targetFats;
    }

    public boolean hasFoods() {
        return !selectedFoods.isEmpty();
    }
}
