import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Model {

    public static String[] studentGroupData = {"SELECT", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    public static JComboBox<String> studentGroupCombobox = new JComboBox<>(studentGroupData);

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

}
//todo make table active on click, fe. when user clicks on row, popup window is displayed and user can define whether student is present or not