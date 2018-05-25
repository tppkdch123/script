package scripts;

import java.awt.*;

public class Mouse implements Runnable {
    private int vecX;
    private int vecY;

    private static boolean up = false;

    private static boolean down = false;

    private static boolean left = false;

    private static boolean right = false;

    private static boolean onoff = true;

    private Robot robot = new Robot();

    public Mouse(int X, int Y) throws AWTException {
        this.vecX = Toolkit.getDefaultToolkit().getScreenSize().width/135;
        this.vecY = Toolkit.getDefaultToolkit().getScreenSize().height/68;
    }

    public static boolean isUp() {
        return up;
    }

    public static void setUp(boolean up) {
        Mouse.up = up;
    }

    public static boolean isDown() {
        return down;
    }

    public static void setDown(boolean down) {
        Mouse.down = down;
    }

    public static boolean isLeft() {
        return left;
    }

    public static void setLeft(boolean left) {
        Mouse.left = left;
    }

    public static boolean isRight() {
        return right;
    }

    public static void setRight(boolean right) {
        Mouse.right = right;
    }

    public void run() {
        try {
            while (true) {
                if (onoff && (up || down || left || right)) {
                    Double y = MouseInfo.getPointerInfo().getLocation().getY();
                    Double x = MouseInfo.getPointerInfo().getLocation().getX();
                    int X = x.intValue();
                    int Y = y.intValue();
                    if (down) {
                        //Y = y < maxY ? y.intValue() + vecY : maxY + vecY;
                         Y=y.intValue()+vecY;
                    }
                    if (up) {
                        //Y = y > 0 ? y.intValue() - vecY : 0 - vecY;
                        Y=y.intValue()-vecY;
                    }
                    if (right) {
                       // X = x < maxX ? x.intValue() + vecX : maxX + vecX;
                        X=x.intValue()+vecX;
                    }
                    if (left) {
                       //X = x > 0 ? x.intValue() - vecX : 0 - vecX;
                        X=x.intValue()-vecX;
                    }
                    robot.mouseMove(X, Y);
                }
                Thread.sleep(10);
            }
            //System.out.println(MouseInfo.getPointerInfo().getLocation());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setOnoff() {
        if (onoff) {
            onoff = false;
            left = false;
            right = false;
            up = false;
            down = false;
        } else {
            onoff = true;;
            left=false;
            right=false;
            up=false;
            down=false;
        }
    }
}
