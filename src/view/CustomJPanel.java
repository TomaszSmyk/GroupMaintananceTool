package view;

import javax.swing.*;
import java.awt.*;

import static view.BackgroundColor.COLOR;

/**
 * Custom class to create custom panel with different color
 */
public class CustomJPanel extends JPanel {

    /**
     * set color as gradient
     */
    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradientPaint = new GradientPaint(COLOR.beginPoint.x, COLOR.beginPoint.y,
                COLOR.gradientLighterBegin, COLOR.endPoint.x, COLOR.endPoint.y, COLOR.gradientLighterEnd);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    /**
     * overrides and does nothing to prevent from drawing border
     */
    @Override
    protected void paintBorder(Graphics g) {
    }

}
