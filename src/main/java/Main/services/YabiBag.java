package Main.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class YabiBag {
    public static void find_SPMipanTeam() throws Exception {
        if(!functions.is_MainPage()){
            Mainframe.textArea_Info.setText("请退回游戏主界面后启动");
            return;
        }else{
            functions.mouse_leftclick(926,558);
            functions.delay(2);
            int xiaoyao[] = functions.find_str("逍遥",0,0,969,422);
            if(xiaoyao[0]<0 && xiaoyao[1]<0){
                Mainframe.textArea_Info.setText("逍遥非首宠");
                Mainframe.textArea_Info.setText("已结束");
                Mainframe.Button_Start.setEnabled(true);
                Mainframe.t.stop();
                return;
            }
            Mainframe.textArea_Info.setText("逍遥为首宠");

            functions.delay();
            int count = functions.find_strcount("逍遥",10,427,967,559);
            Mainframe.textArea_Info.setText(count + "只逍遥");

            functions.delay();
            count = functions.find_strcount("敖兴",10,427,967,559);
            Mainframe.textArea_Info.setText(count + "只龙王敖兴");

            functions.delay();
            count = functions.find_strcount("SP密",10,427,967,559);
            Mainframe.textArea_Info.setText(count + "只SP密潘");

            functions.delay();
            count = functions.find_strcount("姜子牙",10,427,967,559);
            Mainframe.textArea_Info.setText(count + "只姜子牙");

            functions.delay();
            count = functions.find_strcount("冰雪女皇",10,427,967,559);
            Mainframe.textArea_Info.setText(count + "只冰雪女皇");

            Person_Skill(2);
            functions.mouse_leftclick(942,59);//关闭背包

        }






    }
    public static void Person_Skill(int mode) throws Exception {
        functions.mouse_leftclick(909,577);
        String strmode[] = {"幻雷", "双刃", "巨化"};
        switch(mode){
            case 0://幻雷
                functions.mouse_leftclick(253,188);
                break;
            case 1://双刃
                functions.mouse_leftclick(340,211);
                break;
            case 2://巨化
                functions.mouse_leftclick(386,275);
                break;

        }
        functions.delay();
        while (true){
            int x[] = functions.find_pic("召唤师技能_装备.bmp");
            if(x[0] > 0 && x[1] >0 )
                functions.mouse_leftclick(x[0],x[1]);
            else{
                Mainframe.textArea_Info.setText("召唤师技能已装备" + strmode[mode]);
                break;
            }

            x = functions.find_pic("召唤师技能_确定装备.bmp");
            if(x[0] > 0 && x[1] >0 )
                functions.mouse_leftclick(x[0],x[1]);
            Mainframe.textArea_Info.setText("召唤师技能已装备" + strmode[mode]);
            break;
        }

        functions.mouse_leftclick(896,97);  //关闭召唤师技能窗口

    }

}

//主线任务的线程
class SPMipanTeam_Thread extends Thread{
    public void run(){
        try {
            YabiBag.find_SPMipanTeam();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


//以下是两个按钮事件
//开始主线
class Start_SPMipanTeam implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Mainframe.Button_Start.setEnabled(false);
        Mainframe.t = new SPMipanTeam_Thread();
        Mainframe.t.start();
    }
}

//结束主线
class End_SPMipanTeam implements ActionListener{
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