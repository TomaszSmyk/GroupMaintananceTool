import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                view.View.createAndShowUI();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}

//todo make rows that was selected green