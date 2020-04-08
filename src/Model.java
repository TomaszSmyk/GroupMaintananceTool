import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Model {

    public static String[] studentGroupData = {"SELECT", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    public static JComboBox<String> studentGroupCombobox = new JComboBox<>(studentGroupData);

    public DefaultTableModel getStudentData() {//todo make this get student data from given group, not all of them
        DefaultTableModel model = new DefaultTableModel(new String[]{"LP", "First Name", "Second Name", "Index", "Email", "Group"}, 0);
        InsertApp app = new InsertApp();
        model = app.selectAllStudentData(model);

        return model;
    }

}
