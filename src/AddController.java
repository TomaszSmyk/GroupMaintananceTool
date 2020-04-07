import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiPredicate;

public class AddController implements ActionListener {
    private JTextField fNameTextField;
    private JTextField lNameTextField;
    private JTextField indexAddTextField;
    private JTextField emailAddTextField;

    public AddController(JTextField fNameTextField, JTextField lNameTextField, JTextField indexAddTextField, JTextField emailAddTextField) {
        this.fNameTextField = fNameTextField;
        this.lNameTextField = lNameTextField;
        this.indexAddTextField = indexAddTextField;
        this.emailAddTextField = emailAddTextField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        InsertApp studentDB = new InsertApp();
        BiPredicate<String, String> compare = Object::equals;
        if(compare.test(action, "add")) {
            int groupNum = Controller.getGroupNumber();
            System.out.println("Added: " + fNameTextField.getText() + " " + lNameTextField.getText() + " " + indexAddTextField.getText() + " " + emailAddTextField.getText() + " " + groupNum);
            studentDB.insertStudent(fNameTextField.getText(), lNameTextField.getText(), Integer.parseInt(indexAddTextField.getText()), emailAddTextField.getText(), groupNum);
        }
    }
}
