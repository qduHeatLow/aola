package Main.services;

import sun.applet.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  Pozhenwang {
    public static void pozhenwang_main() throws Exception {
        Mainframe.textArea_Info.setText("正在执行");
        Data.FIND_PARTIAL = 0.8;
        int x[] = functions.find_pic("破阵王头像.bmp");
        int y[] = functions.find_pic("破阵王头像3.bmp");
        int z[] = functions.find_pic("破阵王头像2.bmp");
        int p[] = functions.find_pic("破阵王头像1.bmp");
        while(x[0]>0 && x[1]>0 || y[0]>0 && y[1]>0  || z[0]>0 && z[1]>0 || p[0]>0 && p[1]>0){
            if(y[0]>0 && x[0]<0){
                x[0] = y[0];
                x[1] = y[1];
            }else if(z[0]>0 && x[0]<0){
                x[0] = z[0];
                x[1] = z[1];
            }else if(p[0]>0 && x[0]<0){
                x[0] = p[0];
                x[1] = p[1];
            }
            Mainframe.textArea_Info.setText("即将进入战斗");
            //Mainframe.textArea_Info.setText(y[0] + "*" + y[1]);
            functions.delay(1);
            functions.mouse_leftclick(x[0],x[1]-20);
            functions.delay(1);
            functions.mouse_leftclick(529,431);



            Mainframe.textArea_Info.setText("已进入战斗");

            Data.is_end = false;
            Fight fight = new Fight();

            fight.fight_inround_data();
            if(Fight.success){
                int yes[] = functions.find_pic("白底蓝确定.bmp");
                while(yes[0] < 0 && yes[1] <0 ){

                    yes = functions.find_pic("白底蓝确定.bmp");
                }
                Mainframe.textArea_Info.setText("正在确定");

                functions.mouse_leftclick(yes[0],yes[1]);
            }
            Mainframe.textArea_Info.setText("即将进行下一关");
            functions.delay(10);
            x = functions.find_pic("破阵王头像.bmp");
            y = functions.find_pic("破阵王头像3.bmp");
            z = functions.find_pic("破阵王头像2.bmp");
            p = functions.find_pic("破阵王头像1.bmp");
        }
        Mainframe.textArea_Info.setText("战斗结束或出现错误，请结束");

    }
}
class Pozhenwang_Thread extends Thread{
    public void run(){
        try {
            Pozhenwang.pozhenwang_main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
//以下是两个按钮事件
//开始主线
class Start_Pozhenwang implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Mainframe.Button_Start.setEnabled(false);
        Mainframe.t = new Pozhenwang_Thread();
        Mainframe.t.start();
    }
}


//结束主线
class End_Pozhenwang implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            functions.delay();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Mainframe.t.stop();
        Mainframe.textArea_Info.setText("已结束");
        Mainframe.Button_Start.setEnabled(true);

    }
}
