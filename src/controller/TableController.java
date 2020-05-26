package controller;

import model.Model;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableController extends MouseAdapter {
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
        sourceTable.setValueAt(isPresent, rowSelected, 6);
        //todo make it write that presence data to the student
        int id = Integer.parseInt((String) sourceTable.getValueAt(rowSelected, 0));
        Model.changeStudentPresence(id, isPresent);

        //todo make it write that presence data to the database

        //todo color table: isPresent==true -> green; isPresent==false -> red

    }
}
