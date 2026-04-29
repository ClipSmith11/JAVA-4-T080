public class BMRCalculator {

    private double bmr;
    private double tdee;
    private double dailyCaloriesNeeded;
    private double protein;
    private double carbs;
    private double fats;
    private String goal;
    private double userWeight;

    public BMRCalculator(User user) {
        this.goal = user.getGoal();
        this.userWeight = user.getWeight();

        calculateBMR(user);
        this.tdee = this.bmr * 1.5;
        adjustCaloriesForGoal();
        calculateMacros();
    }

    private void calculateBMR(User user) {
        double weight = user.getWeight();
        double height = user.getHeight();
        int age = user.getAge();

        if (user.getGender().equalsIgnoreCase("male")) {
            this.bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            this.bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
    }

    private void adjustCaloriesForGoal() {
        if (goal.equalsIgnoreCase("cut")) {
            this.dailyCaloriesNeeded = this.tdee - 500;
        } else if (goal.equalsIgnoreCase("bulk")) {
            this.dailyCaloriesNeeded = this.tdee + 500;
        } else {
            this.dailyCaloriesNeeded = this.tdee;
        }
    }

    private void calculateMacros() {
        if (goal.equalsIgnoreCase("bulk")) {
            this.protein = userWeight * 1.6;
        } else if (goal.equalsIgnoreCase("cut")) {
            this.protein = userWeight * 1.2;
        } else {
            this.protein = userWeight * 0.8;
        }

        this.fats = (this.dailyCaloriesNeeded * 0.25) / 9.0;
        this.carbs = (this.dailyCaloriesNeeded - (protein * 4) - (fats * 9)) / 4.0;
    }

    public double getBMR() {
        return bmr;
    }

    public double getTDEE() {
        return tdee;
    }

    public double getDailyCaloriesNeeded() {
        return dailyCaloriesNeeded;
    }

    public double getCalories() {
        return dailyCaloriesNeeded;
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
}