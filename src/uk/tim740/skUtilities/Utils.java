package uk.tim740.skUtilities;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 * Created by tim740 on 03/04/2016
 */
public class Utils {

    public static String getFileSize(double s) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (s < 1024) {
            return (s + " B").replaceFirst(".0", "");
        } else if (s < 1048576) {
            return df.format(s / 1024) + " KB";
        } else if (s < 1073741824) {
            return df.format(s / 1048576) + " MB";
        } else if (s < 1099511627776L) {
            return df.format(s / 1073741824) + " GB";
        } else {
            return df.format(s / 1099511627776L) + " TB";
        }
    }

    public static void downloadFile(File pth, String url) {
        try {
            ReadableByteChannel rbc = Channels.newChannel(new URL(url).openStream());
            FileOutputStream fos = new FileOutputStream(pth);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (Exception e) {
            skUtilities.prSysE(e.getMessage(), "Utils", e);
        }
    }

    public static String getDefaultPath(String pth) {
        if (!Bukkit.getPluginManager().getPlugin("skUtilities").getConfig().getBoolean("useRootAsDefaultPath", false)) {
            String dp = Paths.get("").normalize().toAbsolutePath().toString();
            if (pth.contains(dp)) {
                return (pth + File.separator);
            } else {
                return (dp + File.separator + pth);
            }
        } else {
            return pth;
        }
    }

}
