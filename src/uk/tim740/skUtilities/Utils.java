package uk.tim740.skUtilities;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static void downloadFile(Path pth, String url) {
        try {
            Files.copy(new URL(url).openStream(), pth);
        } catch (MalformedURLException x) {
            skUtilities.prSysE("Error downloading from: '" + url + "' Is the site down?", "Utils", x);
        } catch (IOException x) {
            skUtilities.prSysE(x.getMessage(), "Utils", x);
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
