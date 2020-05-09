package controller;


import model.Database;
import model.Model;
import model.Person;
import model.Student;
import view.Command;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Controller implements ActionListener {
    private int groupSelected = 1;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        Controller controller = new Controller();
        if(action.equals(Command.ADD.toString())){
            System.out.println("ADDING");
            String fName = View.addFirstNameTextField.getText();
            String lName = View.addLastNameTextField.getText();
            int index = Integer.parseInt(View.addIndexTextField.getText());
            int groupNumber = Integer.parseInt(View.addGroupTextField.getText());
            String email = View.addEmailTextField.getText();
            Student student = new Student.Builder().firstName(fName).lastName(lName).index(index).groupNumber(groupNumber).email(email).build();
            System.out.println("jsadljal" + Arrays.toString(student.getData()));
            controller.notifyStudentAdded();
            Database db = new Database();
            db.insertStudent(student.getFirstName(), student.getLastName(), student.getIndex(), student.getGroupNumber(), student.getEmail());
            new Model(student.getGroupNumber());
        }
        else if (action.equals(Command.GROUP_NUMBER_CHANGED.toString())) {
            JComboBox<Integer> jcb = (JComboBox)actionEvent.getSource();
            groupSelected = (Integer)jcb.getSelectedItem();
            new Model(groupSelected);
        }
    }
    private void notifyStudentAdded() {
        new Model(this.groupSelected);
    }
}