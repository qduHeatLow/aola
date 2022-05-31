package Main.utils;

import Main.services.Mainframe;
import Main.services.functions;
import sun.applet.Main;

import java.lang.reflect.Field;

/**
 * @Auth yaozhongjie
 * @Date 2019/6/25 11:30
 **/
public class JdmUtil {


    public static void setLibPath(){
        try{

            String libraryPath= functions.getPath()+"lib";
            //String libraryPath= "D:\\aola\\lib";
            Field userPathsField = ClassLoader.class.getDeclaredField("usr_paths");
            userPathsField.setAccessible(true);
            String[] paths = (String[]) userPathsField.get(null);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.length; i++) {
                if (libraryPath.equals(paths[i])) {
                    continue;
                }
                sb.append(paths[i]).append(';');
            }
            sb.append(libraryPath);
            System.setProperty("java.library.path", sb.toString());
            final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
            //Mainframe.textArea_Info.setText(libraryPath+"123");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}
