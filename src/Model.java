import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Model {

    public static String[] studentGroupData = {"SELECT", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    public static JComboBox<String> studentGroupCombobox = new JComboBox<>(studentGroupData);
    public DefaultTableModel getStudentData(int groupID) {
        return new DefaultTableModel();//todo make it to return model with data from database
    }
}
