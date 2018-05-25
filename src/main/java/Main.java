import scripts.KeyBoardHook;
import scripts.Mouse;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("键盘视角脚本");
        jFrame.setSize(600, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.add(new JLabel("<html>大鸡岭茶包</html>"),JLabel.CENTER);
        jFrame.setVisible(true);


        try {
            new Thread(new Mouse(10, 16)).start();
        } catch (AWTException e) {
            System.exit(0);
        }
        new Thread(new KeyBoardHook()).start();
    }
}
