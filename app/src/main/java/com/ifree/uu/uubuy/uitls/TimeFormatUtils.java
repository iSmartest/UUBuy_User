package com.ifree.uu.uubuy.uitls;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/30 0030
 * Description:
 */
public class TimeFormatUtils {
    public static String modifyDataFormat(String str){
        String result;
        if (str.isEmpty()){
            result = "";
        }else {
            result = str.substring(0,4) + "."+ str.substring(5,7) + "."+ str.substring(8,10);
        }
        return result;
    }

    public static String modifyDataFormat2(String str){
        String result;
        if (str.isEmpty()){
            result = "";
        }else {
            result = str.substring(0,4) + "."+ str.substring(5,7) + "."+ str.substring(8,10) + "—" + str.substring(11,15) + "." + str.substring(16,18) + "." + str.substring(19,21);
        }
        return result;
    }
}
