package scripts;

import java.awt.*;

public class Mouse implements Runnable {
    private int vecX;
    private int vecY;

    private int rollX;

    private static boolean up = false;

    private static boolean down = false;

    private static boolean left = false;

    private static boolean right = false;

    private static boolean onoff = true;

    private static boolean leftRoll = false;

    private static boolean rightRoll = false;

    private Robot robot = new Robot();

    public Mouse(int X, int Y) throws AWTException {
        this.vecX = Toolkit.getDefaultToolkit().getScreenSize().width/110;
        this.vecY = Toolkit.getDefaultToolkit().getScreenSize().height/70;
        this.rollX=Toolkit.getDefaultToolkit().getScreenSize().width/175;
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
                        X+=vecX;
                    }
                    if (left) {
                       //X = x > 0 ? x.intValue() - vecX : 0 - vecX;
                        X-=vecX;
                    }

                    if(leftRoll){
                       X-=rollX;
                    }

                    if(rightRoll){
                        X+=rollX;
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

    public static void setOp(){
        if(left){
            left=false;
        }
        else{
            right=false;
            left=true;
        }
    }

    public static void setOp2(){
        if(right){
            right=false;
        }
        else{
            left=false;
            right=true;
        }
    }
}
