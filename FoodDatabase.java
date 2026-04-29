import java.util.*;

public class FoodDatabase {

    public static List<FoodItem> getFoodList() {

        List<FoodItem> foodList = new ArrayList<>();

        foodList.add(new FoodItem("Rice",      2.7,   28.0,  0.3,   130,  10, true));
        foodList.add(new FoodItem("Roti",      3.7,   20.0,  1.0,   120,   8, true));
        foodList.add(new FoodItem("Dal",       9.0,   20.0,  1.0,   150,  15, true));
        foodList.add(new FoodItem("Paneer",   18.0,    5.0, 20.0,   265,  50, true));
        foodList.add(new FoodItem("Milk",      6.4,    5.0,  3.3,   100,  20, true));
        foodList.add(new FoodItem("Banana",    1.1,   27.0,  0.3,   105,   5, true));
        foodList.add(new FoodItem("Oats",     13.0,   68.0,  7.0,   389,  20, true));
        foodList.add(new FoodItem("Egg",       6.0,    1.0,  5.0,    70,   7, false));
        foodList.add(new FoodItem("Chicken",  25.0,    0.0,  5.0,   200,  60, false));
        foodList.add(new FoodItem("Fish",     24.0,    0.0,  3.0,   140,  80, false));

        return foodList;
    }
}