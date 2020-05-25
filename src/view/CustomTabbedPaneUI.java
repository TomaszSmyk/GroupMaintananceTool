package view;

import java.awt.*;
import javax.swing.plaf.basic.*;

import static view.BackgroundColor.COLOR;

public class CustomTabbedPaneUI extends BasicTabbedPaneUI {

    private final int barHeight = 40;

//    private final int tabDesiredHeight = (700 - barHeight)/5;//todo provide size from outer class
    private final int tabDesiredHeight = (View.widowSize.height - 6)/5 - 10;
    private final int tabDesiredWidth = View.widowSize.width/5;

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2D = (Graphics2D) g;

        Rectangle bounds = new Rectangle(x, y, w, h);
        GradientPaint gradientShadow;
        if (isSelected) {
            gradientShadow = new GradientPaint(COLOR.beginPoint.x, COLOR.beginPoint.y, COLOR.gradientBegin,
                    COLOR.endPoint.x, COLOR.endPoint.y, COLOR.gradientEnd);
        } else {
            gradientShadow = new GradientPaint(COLOR.beginPoint.x, COLOR.beginPoint.y, COLOR.gradientLighterBegin,
                    COLOR.endPoint.x, COLOR.endPoint.y, COLOR.gradientLighterEnd);
        }
        g2D.setPaint(gradientShadow);

        g2D.fill(bounds);
    }


    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return tabDesiredWidth;
    }

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return tabDesiredHeight;
    }

    //no tab border painted
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }

}