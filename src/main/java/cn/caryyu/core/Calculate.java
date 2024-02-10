package cn.caryyu.core;

import cn.caryyu.entity.PositionObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Calculate {

    /**
     * 核心计算获得需要鼠标点击的位置
     * 通过计算每一帧来获得电脑的xy轴
     * @param x
     * @param y
     * @param image
     * @param t
     * @return
     */
    private static double getLoss(int x, int y, BufferedImage image, BufferedImage t) {
        double loss = 0;
        for (int m = 0; m < t.getWidth(); m++) {
            for (int n = 0; n < t.getHeight(); n++) {
                if (x + m >= image.getWidth() || y + n >= image.getHeight()) {
                    continue;
                }
                Color ic = new Color(image.getRGB(x + m, y + n));
                Color tc = new Color(t.getRGB(m, n));
                loss += Math.pow(ic.getRed() - tc.getRed(), 2)
                        + Math.pow(ic.getBlue() - tc.getBlue(), 2)
                        + Math.pow(ic.getGreen() - tc.getGreen(), 2);
            }
        }
        return loss;
    }

    public static PositionObject calcPositionObject(BufferedImage image, BufferedImage t){
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); y++) {
                double loss = Calculate.getLoss(x, y, image, t);
                if (loss < 30) {
                    return new PositionObject(x,y,loss);
                }
            }
        }
        return null;
    }
}
