package Main.services;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fight_Qingming {
}
//战斗线程
class FightThread extends Thread{
    public void run(){
        try {
            if (Mainframe.comboBox.getSelectedIndex() == 0) {
                Fight fight = new Fight();
                Data.skill_loc = 2;
                fight.fight_inround("占卜回魂", 2, 2, false);
            } else if (Mainframe.comboBox.getSelectedIndex() == 1) {
                Fight fight = new Fight();
                Data.skill_loc = 2;
                fight.fight_inround("占卜回魂", 2, 2, true);
            }else if(Mainframe.comboBox.getSelectedIndex() == 2){
                Fight fight = new Fight();
                while (true){
                    if(Data.is_end) break;
                    Data.FIND_PARTIAL = 1.0;
                    if(fight.is_endfight()) {
                        Mainframe.textArea_Info.setText("战斗结束");
                        functions.mouse_leftclick(484,472);
                        functions.delay();
                        break;
                    }else if (fight.is_inround()) {
                        Mainframe.textArea_Info.setText("回合正在进行...等待回合结束");
                        functions.delay();
                    }else{
                        if(!"ffffff".equals(Data.dm.GetColor(280,181) )){   //判断有没有魂（上访的）
                            if(fight.is_threestar()){
                                Mainframe.textArea_Info.setText("使用三星技能" );
                                functions.mouse_leftclick(Data.threestarx,Data.threestary);
                                functions.mouse_leftclick(Data.centerx, Data.centery);
                                continue;
                            }
                        }
                        Data.skill_loc = 2; //占卜回魂位置
                        Data.FIND_CH_COLOR = "ffffff";
                        Data.FIND_PARTIAL = 1;
                        int x[] = functions.find_str("占卜回魂");
                        if (x[0]>=0 && x[1]>=0){
                            if (fight.is_pp()){
                                Mainframe.textArea_Info.setText("使用技能:" + "占卜回魂");
                                functions.mouse_leftclick(x[0], x[1]);
                            }else{
                                Mainframe.textArea_Info.setText("技能: " + "占卜回魂" + " 无pp");
                                fight.use_pp();
                            }
                            functions.mouse_leftclick(Data.centerx, Data.centery);
                        }else{
                                Mainframe.textArea_Info.setText("识字失败");
                                functions.delay();
                            }
                        }
                }
            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}

//开始
class Start_Qingming implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        Data.is_end = false;

        Mainframe.Button_Start.setEnabled(false);
        Mainframe.t = new FightThread();
        Mainframe.t.start();

    }
}

//结束
class End_Qingming implements ActionListener{
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
