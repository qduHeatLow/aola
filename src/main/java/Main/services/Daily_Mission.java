package Main.services;

import com.develop.jawin.COMException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Daily_Mission {

}

//主线任务的线程
class MissionThread extends Thread{
    public void run(){
        try {
            Random rd = new Random();
            while(true){
                Data.FIND_PARTIAL = 0.5;
                int pass[] = functions.find_pic("跳过动画.bmp");
                int click_talk[] = functions.find_color("ffff33");
                if( ("ffffff".equals(Data.dm.GetColor(819,388))) &&
                        ("ffffff".equals(Data.dm.GetColor(855,387)))){
                    functions.mouse_leftclick(820,390);
                    Mainframe.textArea_Info.setText("已关闭弹幕");
                }
                if( ("4ddafd".equals(Data.dm.GetColor(600,420))) ){
                    //点击剧情对话
                    if(click_talk[0] != -1 && click_talk[1] !=-1){
                        functions.mouse_leftclick(click_talk[0],click_talk[1]+5);
                    }
                    functions.mouse_leftclick(600,520);
                    Mainframe.textArea_Info.setText("正在点击对话");
                }else if(pass[0] != -1 && pass[1]!=-1){
                    //找图
                    Mainframe.textArea_Info.setText("正在跳过剧情动画");
                    functions.mouse_leftclick(pass[0],pass[1]);
                }if( ("ffffff".equals(Data.dm.GetColor(708,79))) ){
                    //点击剧情对话
                    if(click_talk[0] != -1 && click_talk[1] !=-1){
                        functions.mouse_leftclick(click_talk[0],click_talk[1]+5);
                    }
                    functions.mouse_leftclick(600,520);
                    Mainframe.textArea_Info.setText("正在跳过动画");
                }else if(("ffffff".equals(Data.dm.GetColor(856,87))) &&
                        ("ffffff".equals(Data.dm.GetColor(876,87)))){
                    //点击剧情对话2
                    Mainframe.textArea_Info.setText("正在跳过剧情动画");
                    functions.mouse_leftclick(876,87);
                }else if( ("ffff00".equals(Data.dm.GetColor(933,545)) )){
                    Mainframe.textArea_Info.setText("正在跳过漫画页面");
                    functions.mouse_leftclick(933,545);
                    functions.mouse_leftclick(Data.centerx,Data.centery);
                } else if( ("ffff00".equals(Data.dm.GetColor(932,115)))  && ("ffff00".equals(Data.dm.GetColor(900,113))) ){
                    Mainframe.textArea_Info.setText("正在关闭漫画页面");
                    functions.mouse_leftclick(932,115);
                    functions.mouse_leftclick(Data.centerx,Data.centery);
                }else if( ("f7cc32".equals(Data.dm.GetColor(800,495)))  &&
                        ("f7cc32".equals(Data.dm.GetColor(929,495))) ){
                    Mainframe.textArea_Info.setText("正在关闭漫画页面");
                    functions.mouse_leftclick(852,491);
                    functions.mouse_leftclick(Data.centerx,Data.centery);
                }else if( ("9f6f00".equals(Data.dm.GetColor(932,115)))  &&
                        ("ffff00".equals(Data.dm.GetColor(945,114))) ){
                    //结束漫画
                    Mainframe.textArea_Info.setText("正在关闭漫画页面");
                    functions.mouse_leftclick(932,115);
                    functions.mouse_leftclick(Data.centerx,Data.centery);

                }else{
                    Mainframe.textArea_Info.setText("没有找到剧情或正在执行操作...");
                }
                int t = rd.nextInt(100);

                functions.delay(0.3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


//以下是两个按钮事件
//开始主线
class Start_Mission implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Mainframe.Button_Start.setEnabled(false);
        Mainframe.t = new MissionThread();
        Mainframe.t.start();
    }
}

//结束主线
class End_Mission implements ActionListener{
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