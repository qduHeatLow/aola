package Main.services;


import com.develop.jawin.COMException;
import sun.reflect.annotation.ExceptionProxy;

public class Fight {
    public static boolean success = true;

    //非设置技能的回合
    public void fight_inround(String str, int mode,int loc,boolean threestar) throws Exception {
        /*
        判读回合是否结束或战斗是否结束 并调用使用技能
        战斗结束则结束该方法 否则一直循环
        str: 技能名
        mode: 模式1(找图)或者模式2(找字)
         */
        while(true){
            if(Data.is_end) break;
            Data.FIND_PARTIAL = 1.0;
            if(is_endfight()) {
                Mainframe.textArea_Info.setText("战斗结束");
                functions.mouse_leftclick(484,472);
                functions.delay();
                break;
            }else if (is_inround()) {
                Mainframe.textArea_Info.setText("回合正在进行...等待回合结束");
                functions.delay();
            }else {
                if (mode == 1){
                    functions.delay();
                    use_skill(str);
                }
                else if(mode == 2){
                    functions.delay();
                    use_skill_ch(str,loc,threestar);
                }

            }

        }
    }

    //设置技能的回合
    public void fight_inround_data() throws Exception {
        /*
        判读回合是否结束或战斗是否结束 并调用使用技能
        战斗结束则结束该方法 否则一直循环
        str: 技能名
        mode: 模式1(找图)或者模式2(找字)
         */
        while(true){
            if(Data.is_end) break;
            if(is_endfight()) {
                Mainframe.textArea_Info.setText("战斗结束");
                functions.mouse_leftclick(484,472);
                Data.is_end = true;
                functions.delay();
                functions.mouse_leftclick(485,388);
                break;
            }else if (is_inround()) {
                Mainframe.textArea_Info.setText("回合正在进行...等待回合结束");
                functions.delay();
            }else {
                Mainframe.textArea_Info.setText("正在使用技能"+Data.skill_loc);
                use_skill_loc();
            }
        }
    }

   //图
    public void use_skill(String str) throws Exception {
        /*
        使用大漠找图识图 使用技能
         */
        String pic_skill = str + ".bmp";
        Data.FIND_PARTIAL = 0.8;
        int x[] = functions.find_pic(pic_skill);
        if(x[0] >= 0 && x[1] >= 0){
            Mainframe.textArea_Info.setText("正在使用技能：" + str);
            functions.mouse_leftclick(x[0], x[1]);

        }else{
            Mainframe.textArea_Info.setText("技能" + str + "无pp，即将补充pp");
            functions.delay();
            use_pp();
        }
        functions.mouse_leftclick(Data.centerx, Data.centery);
    }

    //设置的位置
    public void use_skill_loc() throws Exception {
        /*
        使用大漠找图识图 使用技能
         */

            if (Data.threestar) {
                if (is_threestar()) {
                    Mainframe.textArea_Info.setText("使用三星技能");
                    functions.mouse_leftclick(Data.threestarx, Data.threestary);
                    functions.mouse_leftclick(Data.centerx, Data.centery);
                    return;
                }
            }
            if (is_pp()) {
                if (Data.skill_loc == 1) {
                    functions.mouse_leftclick(885, 280);
                } else if (Data.skill_loc == 2) {
                    functions.mouse_leftclick(885, 333);
                }
            } else {
                Mainframe.textArea_Info.setText("技能" + "无pp，即将补充pp");
                functions.delay();
                use_pp();
            }
            functions.mouse_leftclick(Data.centerx, Data.centery);
        }


