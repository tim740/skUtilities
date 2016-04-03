package uk.tim740.skUtilities;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;

/**
 * Created by tim740 on 03/04/2016
 */
public class Utils {

    public static String getFileSize(double s){
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

    public static void downloadFile(File pth, String url){
        try {
            ReadableByteChannel rbc = Channels.newChannel(new URL(url).openStream());
            FileOutputStream fos = new FileOutputStream(pth);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (Exception e) {
            skUtilities.prSys(e.getMessage(), "Utils", 0);
        }
    }
}
