package utils;

import javax.swing.*;
import java.awt.*;

public class ToolUtil {
    public static boolean isEmpty(String str){
        if(str != null && !"".equals(str.trim())){
            return false;
        }
        return true;
    }

}
