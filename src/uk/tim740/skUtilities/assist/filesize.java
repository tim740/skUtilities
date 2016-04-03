package uk.tim740.skUtilities.assist;

import java.text.DecimalFormat;

/**
 * Created by tim740 on 03/04/2016
 */
public class FileSize {
    public static String getSize(double s){
        DecimalFormat df = new DecimalFormat("#.##");
        if (s <1024){
            return df.format(s) + "Bytes";
        }else if (s <1048576){
            return df.format(s /1024) + "KB";
        }else if (s <1073741824) {
            return df.format(s /1048576) + "MB";
        }else if (s <1099511627776L){
            return df.format(s /1073741824) + "GB";
        }else{
            return df.format(s /1099511627776L) + "TB";
        }
    }
}
