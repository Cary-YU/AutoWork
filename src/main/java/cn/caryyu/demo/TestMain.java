package cn.caryyu.demo;

import cn.caryyu.Single.SingleRobot;
import cn.caryyu.util.DesktopUtil;
import cn.caryyu.util.RobotUtil;
import cn.caryyu.util.ToolkitUtil;

import java.awt.*;

public class TestMain {
    public static void main(String[] args) {
        Robot robot = SingleRobot.getInstance();
        //打开游览器
        DesktopUtil.browse(null);
        //往剪贴板中放入数据
        ToolkitUtil.setClipboard("高德地图");
        //停顿100毫秒  跟Thread.sleep() 一样
        robot.delay(100);
        RobotUtil.browserAddress(robot);
        RobotUtil.affix(robot);
        RobotUtil.enter(robot);
    }
}
