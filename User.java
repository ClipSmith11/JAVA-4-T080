public class User {

    private String name;
    private int age;
    private double weight;
    private double height;
    private String gender;
    private String goal;
    private boolean isVegetarian;

    public User(String name, int age, double weight, double height, String gender, String goal, boolean isVegetarian) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.goal = goal;
        this.isVegetarian = isVegetarian;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public String getGoal() {
        return goal;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }
}