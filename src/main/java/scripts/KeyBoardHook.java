package scripts;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import scripts.runnables.LeftOrRight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class KeyBoardHook implements Runnable {

    private WinUser.HHOOK hhk;

    public static boolean shut = false;

    public static boolean five = false;


    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new SynchronousQueue<Runnable>());

    //钩子回调函数
    private WinUser.LowLevelKeyboardProc keyboardProc = new WinUser.LowLevelKeyboardProc() {
        public WinDef.LRESULT callback(int nCode, WinDef.WPARAM wParam, WinUser.KBDLLHOOKSTRUCT event) {
            // 输出按键值和按键时间
            if (nCode >= 0) {
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                System.out.println(time + " KEY: " + event.vkCode);
                switch (event.vkCode) {

                    case 86:
                        if(event.flags == 0){
                            pause();
                        }
                        break;
                    case 162:
                        if (event.flags == 0) {
                            if (Mouse.isUp()) {
                                Mouse.setUp(false);
                                All.closeTermination();
                            } else {
                                Mouse.setDown(false);
                                Mouse.setUp(true);
                                All.closeTermination();
                            }
                        }
                        break;
                    case 81:
                        if (event.flags == 0) {
                            Mouse.setUp(false);
                            if (Mouse.isDown()) {
                                Mouse.setDown(false);
                                All.closeTermination();
                            } else {
                                Mouse.setDown(true);
                                All.closeTermination();
                            }
                        }
                        break;
                    case 96:
                        if (event.flags == 0) {
                            All.switchTermination();
                            try {
                                threadPoolExecutor.execute(new LeftOrRight(true));
                            } catch (Exception e) {
                                break;
                            }
                        }
                        break;
                    case 107:
                        if (event.flags == 0) {
                            All.switchTermination();
                            try {
                                threadPoolExecutor.execute(new LeftOrRight(false));
                            } catch (Exception e) {
                                break;
                            }
                        }
                        break;
                    case 19:
                        if (event.flags == 0) {
                            Mouse.setOnoff();
                        }
                        break;
                    case 35:
                        KeyBoardHook.this.setHookOff();
                        break;
                    case 32:
                        if (event.flags == 0){
                            pause();
                        }
                        break;
                    case 101:
                        if (event.flags == 0) {
                            five = true;
                        } else {
                            five = false;
                        }
                        break;
                    default:
                        break;
                }
            }
            return User32.INSTANCE.CallNextHookEx(hhk, nCode, wParam, null);
        }
    };

    public void run() {
        WinDef.HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);
        hhk = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, keyboardProc, hMod, 0);

        int result;
        WinUser.MSG msg = new WinUser.MSG();
        while ((result = User32.INSTANCE.GetMessage(msg, null, 0, 0)) != 0) {
            if (result == -1) {
                setHookOff();
                break;
            } else {
                User32.INSTANCE.TranslateMessage(msg);
                User32.INSTANCE.DispatchMessage(msg);
            }
        }
    }

    // 移除钩子并退出
    public void setHookOff() {
        //System.out.println("Hook Off!");
        User32.INSTANCE.UnhookWindowsHookEx(hhk);
        System.exit(0);
    }

    //停止
    private void pause() {
        Mouse.setDown(false);
        Mouse.setUp(false);
        shut = true;
        All.closeTermination();
    }
}
