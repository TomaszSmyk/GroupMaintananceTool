package view;

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
    private static final char[] CORRECT_PASSWORD = {'a', 'b', 'c'};
    private final int WIDTH = 250;
    private final int HEIGHT = 400;
    private final boolean IS_RESIZABLE = false;

    public LoginForm() {
        LogInController logInController = new LogInController();
        passwordField.addActionListener(logInController);
        logInButton.addActionListener(logInController);
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(IS_RESIZABLE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPane);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static boolean isPasswordCorrect(char[] enteredPassword) {
        boolean isCorrect;
        if(enteredPassword.length != CORRECT_PASSWORD.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals(enteredPassword, CORRECT_PASSWORD);
        }
        return isCorrect;
    }

    private class LogInController implements ActionListener{ //todo check if login is correct, checks from database
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getActionCommand().equals("password")) {
                char[] enteredPassword = passwordField.getPassword();
                if (isPasswordCorrect(enteredPassword)) {
                    View.login = loginField.getText();
                    JOptionPane.showMessageDialog(new JFrame(), "Success");
                    new View();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "Failure");
                }
            }
        }
    }
    public JTextField getLoginField() {
        return loginField;
    }
    public JPasswordField getPasswordField() {
        return passwordField;
    }
}
