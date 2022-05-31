package Main.services;


import com.develop.jawin.COMException;
import sun.applet.Main;

import java.awt.*;
import java.io.File;

public class functions {

    public static void  mouse_leftclick(int x, int y) throws Exception {
        /*
        鼠标点击(x,y)点
         */
        functions.delay();
        Data.dm.MoveTo(x, y);
        functions.delay();
        Data.dm.LeftClick();

    }


    public static void delay(double x) throws Exception, InterruptedException {
        /*
        暂停程序
         */
        Thread.sleep((long)x * 1000);
    }
    public static void delay() throws Exception, InterruptedException {
        /*
        暂停程序
         */
        Thread.sleep(500);
    }


    public static int[] find_pic(String pic_name, int startx, int starty, int endx, int endy) throws Exception {
        /*
        调用找图并返回找图后的两个坐标
         */
        String str_pic = Data.dm.FindPicE(startx, starty, endx, endy, pic_name, Data.FIND_PIC_COLOR, Data.FIND_PARTIAL, 0);
        String[] splitAddr =  str_pic.split("\\|");

        int a[] = new int[2];
        if("".equals(str_pic)){
            a[0] = -1;
            a[1] = -1;
            return a;
        }
        a[0] = Integer.parseInt(splitAddr[1]);
        a[1] = Integer.parseInt(splitAddr[2]);
        return a;
    }

    public static int[] find_pic(String pic_name) throws Exception {
        /*
        调用找图并返回找图后的两个坐标 整个游戏区域
         */
        int startx=0;
        int starty=0;
        int endx=1366;
        int endy=768;
        String str_pic = Data.dm.FindPicEx(startx, starty, endx, endy, pic_name, Data.FIND_PIC_COLOR, Data.FIND_PARTIAL, 0);
        String[] splitAddr =  str_pic.split("\\|");
        String[] splitaddr = splitAddr[0].split(",");

        int a[] = new int[2];
        if("".equals(str_pic)){
            a[0] = -1;
            a[1] = -1;
            return a;
        }

        a[0] = Integer.parseInt(splitaddr[1]);
        a[1] = Integer.parseInt(splitaddr[2]);
        return a;

    }




    public static int[] find_str(String str_name) throws Exception {
        /*
        调用找图并返回找图后的两个坐标 整个游戏区域
         */
        int startx=0;
        int starty=0;
        int endx=1366;
        int endy=768;
        Data.FIND_PARTIAL=1.0;
        String str_ch = Data.dm.FindStrE(startx, starty, endx, endy, str_name, Data.FIND_CH_COLOR, Data.FIND_PARTIAL);
        String[] splitAddr =  str_ch.split("\\|");

        int a[] = new int[2];
        if("".equals(str_ch)){
            a[0] = -1;
            a[1] = -1;
            return a;
        }

        a[0] = Integer.parseInt(splitAddr[1]);
        a[1] = Integer.parseInt(splitAddr[2]);
        return a;

    }

    public static int[] find_str(String str_name, int startx, int starty, int endx, int endy) throws Exception {
        /*
        调用找图并返回找图后的两个坐标 整个游戏区域
         */

        Data.FIND_PARTIAL=1.0;
        String str_ch = Data.dm.FindStrE(startx, starty, endx, endy, str_name, Data.FIND_CH_COLOR, Data.FIND_PARTIAL);
        String[] splitAddr =  str_ch.split("\\|");

        int a[] = new int[2];
        if("".equals(str_ch)){
            a[0] = -1;
            a[1] = -1;
            return a;
        }

        a[0] = Integer.parseInt(splitAddr[1]);
        a[1] = Integer.parseInt(splitAddr[2]);
        return a;

    }

    public static int find_strcount(String str_name, int startx, int starty, int endx, int endy) throws Exception {
        /*
        调用找图并返回找图后的两个坐标
         */
        Data.FIND_PARTIAL=1.0;
        String str_ch = Data.dm.FindStrEx(startx, starty, endx, endy, str_name, Data.FIND_CH_COLOR, Data.FIND_PARTIAL);
        String[] splitAddr;
        int count = 0;
        if(str_ch.contains("|")){
            splitAddr = str_ch.split("\\|");
            count = splitAddr.length;
        }else{
            splitAddr =  str_ch.split(",");
            if("".equals(str_ch)){
                count = 0;

            }
            count = 1;
        }
        return count;
    }
    public static int[] find_color(String color) throws COMException {
        /*
        调用找图并返回找图后的两个坐标 整个游戏区域
         */
        int startx=0;
        int starty=0;
        int endx=1366;
        int endy=768;
        Data.FIND_PARTIAL=1.0;
        String str_ch = Data.dm.FindColorE(startx, starty, endx, endy, color, Data.FIND_PARTIAL,0);
        String[] splitAddr =  str_ch.split("\\|");

        int a[] = new int[2];
        if("".equals(str_ch)){
            a[0] = -1;
            a[1] = -1;
            return a;
        }

        a[0] = Integer.parseInt(splitAddr[0]);
        a[1] = Integer.parseInt(splitAddr[1]);
        return a;
    }
    public static int[] find_color(String color, int startx, int starty, int endx, int endy) throws COMException {
        /*
        调用找图并返回找图后的两个坐标 整个游戏区域
         */
        Data.FIND_PARTIAL=1.0;
        String str_ch = Data.dm.FindColorE(startx, starty, endx, endy, color, Data.FIND_PARTIAL,0);
        String[] splitAddr =  str_ch.split("\\|");

        int a[] = new int[2];
        if("".equals(str_ch)){
            a[0] = -1;
            a[1] = -1;
            return a;
        }

        a[0] = Integer.parseInt(splitAddr[0]);
        a[1] = Integer.parseInt(splitAddr[1]);
        return a;
    }

    public static String getPath(){
        File file = getFile();

        if(file==null)return null;
        String path = file.getAbsolutePath();
        Mainframe.textArea_Info.setText(path);
        System.out.println(path);
        int i = path.indexOf("jdm-master");
        String new_path = path.substring(0,i);
        Mainframe.textArea_Info.setText(new_path);
        return new_path;
    }

    public static File getFile() {
        //关键是这行...
        String path = functions.class.getProtectionDomain().getCodeSource()
                .getLocation().getFile();
        //Mainframe.textArea_Info.setText(path);
        try{
            path = java.net.URLDecoder.decode(path, "UTF-8");//转换处理中文及空格
        }catch (java.io.UnsupportedEncodingException e){
            return null;
        }
        return new File(path);
    }

    public static boolean is_MainPage() throws Exception {
        int x[] = functions.find_pic("主界面_战力.bmp");
        if(x[0]<0 && x[1]<0) {
            return false;
        }
        return true;
    }
//    pic_判断主界面 = "c:\test_game\主界面判断.bmp"
//    找图颜色 = "121212"
//    找图偏色度 = 1.0
//    dm_ret1 = dm.FindPic(0, 0, 1366, 768, pic_判断主界面, 找图颜色, 找图偏色度, 0, x, y)
//    If x >= 0 and y >= 0 Then
//    TracePrint "当前为主界面"
//    Call 等待()
//    Call 进入家园()
//    Else
//    TracePrint "请回到主界面再启动本脚本"
//    Delay(100)
//    End If
//    End Function

}
