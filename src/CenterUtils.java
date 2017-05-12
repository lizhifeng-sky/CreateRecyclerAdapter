import java.awt.*;

/**
 * Created by Administrator on 2017/4/20 0020.
 */
public class CenterUtils {
    public static void setFrameContentInParent(Frame jFrame) {
        int windowWidth = jFrame.getWidth(); //获得窗口宽
        int windowHeight = jFrame.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        jFrame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
    }

    public static void setDialogContentInParent(Dialog jFrame) {
        int windowWidth = jFrame.getWidth(); //获得窗口宽
        int windowHeight = jFrame.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
        jFrame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);//设置窗口居中显示
    }
}
