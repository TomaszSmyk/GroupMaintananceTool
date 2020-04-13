package controller;

import model.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiPredicate;

public class Controller implements ActionListener {
    private JTextField fNameTextField;
    private JTextField lNameTextField;
    private JComboBox<String> groupComboBox;
    private String groupNumberChosen = "";//todo do I use it?


    public Controller(JTextField fNameTextField, JTextField lNameTextField, JComboBox<String> groupComboBox) {
        this.fNameTextField = fNameTextField;
        this.lNameTextField = lNameTextField;
        this.groupComboBox = groupComboBox;
        updateStudentGroups();
    }
    public Controller() { //todo is it necessary?
        //do nothing
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();

        BiPredicate<String, String> compare = Object::equals;
        if(compare.test(action, "add")) {
            System.out.println("added: " + fNameTextField.getText() + " " + lNameTextField.getText());

        }

        if(compare.test(action, "groupNumberChanged")) {//todo for what is it here?

            JComboBox temp = (JComboBox) actionEvent.getSource();

            groupNumberChosen = (String) temp.getSelectedItem();
//            new Model().getStudentData(getGroupNumber());
            studentGroupComboBoxHasChanged();
            //todo update presence
            //todo update add
            //todo update delete
            //todo update chart
        }
    }

    private void updateStudentGroups() {
        groupComboBox.removeAllItems();
        for(String element: Model.studentGroupData) {
            groupComboBox.addItem(element);
        }
        groupComboBox.setSelectedIndex(getGroupNumber());

    }
    private void studentGroupComboBoxHasChanged() {
        new Model().notifyStudentGroupComboBoxHasChanged(getGroupNumber());
    }
    public static int getGroupNumber() {//todo move it to Model class, refactor this
        if (Model.studentGroupCombobox.getSelectedItem() == null) {
            Object[] groupToChose = Model.studentGroupData;
            int groupNum = Integer.parseInt((String) JOptionPane.showInputDialog(new JFrame(), "Please chose student group:", "Group number", JOptionPane.PLAIN_MESSAGE, null, groupToChose, "0"));
            System.out.println("Group number chosen: " + groupNum);
//            new controller.Controller().groupComboBox.setSelectedIndex(groupNum);
            return groupNum;
        } else if (Model.studentGroupCombobox.getSelectedItem().equals("SELECT")) {
            Object[] groupToChose = Model.studentGroupData;
            int groupNum = Integer.parseInt((String) JOptionPane.showInputDialog(new JFrame(), "Please chose student group:", "Group number", JOptionPane.PLAIN_MESSAGE, null, groupToChose, "0"));
            System.out.println("Group number chosen: " + groupNum);
//            new controller.Controller().groupComboBox.setSelectedIndex(groupNum);
            return groupNum;
        }
        else {
            return Integer.parseInt((String) Model.studentGroupCombobox.getSelectedItem());
        }
    }


}