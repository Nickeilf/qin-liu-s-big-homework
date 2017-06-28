package nju.edu.courselab.Util;

import android.util.Base64;

/**
 * Created by nick on 2017/6/23.
 */
public class BaseEncoder {

    public static String encodeString(String str){
        str =str.replaceAll("\\s+","");
        String encodedString = Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
        return encodedString;
    }
}
