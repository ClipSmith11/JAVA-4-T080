import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Using default look and feel.");
        }

        SwingUtilities.invokeLater(() -> {
            MacroPlannerUI ui = new MacroPlannerUI();
            ui.setVisible(true);
        });
    }
}
