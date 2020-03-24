import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private JTextField fNameTextField;
    private JTextField lNameTextField;
    private JComboBox<StudentGroup> groupComboBox;

    public Controller(JTextField fNameTextField, JTextField lNameTextField, JComboBox<StudentGroup> groupComboBox) {
        this.fNameTextField = fNameTextField;
        this.lNameTextField = lNameTextField;
        this.groupComboBox = groupComboBox;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        if(action.equals("add")) {
            System.out.println("added: " + fNameTextField.getText() + " " + lNameTextField.getText());
        }
    }
}
