package controller;

import model.Model;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabController extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        if (tabbedPane.getSelectedIndex() == 1) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            Model.fireTabChanged();
        }
    }
}
