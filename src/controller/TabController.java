package controller;

import model.Model;
import view.LineChart;
import view.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TabController extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        Model.fireTabChanged();
        if (tabbedPane.getSelectedIndex() == 4) {
            System.out.println("TABCONTROLLER");
            View.setupChartsTab();
            View.tabbedPane.remove(4);
        }
    }
}
