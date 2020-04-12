package controller;

import view.LoginForm;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.LoginForm.isPasswordCorrect;

public class LogInController implements ActionListener { //todo check if login is correct, checks from database

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        LoginForm loginForm = new LoginForm();
        if(actionEvent.getActionCommand().equals("password")) {
            char[] enteredPassword = loginForm.getPasswordField().getPassword();
            if (isPasswordCorrect(enteredPassword)) {
                View.login = loginForm.getLoginField().getText();
                JOptionPane.showMessageDialog(new JFrame(), "Success");
                new View();//todo this should be in the LoginForm class
                loginForm.dispose();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Failure");
            }
        }
    }
}