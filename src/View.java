import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class View extends JFrame {
    private static final Dimension windowDimension = new Dimension(800,800);
    private JTabbedPane mainPane;
    public JComboBox<String> groupComboBox;
    private JTextField fNameTextField;
    private JButton logOutButton;
    private JButton addButton;
    private JTextField lNameTextField;
    private JComboBox<String> groupComboBoxDelete;// todo change to jtextfield
    private JTextField indexAddTextField;
    private JTextField emailAddTextField;
    private JTable studentTable;
    private JLabel loginField;

    public static String login = "";//todo insecure

    public View() {
        setSize(windowDimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPane);
        setLocationRelativeTo(null);
        setVisible(true);
        updatePresenceData();
        System.out.println("Login: " + login);
        loginField.setText(login);

        logOutButton.addActionListener(actionEvent -> {
            if (actionEvent.getActionCommand().equals("logout")){
                dispose();
                new LoginForm();
            }
        });

        addButton.addActionListener(new AddController(fNameTextField, lNameTextField, indexAddTextField, emailAddTextField));//todo refactor

        Controller controllerActionListener = new Controller(fNameTextField, lNameTextField, groupComboBox);//todo refactor
//        addButton.addActionListener(controllerActionListener);
        groupComboBox.addActionListener(controllerActionListener);
    }

    public View(boolean update) {//todo make sure it works with login, fe if u can still display logged person in home tab after updating, also, is there any way to make it smoother,
        //todo REFACTOR THIS __________IMPORTANT______________
        dispose();
        new View();
    }

    public View(String login) {
        this.login = login;
        new View();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private static void createAndShowGUI() throws Exception{
        new LoginForm();
//        new View();
    }

    private void createUIComponents() {
        groupComboBox = Model.studentGroupCombobox;
    }

    public void updatePresenceData() {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
        studentTable.setModel(new Model().getStudentData());
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
