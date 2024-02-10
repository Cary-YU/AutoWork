package cn.caryyu.demo;

import cn.caryyu.Single.SingleRobot;
import cn.caryyu.core.Calculate;
import cn.caryyu.entity.PositionObject;
import cn.caryyu.util.DesktopUtil;
import cn.caryyu.util.RobotUtil;
import cn.caryyu.util.ToolkitUtil;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class TestMain {
    public static void main(String[] args) throws Exception {
        Robot robot = SingleRobot.getInstance();
        //打开游览器
        DesktopUtil.browse(null);
        //往剪贴板中放入数据
        ToolkitUtil.setClipboard("高德地图");
        //停顿100毫秒  跟Thread.sleep() 一样
        //根据打开网页的速度进行调整
        robot.delay(1000);
        RobotUtil.browserAddress(robot);
        RobotUtil.affix(robot);
        RobotUtil.enter(robot);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        BufferedImage image = RobotUtil.createScreenCapture(robot,new Rectangle(screenSize));
        File file = new File("./src/main/resources/photos/test.png");
        ImageIO.write(image, "png", file);
        BufferedImage t = ImageIO.read(new File("./src/main/resources/photos/t.jpg"));
        PositionObject positionObject = Calculate.calcPositionObject(image, t);
        if (positionObject == null){
            throw new Exception("计算图片未获得点位");
        }
        robot.mouseMove(positionObject.getX(),positionObject.getY());
        robot.delay(100);
        // 点击左键
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
