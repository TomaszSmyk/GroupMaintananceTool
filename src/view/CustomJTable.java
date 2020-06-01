package view;

import javax.swing.*;
import java.awt.*;

import static view.BackgroundColor.COLOR;

/**
 * Custom class to create custom table with different color
 */
public class CustomJTable extends JTable {
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
}
