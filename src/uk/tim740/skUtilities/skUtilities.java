package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import uk.tim740.skUtilities.util.EffReloadConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Objects;

public class skUtilities extends JavaPlugin {

    @Override
    public void onEnable() {
        long s = System.currentTimeMillis();
        Skript.registerAddon(this);
        getDataFolder().mkdirs();
        saveDefaultConfig();
        if (!(getConfig().getInt("configVersion") == 9) || !(getConfig().isSet("configVersion"))) {
            try {
                Path pth = Paths.get(getDataFolder().getAbsolutePath() + File.separator + "config.yml");
                Path ptho = Paths.get(getDataFolder().getAbsolutePath() + File.separator + "config.old");
                if (Files.exists(ptho)) {
                    Files.delete(ptho);
                }
                pth.toFile().renameTo(ptho.toFile());
                saveDefaultConfig();
                prSysI("");
                prSysI("You where using an old version of the config!");
                prSysI("It was copied and renamed to 'config.old'");
                prSysI("A new config has been generated!");
                prSysI("New config has reset to default options!");
                prSysI("");
            } catch (Exception x) {
                skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
            }
        }
        String ls = "";
        if (getConfig().getBoolean("loadConversions", true)) ls += "Conversions,"; Reg.convert();
        if (getConfig().getBoolean("loadFiles", true)) ls += " Files,"; Reg.files();
        if (getConfig().getBoolean("loadYaml", true)) ls += " Yaml,"; Reg.yaml();
        if (getConfig().getBoolean("loadUrls", true)) ls += " Urls,"; Reg.url();
        if (getConfig().getBoolean("loadUtilities", true)) ls += " Utilities"; Reg.utils();
        Skript.registerEffect(EffReloadConfig.class, "reload %string%'s config", "reload config of %string%");

        if (getConfig().getBoolean("checkForUpdates", true)) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::updateChk, 1L, 864000L);
        } else {
            prSysI("Checking for updates is disabled, you should consider enabling it again!");
        }

        try {
            MetricsLite mcs = new MetricsLite(this);
            mcs.start();
        } catch (Exception x) {
            skUtilities.prSysE("Failed to submit stats to Metrics, MCStats could be down!", getClass().getSimpleName(), x);
        }
        prSysI("loaded modules (" + ls + ") in " + (System.currentTimeMillis() - s) + "ms");
    }

    private void updateChk() {
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver").openStream()));
            String v = ur.readLine();
            ur.close();
            if (!Objects.equals(getVer(), v)) {
                prSysI("A new version of the skUtilities is out v" + v);
                if (getConfig().getBoolean("downloadUpdates", true)) {
                    String dln = ("plugins" + File.separator + "skUtilities" + File.separator + "skUtilities.v" + v + ".jar");
                    if (!new File(dln).exists()) {
                        prSysI("Starting download of skUtilities v" + v);
                        downloadFile(Paths.get(dln), "https://github.com/tim740/skUtilities/releases/download/v" + v + "/skUtilities.v" + v + ".jar");
                        prSysI("Finished download of 'skUtilities v" + v + ".jar' located in 'plugins/skUtilities'");
                        if (getConfig().getBoolean("downloadChangelog", true)) {
                            String dlnc = ("plugins" + File.separator + "skUtilities" + File.separator + "skUtilities_v" + v + "_Changelog.sk");
                            if (!new File(dlnc).exists()) {
                                downloadFile(Paths.get(dlnc), "https://github.com/tim740/skUtilities/releases/download/v" + v + "/skUtilities_v" + v + "_Changelog.sk");
                            }
                            prSysI("View changelog for 'skUtilities_v" + v + "_Changelog.sk' in directory 'plugins/skUtilities'");
                        } else {
                            prSysI("View changelog here: 'https://github.com/tim740/skUtilities/releases/latest'");
                        }
                    } else {
                        prSysU("Latest version (v" + v + ") has already been downloaded and ready to use.");
                    }
                } else {
                    prSysU("Download v" + v + ": 'https://github.com/tim740/skUtilities/releases/latest'");
                    prSysI("Option: 'downloadUpdates' is disabled in the config.");
                }
            }
        } catch (Exception e) {
            prSysE("Failed to get latest version number, you might be offline!", "Main", e);
        }
    }
    private void prSysU(String s) {
        if (Bukkit.getPluginManager().getPlugin("skUtilities").getConfig().getBoolean("broadcastUpdates", true)) {
            Bukkit.broadcast(ChatColor.AQUA + "[skUtilities: Update] " + ChatColor.GRAY + s, "skUtilities.update");
        }
        prSysI(s);
    }

    public static void prSysE(String s, String c) {
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + ": " + s + " (" + c + ".class)");
        if (Bukkit.getPluginManager().getPlugin("skUtilities").getConfig().getBoolean("broadcastErrors", true)) {
            Bukkit.broadcast(ChatColor.RED + "[skUtilities: WARN]" + ChatColor.GRAY + " v" + getVer() + ": " + s + " (" + c + ".class)", "skUtilities.error");
        }
    }

    public static void prSysE(String s, String c, Exception e) {
        if (Bukkit.getPluginManager().getPlugin("skUtilities").getConfig().getBoolean("debug", true)) {
            e.printStackTrace();
        } else {
            prSysE(s, c);
        }
    }

    public static void prSysI(String s) {
        Bukkit.getServer().getLogger().info("[skUtilities] v" + getVer() + ": " + s);
    }

    private static String getVer() {
        return Bukkit.getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
    }

    public static String getFileSize(double i) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (i < 1024) {
            return (i + " B").replaceFirst(".0", "");
        } else if (i < 1048576) {
            return df.format(i / 1024) + " KB";
        } else if (i < 1073741824) {
            return df.format(i / 1048576) + " MB";
        } else if (i < 1099511627776L) {
            return df.format(i / 1073741824) + " GB";
        } else {
            return df.format(i / 1099511627776L) + " TB";
        }
    }

    public static void downloadFile(Path pth, String url) {
        try {
            Files.copy(new URL(url).openStream(), pth);
        } catch (FileAlreadyExistsException x) {
            skUtilities.prSysE("File Already Exists: '" + pth + "' cannot download into a file that already exists!", "Utils", x);
        } catch (Exception x) {
            skUtilities.prSysE("Error downloading from: '" + url + "' Is the site down?", "Utils", x);
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
