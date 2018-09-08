package scripts;

import java.awt.*;

public class Mouse implements Runnable {

    private int rollX;

    private static boolean up = false;

    private static boolean down = false;

    private Robot robot = All.getRobot();

    public Mouse(int X, int Y) throws AWTException {
        this.rollX = 0;
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
                if (All.isOnoff()&&(up||down||All.isIsOP())) {
                    int baseX=((Double)MouseInfo.getPointerInfo().getLocation().getX()).intValue();
                    int baseY=((Double)MouseInfo.getPointerInfo().getLocation().getY()).intValue();
                    if (down) {
                        All.setY(true);
                    }
                    if (up) {
                        All.setY(false);
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


    public static void setOnoff() {
        if (All.isOnoff()) {
            All.setOnoff(false);
            up = false;
            down = false;
            All.closeTermination();
            KeyBoardHook.shut = true;
        } else {
            All.setOnoff(true);
            up=false;
            down=false;
        }
    }
}
