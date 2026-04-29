import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MacroPlannerUI extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField weightField;
    private JTextField heightField;
    private JTextField budgetField;
    private JComboBox<String> genderCombo;
    private JComboBox<String> goalCombo;
    private JCheckBox vegetarianCheck;

    private JLabel bmrValueLabel;
    private JLabel tdeeValueLabel;
    private JLabel caloriesValueLabel;
    private JLabel proteinValueLabel;
    private JLabel carbsValueLabel;
    private JLabel fatsValueLabel;
    private JLabel budgetValueLabel;

    private JTextArea messageArea;
    private DefaultTableModel tableModel;

    public MacroPlannerUI() {
        setTitle("Macro Planner Dashboard");
        setSize(980, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(16, 16));

        Color background = new Color(245, 247, 250);
        getContentPane().setBackground(background);

        ((JPanel) getContentPane()).setBorder(new EmptyBorder(16, 16, 16, 16));

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFormPanel(), BorderLayout.WEST);
        add(createResultPanel(), BorderLayout.CENTER);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel titleLabel = new JLabel("Nutrition and Macro Planner");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(28, 63, 98));

        JLabel subtitleLabel = new JLabel("Simple Swing frontend for BMR, calories, macros, and budget diet planning");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitleLabel.setForeground(new Color(90, 100, 110));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(subtitleLabel);

        panel.add(textPanel, BorderLayout.WEST);
        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(320, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                new EmptyBorder(16, 16, 16, 16)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel formTitle = new JLabel("User Details", SwingConstants.CENTER);
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(formTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        nameField = new JTextField();
        addFormRow(panel, gbc, "Name", nameField);

        gbc.gridy += 2;
        ageField = new JTextField();
        addFormRow(panel, gbc, "Age", ageField);

        gbc.gridy += 2;
        weightField = new JTextField();
        addFormRow(panel, gbc, "Weight (kg)", weightField);

        gbc.gridy += 2;
        heightField = new JTextField();
        addFormRow(panel, gbc, "Height (cm)", heightField);

        gbc.gridy += 2;
        genderCombo = new JComboBox<>(new String[] {"Male", "Female"});
        addFormRow(panel, gbc, "Gender", genderCombo);

        gbc.gridy += 2;
        goalCombo = new JComboBox<>(new String[] {"Cut", "Maintain", "Bulk"});
        addFormRow(panel, gbc, "Goal", goalCombo);

        gbc.gridy += 2;
        budgetField = new JTextField();
        addFormRow(panel, gbc, "Budget (Rs)", budgetField);

        gbc.gridy += 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        vegetarianCheck = new JCheckBox("Vegetarian Diet");
        vegetarianCheck.setOpaque(false);
        vegetarianCheck.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(vegetarianCheck, gbc);

        gbc.gridy++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        buttonPanel.setOpaque(false);

        JButton calculateButton = new JButton("Generate Plan");
        calculateButton.setBackground(new Color(35, 122, 87));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.addActionListener(e -> generatePlan());

        JButton clearButton = new JButton("Clear");
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> clearForm());

        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setOpaque(false);

        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 230)),
                new EmptyBorder(16, 16, 16, 16)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 18);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        bmrValueLabel = addStatRow(statsPanel, gbc, "BMR");
        tdeeValueLabel = addStatRow(statsPanel, gbc, "TDEE");
        caloriesValueLabel = addStatRow(statsPanel, gbc, "Daily Calories");
        proteinValueLabel = addStatRow(statsPanel, gbc, "Protein");
        carbsValueLabel = addStatRow(statsPanel, gbc, "Carbs");
        fatsValueLabel = addStatRow(statsPanel, gbc, "Fats");
        budgetValueLabel = addStatRow(statsPanel, gbc, "Budget Summary");

        panel.add(statsPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[] {"Food", "Protein (g)", "Carbs (g)", "Fats (g)", "Calories", "Price (Rs)", "Type"},
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable foodTable = new JTable(tableModel);
        foodTable.setRowHeight(24);
        foodTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane tableScrollPane = new JScrollPane(foodTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Recommended Foods"));

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messageArea.setText("Enter user details and click Generate Plan to view the diet summary.");

        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        messageScrollPane.setPreferredSize(new Dimension(100, 110));
        messageScrollPane.setBorder(BorderFactory.createTitledBorder("Plan Notes"));

        JPanel lowerPanel = new JPanel(new BorderLayout(12, 12));
        lowerPanel.setOpaque(false);
        lowerPanel.add(tableScrollPane, BorderLayout.CENTER);
        lowerPanel.add(messageScrollPane, BorderLayout.SOUTH);

        panel.add(lowerPanel, BorderLayout.CENTER);
        return panel;
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, String labelText, java.awt.Component component) {
        gbc.gridx = 0;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(label, gbc);

        gbc.gridx = 1;
        component.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(component, gbc);
    }

    private JLabel addStatRow(JPanel panel, GridBagConstraints gbc, String labelText) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panel.add(label, gbc);

        gbc.gridx = 1;
        JLabel valueLabel = new JLabel("--");
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(valueLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        return valueLabel;
    }

    private void generatePlan() {
        try {
            String name = nameField.getText().trim();
            int age = parseInteger(ageField.getText().trim(), "Age", 1, 120);
            double weight = parseDouble(weightField.getText().trim(), "Weight", 1, 500);
            double height = parseDouble(heightField.getText().trim(), "Height", 50, 300);
            double budget = parseDouble(budgetField.getText().trim(), "Budget", 1, 100000);

            if (name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty.");
            }

            String gender = genderCombo.getSelectedItem().toString();
            String goal = goalCombo.getSelectedItem().toString().toLowerCase();

            User user = new User(name, age, weight, height, gender, goal, vegetarianCheck.isSelected());
            BMRCalculator calculator = new BMRCalculator(user);
            DietPlanResult result = DietService.generateDietPlan(user, budget, calculator);

            updateStats(calculator, result);
            updateFoodTable(result);
            updateMessage(user, result);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int parseInteger(String value, String fieldName, int min, int max) {
        try {
            int number = Integer.parseInt(value);
            if (number < min || number > max) {
                throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max + ".");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid number for " + fieldName + ".");
        }
    }

    private double parseDouble(String value, String fieldName, double min, double max) {
        try {
            double number = Double.parseDouble(value);
            if (number < min || number > max) {
                throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max + ".");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid number for " + fieldName + ".");
        }
    }

    private void updateStats(BMRCalculator calculator, DietPlanResult result) {
        bmrValueLabel.setText(String.format("%.0f cal/day", calculator.getBMR()));
        tdeeValueLabel.setText(String.format("%.0f cal/day", calculator.getTDEE()));
        caloriesValueLabel.setText(String.format("%.0f cal/day", calculator.getDailyCaloriesNeeded()));
        proteinValueLabel.setText(String.format("%.2f g", calculator.getProtein()));
        carbsValueLabel.setText(String.format("%.2f g", calculator.getCarbs()));
        fatsValueLabel.setText(String.format("%.2f g", calculator.getFats()));
        budgetValueLabel.setText(String.format("Spent Rs %.2f / Remaining Rs %.2f",
                result.getTotalSpent(), result.getRemainingBudget()));
    }

    private void updateFoodTable(DietPlanResult result) {
        tableModel.setRowCount(0);

        for (FoodItem food : result.getSelectedFoods()) {
            tableModel.addRow(new Object[] {
                    food.getName(),
                    String.format("%.1f", food.getProtein()),
                    String.format("%.1f", food.getCarbs()),
                    String.format("%.1f", food.getFats()),
                    String.format("%.0f", food.getCalories()),
                    String.format("%.2f", food.getPrice()),
                    food.isVegetarian() ? "Veg" : "Non-Veg"
            });
        }
    }

    private void updateMessage(User user, DietPlanResult result) {
        StringBuilder builder = new StringBuilder();
        builder.append("Hello ").append(user.getName()).append(", your plan is ready.\n\n");

        if (!result.hasFoods()) {
            builder.append("No diet could be generated within the selected budget. ");
            builder.append("Try increasing the budget or changing the food preference.");
            messageArea.setText(builder.toString());
            return;
        }

        builder.append(String.format("Selected foods: %d%n", result.getSelectedFoods().size()));
        builder.append(String.format("Protein covered: %.2f / %.2f g%n",
                result.getTotalProtein(), result.getTargetProtein()));
        builder.append(String.format("Carbs covered: %.2f / %.2f g%n",
                result.getTotalCarbs(), result.getTargetCarbs()));
        builder.append(String.format("Fats covered: %.2f / %.2f g%n",
                result.getTotalFats(), result.getTargetFats()));
        builder.append(String.format("Budget used: Rs %.2f out of Rs %.2f%n",
                result.getTotalSpent(), result.getBudget()));

        if (user.isVegetarian()) {
            builder.append("\nVegetarian filter is active.");
        } else {
            builder.append("\nBoth vegetarian and non-vegetarian food items are available.");
        }

        messageArea.setText(builder.toString());
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        weightField.setText("");
        heightField.setText("");
        budgetField.setText("");
        genderCombo.setSelectedIndex(0);
        goalCombo.setSelectedIndex(0);
        vegetarianCheck.setSelected(false);

        bmrValueLabel.setText("--");
        tdeeValueLabel.setText("--");
        caloriesValueLabel.setText("--");
        proteinValueLabel.setText("--");
        carbsValueLabel.setText("--");
        fatsValueLabel.setText("--");
        budgetValueLabel.setText("--");

        tableModel.setRowCount(0);
        messageArea.setText("Enter user details and click Generate Plan to view the diet summary.");
    }
}
