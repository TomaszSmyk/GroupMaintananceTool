import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginForm extends JFrame {

    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JPanel mainPane;
    private static char[] correctPassword = {'a', 'b', 'c'};

    public LoginForm() {
        LogInController logInController = new LogInController();
        passwordField1.addActionListener(logInController);
        logInButton.addActionListener(logInController);
        setSize(new Dimension(250, 450));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class LogInController implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getActionCommand().equals("password")) {
                char[] enteredPassword = passwordField1.getPassword();
                if (isPasswordCorrect(enteredPassword)) {
                    JOptionPane.showMessageDialog(new JFrame(), "Success");
                    new View();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Failure");
                }
            }
        }
    }
    private static boolean isPasswordCorrect(char[] enteredPassword) {
        boolean isCorrect;
        if(enteredPassword.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(enteredPassword, correctPassword);
        }
        return isCorrect;
    }
}
