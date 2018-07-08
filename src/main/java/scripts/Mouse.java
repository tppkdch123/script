package scripts;

import java.awt.*;

public class Mouse implements Runnable {

    private int rollX;

    private static boolean up = false;

    private static boolean down = false;

    private static boolean leftRoll = false;

    private static boolean rightRoll = false;

    private Robot robot = All.getRobot();

    public Mouse(int X, int Y) throws AWTException {
        this.rollX=Toolkit.getDefaultToolkit().getScreenSize().width/1900;
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

    public void run() {
        try {
            while (true) {
                if (All.isOnoff()&&(up||down||leftRoll||rightRoll||All.isIsOP())) {
                    int baseX=((Double)MouseInfo.getPointerInfo().getLocation().getX()).intValue();
                    int baseY=((Double)MouseInfo.getPointerInfo().getLocation().getY()).intValue();
                    if (down) {
                        All.setY(true);
                    }
                    if (up) {
                        //Y = y > 0 ? y.intValue() - vecY : 0 - vecY;
                        All.setY(false);
                    }

                    if(leftRoll){
                       All.setX(-rollX);
                    }

                    if(rightRoll){
                        All.setX(rollX);
                    }
                    All.setPosition(baseX+All.getX(),baseY+All.getY());
                    All.clear();
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isLeftRoll() {
        return leftRoll;
    }

    public static void setLeftRoll(boolean leftRoll) {
        Mouse.leftRoll = leftRoll;
    }

    public static boolean isRightRoll() {
        return rightRoll;
    }

    public static void setRightRoll(boolean rightRoll) {
        Mouse.rightRoll = rightRoll;
    }

    public static void setOnoff() {
        if (All.isOnoff()) {
            All.setOnoff(false);
            up = false;
            down = false;
            leftRoll=false;
            rightRoll=false;
        } else {
            All.setOnoff(true);
            up=false;
            down=false;
            leftRoll=false;
            rightRoll=false;
        }

    }

}
