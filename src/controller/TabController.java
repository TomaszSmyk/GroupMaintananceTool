package controller;

import model.Model;
import view.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Tabs controller
 */
public class TabController extends MouseAdapter {
    /**
     * If user clicked any tab, invokes Model method and also, if that tab is on index 4 update chart
     * by crating new tab with updated chard and deleting old one
     * @param e mouse clocked event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        Model.fireTabChanged();
        if (tabbedPane.getSelectedIndex() == 4) {
            View.setupChartsTab();
            View.tabbedPane.remove(4);
        }
    }
}
