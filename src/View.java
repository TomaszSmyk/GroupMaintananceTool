import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private static final Dimension windowDimension = new Dimension(800,800);
    private JTabbedPane mainPane;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField fNameTextField;
    private JButton logOutButton;
    private JButton addButton;
    private JTextField lNameTextField;
    private JComboBox<StudentGroup> groupComboBox;

    public View() {
        setSize(windowDimension);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mainPane);
        setLocationRelativeTo(null);
        setVisible(true);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getActionCommand().equals("logout")){
                    dispose();
                    new LoginForm();
                }
            }
        });

        addButton.addActionListener(new Controller(fNameTextField, lNameTextField, groupComboBox));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private static void createAndShowGUI() throws Exception{
        new LoginForm();
//        new View();
    }

}