    //字
    public void use_skill_ch(String str,int loc,boolean threestar) throws Exception {
        /*
        使用大漠找字 并使用技能
         */
        Data.FIND_CH_COLOR = "ffffff";
        Data.FIND_PARTIAL = 1;
        for(int i = 0;i < 3;i++){
            int x[] = functions.find_str(str);
            if(threestar){
                if(is_threestar()){
                    Mainframe.textArea_Info.setText("使用三星技能" );
                    functions.mouse_leftclick(Data.threestarx,Data.threestary);
                    functions.mouse_leftclick(Data.centerx, Data.centery);
                    break;
                }
            }
            if (x[0]>=0 && x[1]>=0){
                if (is_pp()){
                    Mainframe.textArea_Info.setText("使用技能:" + str);
                    functions.mouse_leftclick(x[0], x[1]);
                }else{
                    Mainframe.textArea_Info.setText("技能: " + str + " 无pp");
                    use_pp();
                }
                functions.mouse_leftclick(Data.centerx, Data.centery);
                break;
            }else{
                Mainframe.textArea_Info.setText("识字失败");
                functions.delay();
            }
        }
    }

    public boolean is_inround() throws Exception {
        return !"64fcfd".equals(Data.dm.GetColor(860,51) );
    }
    public boolean is_endfight() throws Exception{
        if("ffffff".equals(Data.dm.GetColor(484,472) ) ||
                "ced5da".equals(Data.dm.GetColor(492,395)) ){
            Fight.success = true;
            return true;
        }if("ffffff".equals(Data.dm.GetColor(479,476))){
            Fight.success = false;
            return true;
        }
        return  false;
    }

    public boolean is_pp() throws Exception {
       /*
       判断有没有坐标为(x,y)的技能是否有pp
       */
        switch (Data.skill_loc){
            case 1:
                return "3f6aea".equals(Data.dm.GetColor(885,280) ) ||
                        "437af5".equals(Data.dm.GetColor(885,280) );
            case 2:
                return "3f6aea".equals(Data.dm.GetColor(885,333) ) ||
                        "437af5".equals(Data.dm.GetColor(885,333) );
        }
        return false;

    }

    public boolean is_threestar() throws Exception {
        return !"737798".equals(Data.dm.GetColor(940,253) );
    }

    public void  use_pp() throws Exception {
        /*
        使用pp药剂 按照初级、中级、高级判断并使用
         */
        use_props();

        String pic_min_pp = "初级pp药剂.bmp";
        String pic_mid_pp = "中级pp药剂.bmp";
        String pic_max_pp = "高级pp药剂.bmp";
        Data.FIND_PARTIAL = 0.7;
        int x[] = functions.find_pic(pic_min_pp);
        if (x[0] >= 0 && x[1] >= 0){
            Mainframe.textArea_Info.setText("正在补充初级pp药剂");
            functions.mouse_leftclick(x[0], x[1]);
            functions.delay();
        }else {
            Mainframe.textArea_Info.setText("无初级pp药剂");
            x = functions.find_pic(pic_mid_pp);
            if (x[0] >= 0 && x[1] >= 0) {
                Mainframe.textArea_Info.setText("正在补充中级pp药剂");
                functions.mouse_leftclick(x[0], x[1]);
                functions.delay();
            }else{
                    Mainframe.textArea_Info.setText("无中级pp药剂");
                    x = functions.find_pic(pic_max_pp);
                    if (x[0] >= 0 && x[1] >= 0) {
                        Mainframe.textArea_Info.setText("正在补充高级pp药剂");
                        functions.mouse_leftclick(x[0], x[1]);
                        functions.delay();
                    } else {
                        Mainframe.textArea_Info.setText("无pp药剂或出错");
                        functions.delay();
                        Mainframe.textArea_Info.setText("请尝试购买其他pp药剂");

                    }
            }

        }

    }




    public void use_props() throws Exception {
        /*
        点击战斗中的道具按键 被use_pp 等方法调用
        */
        String pic_props = "道具.bmp";
        Data.FIND_PARTIAL = 1;
        while(true){
            int x[] = functions.find_pic(pic_props);
            if (x[0] >= 0 && x[1] >= 0){
                functions.mouse_leftclick(x[0], x[1]);
                Mainframe.textArea_Info.setText("点击道具");
                functions.delay(1);
                break;
            }else{
                Mainframe.textArea_Info.setText("点击道具失败,正在重试");
            }
            functions.delay(0.3);
        }
    }


}
