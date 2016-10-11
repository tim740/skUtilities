package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class skUtilities extends JavaPlugin {

    @Override
    public void onEnable() {
        long s = System.currentTimeMillis();
        Skript.registerAddon(this);
        getDataFolder().mkdirs();
        saveDefaultConfig();
        if (!(getConfig().getInt("configVersion") == 6) || !(getConfig().isSet("configVersion"))) {
            File pth = new File(getDataFolder().getAbsolutePath() + File.separator + "config.yml");
            File ptho = new File(getDataFolder().getAbsolutePath() + File.separator + "config.old");

            if (ptho.exists()) {
                ptho.delete();
            }
            pth.renameTo(ptho);
            saveDefaultConfig();

            prSysI("");
            prSysI("You where using an old version of the config!");
            prSysI("It was copied and renamed to 'config.old'");
            prSysI("A new config has been generated!");
            prSysI("New config has reset to default options!");
            prSysI("");
        }
        if (getConfig().getBoolean("loadConversions", true)) {
            RegConvert.regC();
        }
        if (getConfig().getBoolean("loadUtilities", true)) {
            RegUtil.regU();
        }
        if (getConfig().getBoolean("loadFiles", true)) {
            RegFiles.regF();
        }
        RegConfig.regCo();
        if (getConfig().getBoolean("checkForUpdates", true)) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::updateChk, 1L, 864000L);
        } else {
            prSysI("Checking for updates is disabled, you should consider enabling it again!");
        }

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (Exception e) {
            skUtilities.prSysE("Failed to submit stats to Metrics, MCStats could be down!", getClass().getSimpleName(), e);
        }

        prSysI("Has fully loaded in " + (System.currentTimeMillis() - s) + "ms!");
    }

    public static void prSysE(String s, String c) {
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + ": " + s + " (" + c + ".class)");
        Bukkit.broadcast(ChatColor.RED + "[skUtilities: ERROR]" + ChatColor.GRAY + " v" + getVer() + ": " + s + " (" + c + ".class)", "skUtilities.error");
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

    private void updateChk() {
        prSysI("Checking for update now you will be notified if there is an update!");
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver").openStream()));
            String v = ur.readLine();
            ur.close();
            if (!Objects.equals(getVer(), v)) {
                prSysI("A new version of the skUtilities is out v" + v);
                if (getConfig().getBoolean("downloadUpdates", true)) {
                    String dln = "plugins" + File.separator + "skUtilities" + File.separator + "skUtilities.v" + v + ".jar";
                    if (!new File(dln).exists()) {
                        prSysI("Starting download of skUtilities v" + v);
                        Utils.downloadFile(new File(dln), "https://github.com/tim740/skUtilities/releases/download/v" + v + "/skUtilities.v" + v + ".jar");
                        prSysI("Finished download of 'skUtilities v" + v + "' located in 'plugins/skUtilities'");
                        if (getConfig().getBoolean("downloadChangelog", true)) {
                            Utils.downloadFile(new File("plugins" + File.separator + "skUtilities" + File.separator + "skUtilities_v" + v + "_Changelog.sk"), "https://github.com/tim740/skUtilities/releases/download/v" + v + "/skUtilities_v" + v + "_Changelog.sk");
                            prSysI("Finished download of 'skUtilities_v" + v + "_Changelog.sk' located in 'plugins/skUtilities'");
                        } else {
                            prSysI("View changelog here: 'https://github.com/tim740/skUtilities/releases/latest'");
                        }
                    } else {
                        prSysI("Latest version of skUtilities (v" + v + ") is already downloaded and ready to use!");
                        Bukkit.broadcast(ChatColor.AQUA + "[skUtilities: Update] " + ChatColor.GRAY + "Latest version of skUtilities (v" + v + ") is already downloaded and ready to use!", "skUtilities.update");
                    }
                } else {
                    prSysI("Download latest version here: 'https://github.com/tim740/skUtilities/releases/latest'");
                    prSysI("You should consider enabling `downloadUpdates` in the config.");
                    Bukkit.broadcast(ChatColor.AQUA + "[skUtilities: Update] " + ChatColor.GRAY + "You can find the latest version here: 'https://github.com/tim740/skUtilities/releases/latest'", "skUtilities.update");
                }
            } else {
                prSysI("Currently using the latest version of skUtilities.");
            }
        } catch (Exception e) {
            prSysE("Failed to get latest version number, you might be offline!", "Main", e);
        }
    }

    private static String getVer() {
        return Bukkit.getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
    }
}
