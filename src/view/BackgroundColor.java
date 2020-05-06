package view;

import java.awt.*;

public enum BackgroundColor {

    COLOR;

    protected final Color gradientBegin;
    protected final Color gradientLighterBegin;
    protected final Color gradientEnd;
    protected final Color gradientLighterEnd;
    protected final Color base;
    protected final Point beginPoint;
    protected final Point endPoint;


    BackgroundColor() {
        gradientBegin = new Color(78, 83, 128, 50);
        gradientLighterBegin = new Color(156, 165, 255, 255);
        gradientEnd = new Color(10, 10, 255, 1);
        gradientLighterEnd = new Color(124, 132, 204, 80);
        base = new Color(79, 97, 255, 255);
        beginPoint = new Point(50 ,500);
        endPoint = new Point(100 ,100);
    }
}
