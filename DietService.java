import java.util.*;

public class DietService {

    public static DietPlanResult generateDietPlan(User user, double budget, BMRCalculator calculator) {
        List<FoodItem> foodList = FoodDatabase.getFoodList();

        double remainingProtein = calculator.getProtein();
        double remainingCarbs = calculator.getCarbs();
        double remainingFats = calculator.getFats();
        double remainingBudget = budget;

        double totalProtein = 0;
        double totalCarbs = 0;
        double totalFats = 0;
        double totalSpent = 0;

        List<FoodItem> selectedFoods = new ArrayList<>();

        for (FoodItem food : foodList) {

            if (user.isVegetarian() && !food.isVegetarian()) {
                continue;
            }

            if (food.getPrice() > remainingBudget) {
                continue;
            }

            if (food.getProtein() <= remainingProtein &&
                food.getCarbs() <= remainingCarbs &&
                food.getFats() <= remainingFats) {

                selectedFoods.add(food);

                remainingProtein -= food.getProtein();
                remainingCarbs -= food.getCarbs();
                remainingFats -= food.getFats();
                remainingBudget -= food.getPrice();

                totalProtein += food.getProtein();
                totalCarbs += food.getCarbs();
                totalFats += food.getFats();
                totalSpent += food.getPrice();
            }
        }

        return new DietPlanResult(
                selectedFoods,
                totalProtein,
                totalCarbs,
                totalFats,
                totalSpent,
                budget,
                calculator.getProtein(),
                calculator.getCarbs(),
                calculator.getFats());
    }

    public static void generateDiet(User user, double budget, BMRCalculator calculator) {
        DietPlanResult result = generateDietPlan(user, budget, calculator);

        System.out.println("\n--- Diet Plan ---");

        if (!result.hasFoods()) {
            System.out.println("No diet could be generated within your budget.");
            return;
        }

        System.out.println("\nRecommended Foods:");
        for (FoodItem food : result.getSelectedFoods()) {
            System.out.printf("- %s (P:%.1fg | C:%.1fg | F:%.1fg)%n", 
                food.getName(), food.getProtein(), food.getCarbs(), food.getFats());
        }

        System.out.println("\n--- Macro Summary ---");
        System.out.printf("Protein: %.2f / %.2f g%n", result.getTotalProtein(), result.getTargetProtein());
        System.out.printf("Carbs: %.2f / %.2f g%n", result.getTotalCarbs(), result.getTargetCarbs());
        System.out.printf("Fats: %.2f / %.2f g%n", result.getTotalFats(), result.getTargetFats());

        System.out.println("\n--- Budget ---");
        System.out.printf("Total: Rs. %.2f | Spent: Rs. %.2f | Remaining: Rs. %.2f%n", 
            result.getBudget(), result.getTotalSpent(), result.getRemainingBudget());
    }
}
