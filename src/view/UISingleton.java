package view;

import model.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UISingleton {
    private static UISingleton instance;
    private Container contentPane;
    private UISingleton(Container contentPane){
        this.contentPane = contentPane;
    }
    public static UISingleton getInstance() {
        if (instance == null) {
            instance = new UISingleton(View.getUI());
        }
        return instance;
    }

    public void refreshTable(int groupNumber) {
//        DefaultTableModel model = (DefaultTableModel) contentPane.getComponents().getModel();
//        model.getDataVector().removeAllElements();
//        studentTable.setModel(new Model().getStudentData(groupNumber));//todo change getgroup number

    }

    protected JTable retrieveTable() {

        for (Component element: contentPane.getComponents()) {
            System.out.println(element instanceof JTable);
            if (element instanceof JTable) {
                System.out.println(element);
                return (JTable)element;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        UISingleton ui = UISingleton.getInstance();
        JTable table = ui.retrieveTable();
        System.out.println(table);
    }
}
