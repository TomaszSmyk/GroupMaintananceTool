import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginForm extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JPanel mainPane;
    private static char[] correctPassword = {'a', 'b', 'c'};
//    public String login; //todo make it more secure

    public LoginForm() {
        LogInController logInController = new LogInController();
        passwordField.addActionListener(logInController);
        logInButton.addActionListener(logInController);
        setSize(new Dimension(250, 450));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class LogInController implements ActionListener{ //todo check if login is correct, checks from database
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getActionCommand().equals("password")) {
                char[] enteredPassword = passwordField.getPassword();
                if (isPasswordCorrect(enteredPassword)) {
                    View.login = loginField.getText();
                    System.out.println("Log: " + View.login);
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
