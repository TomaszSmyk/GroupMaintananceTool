package view;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

import static view.BackgroundColor.COLOR;

/**
 * Custom class to create tabbed pane with different color and tabs width/heights
 */
public class CustomTabbedPaneUI extends BasicTabbedPaneUI {

    private final int tabDesiredHeight = (View.widowSize.height - 6)/5 - 10;
    private final int tabDesiredWidth = View.widowSize.width/5;

    /**
     * Overridden super method, generally used to change color of pane
     */
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

    /**
     * Overridden method to make tab wider
     */
    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return tabDesiredWidth;
    }

    /**
     * Overridden method to make tab higher
     */
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return tabDesiredHeight;
    }

    /**
     * Overiddent method to make sure that there will not be tab border painted, so just it does nothing at all
     */
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
    }

}