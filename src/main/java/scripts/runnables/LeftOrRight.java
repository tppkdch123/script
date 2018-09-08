package scripts.runnables;

import scripts.All;
import scripts.KeyBoardHook;

import static scripts.enums.Constant.*;

public class LeftOrRight implements Runnable {
    private final boolean can;

    public LeftOrRight(boolean b) {
        can = b;
    }

    public void run() {
        int u = 0;
        All.setIsOP(true);
        KeyBoardHook.shut = false;
        try {
            while (u < frequency) {
                if (ifTermination()) {
                    return;
                }
                move();
                u++;
            }

            while(true){
                if (ifTermination()) {
                    return;
                }
                All.madeSmallX(can);
                try {
                    Thread.sleep(saveTime);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
        } finally {
            All.setIsOP(false);
        }
    }

    private boolean ifTermination() {
        if (!All.isOnoff() || KeyBoardHook.shut || All.isIsTermination()) {
            return true;
        }
        return false;
    }

    private void move() {
        All.madeX(can);
        try {
            Thread.sleep(leftOrRightTime);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
