import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private static final Dimension windowDimension = new Dimension(800,800);
    private JTabbedPane mainPane;
    private JComboBox<String> groupComboBox;
    private JTextField textField1;
    private JTextField fNameTextField;
    private JButton logOutButton;
    private JButton addButton;
    private JTextField lNameTextField;
    private JComboBox<String> groupComboBoxDelete;// todo change to jtextfield
    private JTextField studentIndexTextField;
    private JTextField studentEmailTextField;

    public View() {
        setSize(windowDimension);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPane);
        setLocationRelativeTo(null);
        setVisible(true);

        logOutButton.addActionListener(actionEvent -> {
            if (actionEvent.getActionCommand().equals("logout")){
                dispose();
                new LoginForm();
            }
        });


        Controller controllerActionListener = new Controller(fNameTextField, lNameTextField, groupComboBox);
        addButton.addActionListener(controllerActionListener);
        groupComboBox.addActionListener(controllerActionListener);
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

}
