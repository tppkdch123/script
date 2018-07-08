package scripts;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class All {
    private static Robot robot;

    private static int vecX;

    public static boolean isIsOP() {
        return isOP;
    }

    public static void setIsOP(boolean isOP) {
        All.isOP = isOP;
    }

    private static boolean isOP=false;

    public static boolean isOnoff() {
        return onoff;
    }

    public static void setOnoff(boolean onoff) {
        All.onoff = onoff;
    }

    private static int vecY;

    private static boolean onoff = true;

    private static AtomicInteger X = new AtomicInteger(0);
    private static AtomicInteger Y = new AtomicInteger(0);


    public static int getX() {
        return X.get();
    }

    public static void setX(Boolean b) {
        if (b) {
            X.addAndGet(vecX);

        } else {
            X.addAndGet(-vecX);
        }
    }

    public static int getY() {
        return Y.get();
    }

    public static void setY(boolean b) {
        if (b) {
            Y.addAndGet(vecY);
        } else {
            Y.addAndGet(-vecY);
        }
    }

    public static void setX(int vec){
        X.addAndGet(vec);
    }

    public static Robot getRobot() {
        return robot;
    }

    public static void setRobot(Robot robot) {
        All.robot = robot;
    }


    static {

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        vecX = Toolkit.getDefaultToolkit().getScreenSize().width / 95;
        vecY = Toolkit.getDefaultToolkit().getScreenSize().height / 70;
    }

    public static void setPosition(int x, int y) {
        robot.mouseMove(x, y);
    }

    public static void clear(){
        X.set(0);
        Y.set(0);
    }
}
