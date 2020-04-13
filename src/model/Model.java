package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public static String[] studentGroupData = {"SELECT", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    public static JComboBox<String> studentGroupCombobox = new JComboBox<>(studentGroupData);

    private List<Person> students = new ArrayList<>();//todo delete?

    public DefaultTableModel getStudentData(int groupNumber) {//todo make this get student data from given group, not all of them
//        DefaultTableModel model = new DefaultTableModel(new Object[]{"LP", "First Name", "Second Name", "Index", "Email", "Group"}, 0);
//        JCheckBox cb = new JCheckBox();
//        cb.setActionCommand("present");
//        cb.addActionListener();
        boolean isPresent = false;
        DefaultTableModel model = new DefaultTableModel(new Object[]{"LP", "First Name", "Second Name", "Index", "Email", "Group", isPresent}, 0);
        InsertApp app = new InsertApp();
        model = app.selectOneGroupStudentData(model, groupNumber);

        return model;
    }

    public void notifyStudentGroupComboBoxHasChanged(int selectedGroupNumber) {

    }

}