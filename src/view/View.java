package view;

import controller.AddController;
import controller.Controller;
import controller.PresenceController;
import model.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {//todo do not extend jframe
    private static final Dimension windowDimension = new Dimension(800,800);
    private JTabbedPane mainPane;
    public JComboBox<String> groupComboBox;
    private JTextField fNameTextField;
    private JButton logOutButton;
    private JButton addButton;
    private JTextField lNameTextField;
    private JComboBox<String> groupComboBoxDelete;// todo change to jtextfield, or rather delete it completly and use controller.Controller's getGroupNumber method
    private JTextField indexAddTextField;
    private JTextField emailAddTextField;
    private JTable studentTable;
    private JLabel loginField;
    private JPanel presenceTab;//todo set it to custom create and define behaviour/content/ in createUIComponents -> add checkboxes etc.

    public static String login = "";//todo insecure

    public View() {
        setSize(windowDimension);//todo unnecessary, let jframe do it by itself
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPane);
        setLocationRelativeTo(null);
        setVisible(true);
        updatePresenceData();
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
//        groupComboBox.setSelectedIndex(controller.Controller.getGroupNumber());
        groupComboBox.addActionListener(controllerActionListener);
    }

    public View(boolean update) {//todo make sure it works with login, fe if u can still display logged person in home tab after updating, also, is there any way to make it smoother,
        //todo REFACTOR THIS __________IMPORTANT______________
        refreshTable();
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
        //todo make it fill presence tab with students name, align it nicely -ish
    }

    public void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.getDataVector().removeAllElements();
        studentTable.setModel(new Model().getStudentData(Controller.getGroupNumber()));//todo change getgroup number
    }

    public void updatePresenceData() {//todo transfer this into another separate controller class
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
        studentTable.setModel(new Model().getStudentData(Controller.getGroupNumber()));
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isPresent = false;
                if(JOptionPane.showConfirmDialog(null, "Is student present", "Student Presence Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    isPresent = true;
                } else {
                    isPresent = false;
                }
                JTable sourceTable = (JTable) e.getSource();
                int rowSelected = sourceTable.rowAtPoint(e.getPoint());
//                int columnSelected = sourceTable.columnAtPoint(e.getPoint());
                sourceTable.setValueAt(isPresent, rowSelected, 6);
                int groupId = Integer.parseInt(sourceTable.getValueAt(rowSelected, 5).toString());
                System.out.println("groupId: " + groupId);
                int studentId = Integer.parseInt(sourceTable.getValueAt(rowSelected, 0).toString());
                System.out.println("studentId: " + studentId);
                new PresenceController(isPresent, groupId, studentId);
            }
        });
    }
}
