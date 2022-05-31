package Main.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Daily_wushanjuqing {

}

//雾山任务的线程
class WushanThread extends Thread{
    public void run(){
        try {
            while(true){
                functions.mouse_leftclick(300,400);
                functions.delay(1);
                int x[] = functions.find_color("ffcc00");
                if(x[0] != -1 && x[1]!=-1){
                    functions.mouse_leftclick(x[0],x[1]+10);
                    Mainframe.textArea_Info.setText("正在进行下一章节");
                    while("292d39".equals(Data.dm.GetColor(885,280))){ //开始某一剧情
                        System.out.println(Data.dm.GetColor(885,280));
                        functions.mouse_leftclick(600,520);
                        Mainframe.textArea_Info.setText("正在进行剧情");
                    }
                    continue;
                }
                break;

            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}


//以下是两个按钮事件
//开始雾山
class Start_Wushan implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Mainframe.Button_Start.setEnabled(false);
        Mainframe.t = new WushanThread();
        Mainframe.t.start();
    }
}

//结束雾山
class End_Wushan implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            functions.delay();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Mainframe.t.stop();
        Mainframe.Button_Start.setEnabled(true);
    }
}