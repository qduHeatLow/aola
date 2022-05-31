package Main.services;

import Main.Dm.Idmsoft;
import Main.utils.JdmUtil;

import java.io.InputStream;


public class Data {
    public static Idmsoft dm;
    public static String picpath = "";
    public static String FIND_PIC_COLOR;
    public static String FIND_CH_COLOR;
    public static double FIND_PARTIAL;
    public static boolean is_end = true;
    public static int centerx = 420;
    public static int centery = 350;
    public static int threestarx = 935;
    public static int threestary = 250;
    public static int skill_loc = 1; //技能
    public static boolean threestar = false;//是否三星
    public Data() throws Exception {
        /*
        初始化相关数据：
        初始化大漠插件
        定义全局找图颜色
        定义全局找字颜色
        定义全局偏色
        字库
         */
        JdmUtil.setLibPath();
        dm = new Idmsoft("dm.dmsoft");
        String ver = dm.Ver();
        Mainframe.textArea_Info.setText(ver);
        //functions.delay(5);
        dm.SetDict(0, functions.getPath()+"lib\\txt_zk\\dm_soft.txt");
        Mainframe.textArea_Info.setText(functions.getPath()+"lib\\pics\\");
        int dm_ret = dm.SetPath(functions.getPath()+"lib\\pics\\");
        FIND_PIC_COLOR = "121212";
        FIND_PARTIAL = 1.0;
        FIND_CH_COLOR = "ffffff";
    }

    public static void findwindow() throws Exception {
        /*
        寻找窗口绑定窗口 并调用收缩窗口
         */
        //Mainframe.textArea_Info.setText("已绑定并调整后台");
        int hwnd = dm.FindWindow("wxWindowNR", "百田游戏安全管家");
        Data.dm.MoveWindow(hwnd, 0, 0);
        Data.dm.GetWindow(hwnd, 1);
        //Data.dm.BindWindowEx(hwnd, "dx2", "windows3", "windows", "", 0);
        Data.dm.BindWindowEx(hwnd, "dx2", "windows3", "windows", "", 4);
        Mainframe.textArea_Info.setText("已绑定并调整后台");



        shrink_window();
    }




    public static void shrink_window() throws Exception {
        /*
        收缩窗口
         */


        String pic_shrinkwindow = "收缩界面.bmp";

        Data.FIND_PARTIAL = 1.0;
        int x[] = functions.find_pic(pic_shrinkwindow);

        if(x[0] >= 0 && x[1] >= 0){
            Mainframe.textArea_Info.setText("正在调整窗口");
            functions.mouse_leftclick(x[0], x[1]);
            functions.delay(1.0);
            Mainframe.textArea_Info.setText("已调整窗口");
        }else{
            Mainframe.textArea_Info.setText("窗口已调整为最佳状态");
            functions.delay(0.1);
        }

    }
}
