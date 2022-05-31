package Main.services;

import sun.applet.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fuben {

    public static void fuben_main() throws Exception {
        if(!functions.is_MainPage()) {
            Mainframe.textArea_Info.setText("请返回主界面以开始");
            return;
        }
        functions.delay(2);
        functions.mouse_leftclick(936,342);
        Data.dm.LeftDown();
        functions.delay(2);
        Data.FIND_PARTIAL = 0.9;
        int x[] = functions.find_pic("神隐雾山_主界面.bmp");
        if(x[0] < 0 && x[1] <0 ){
            Mainframe.textArea_Info.setText("没有找到副本");
            return;
        }
        Data.dm.MoveTo(x[0],x[1]);
        Mainframe.textArea_Info.setText("正在进入副本");
        Data.dm.LeftClick();
        //functions.mouse_leftclick(x[0],x[1]);
        functions.delay();

        x = functions.find_pic("神隐雾山_二级界面.bmp");
        while(x[0] < 0 && x[1] <0 ){
            Mainframe.textArea_Info.setText("没有进入副本 正在重试");
            x = functions.find_pic("神隐雾山_二级界面.bmp");
        }
        Mainframe.textArea_Info.setText("正在进入副本");
        functions.mouse_leftclick(x[0],x[1]);
        functions.delay();

        for(int count = 1,startx = 0,endx = 303;count<=4 ;count ++) {
            functions.delay();
            x = functions.find_pic("单个副本.bmp", startx, 0, endx, 597);
            while (x[0] < 0 && x[1] < 0) {
                Mainframe.textArea_Info.setText("没有进入单个副本 正在重试");
                x = functions.find_pic("单个副本.bmp");
            }
            Mainframe.textArea_Info.setText("正在进入单个副本");

            functions.mouse_leftclick(x[0], x[1]);
            functions.delay();
            switch (count){
                case 1:
                    functions.mouse_leftclick(745,431);
                    functions.delay(2);
                    sweep();
                    startx = 303;
                    endx = 492;
                    break;
                case 2:
                    functions.mouse_leftclick(829,404);
                    functions.delay(2);
                    sweep();
                    startx = 492;
                    endx = 646;
                    break;
                case 3:
                    functions.mouse_leftclick(830,342);
                    functions.delay(2);
                    sweep();
                    startx = 646;
                    endx = 979;
                    break;
                case 4:
                    functions.mouse_leftclick(834,378);
                    functions.delay(2);
                    sweep();
                    break;
            }


        }
        functions.delay();
        functions.mouse_leftclick(937, 64);
        functions.delay();
        functions.mouse_leftclick(937, 64);

        Mainframe.textArea_Info.setText("已结束，请关闭");


    }

    public static void sweep() throws Exception {
        functions.delay();
        int count = 0;

        do {
            //Mainframe.textArea_Info.setText("已进入副本 检测对话"+count++);

            functions.delay();
            if ( ("4cd8fc".equals(Data.dm.GetColor(697,421))) ){
                //点击剧情对话
                functions.mouse_leftclick(854,565);
                Mainframe.textArea_Info.setText("正在点击对话");
            }
        }while ( !("ffffff".equals(Data.dm.GetColor(661,153))));

        for(int i = 0;i<3;i++){

            int x[] = functions.find_pic("一键扫荡.bmp");	//扫荡


            if(x[0] < 0 && x[1] <0 ){
                Mainframe.textArea_Info.setText("已经扫荡完成");
                break;
            }
            Mainframe.textArea_Info.setText("正在扫荡");
            functions.mouse_leftclick(x[0],x[1]);
            int yes[] = functions.find_pic("白底蓝确定.bmp");
            while(yes[0] < 0 && yes[1] <0 ){
//                Mainframe.textArea_Info.setText("没有找到确定");
                yes = functions.find_pic("白底蓝确定.bmp");
            }
            Mainframe.textArea_Info.setText("正在确定");
            functions.mouse_leftclick(yes[0],yes[1]);
            functions.delay();
        }
        functions.delay();//关闭单个副本
        functions.mouse_leftclick(876, 109);
        functions.delay();//关闭单个副本
        functions.mouse_leftclick(937, 64);
    }



}

//主线任务的线程
class Fuben_Thread extends Thread{
    public void run(){
        try {
            Fuben.fuben_main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

//以下是两个按钮事件
//开始主线
class Start_Fuben implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Mainframe.Button_Start.setEnabled(false);
        Mainframe.t = new Fuben_Thread();
        Mainframe.t.start();
    }
}


//结束主线
class End_Fuben implements ActionListener{
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
