package Main.services;

import Main.Dm.Idmsoft;
import Main.utils.JdmUtil;

/**
 * @Auth yaozhongjie
 * @Date 2019/6/25 11:22
 **/

public class Jdm {



    public static void main(String[] args) throws Exception {

        Mainframe window;
        try {
            window = new Mainframe();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Data data = new Data();





        Data.findwindow();

    }













}
