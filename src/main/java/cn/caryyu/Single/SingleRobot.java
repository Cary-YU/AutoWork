package cn.caryyu.Single;

import java.awt.*;

/**
 * Cary-Yu
 * 全局只有一个Robot
 */
public class SingleRobot {

    private static Robot instance;
    public SingleRobot(){}

    public static Robot getInstance()  {
        if (instance == null) {
            try {
                instance = new Robot();
            } catch (AWTException ignored) {
            }
        }
        return instance;
    }
}
