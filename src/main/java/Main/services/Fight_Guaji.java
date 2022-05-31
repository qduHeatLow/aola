package Main.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fight_Guaji
{
}
//战斗线程
class FightGuaji_Thread extends Thread{
    public void run(){
        try {
                Fight fight = new Fight();
                fight.fight_inround_data();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
//开始挂机
class Start_FightGuaji implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Data.is_end = false;
        Mainframe.Button_Start.setEnabled(false);
        Mainframe.t = new FightGuaji_Thread();
        Mainframe.t.start();

    }
}

//结束挂机
class End__FightGuaji implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        Mainframe.textArea_Info.setText("已结束运行");
        Data.is_end = true;
        try {
            functions.delay();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Mainframe.t.stop();
        Mainframe.Button_Start.setEnabled(true);

    }
}