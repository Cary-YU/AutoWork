# AutoWork
## 场景

使用场景包括自动化重复性任务、提高工作效率和减少人工错误，适用于各行各业的流程自动化需求。

## 原理概述

截取屏幕，并识别图片关键点像素，确定位置，并通过剪贴板实现JVM消息和屏幕消息的交换。通过Robot类操作鼠标和键盘。

## 用法

```java
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
        // 获取屏幕分辨率
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 截取当前屏幕
        BufferedImage image = RobotUtil.createScreenCapture(robot,new Rectangle(screenSize));
//        File file = new File("./src/main/resources/photos/test.png");
//        ImageIO.write(image, "png", file);
        // 读取需要比对的图片
        BufferedImage t = ImageIO.read(new File("./src/main/resources/photos/t.jpg"));
        // 核心计算
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
```

核心计算逻辑

```java
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
```
首先根据屏幕的截图去遍历每一帧，然后每一帧跟我们提前创建好的图片进行比对，计算一个得分，当得分小于30的时候我们就认为找到了，并通过Robot类移动鼠标并点击。
