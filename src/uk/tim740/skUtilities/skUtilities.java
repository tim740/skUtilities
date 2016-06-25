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

import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getScheduler;

public class skUtilities extends JavaPlugin {

	@Override
	public void onEnable() {
        long s = System.currentTimeMillis();
        Skript.registerAddon(this);
        getDataFolder().mkdirs();
        saveDefaultConfig();
        if (!(getConfig().getInt("configVersion") == 3)){
            resetConfig();
        }
        if (!getConfig().contains("configVersion")){
            resetConfig();
        }
        if (getConfig().getBoolean("loadConversions", true)){
            RegConvert.regC();
        }
        if (getConfig().getBoolean("loadUtilities", true)){
            RegUtil.regU();
            RegUtil.regUE();
        }
        if (getConfig().getBoolean("loadFiles", true)) {
            RegFiles.regF();
            RegFiles.regFE();
        }
        RegConfig.regCo();
        if (getConfig().getBoolean("checkForUpdates", true)) {
            getScheduler().scheduleSyncRepeatingTask(this, this::updateChk, 1L, 864000L);
        }else{
            prSysi("It seems like you've disabled updates, you should consider enabling them again!");
    }

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (Exception e) {
            skUtilities.prSys("Failed to submit stats to Metrics", getClass().getSimpleName(), 0);
        }

        prSysi("Has fully loaded in " + (System.currentTimeMillis() - s) + "ms!");
    }

    public static void prSys(String s, String c, Integer t) {
        if (t == 0){
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + ": " + s + " ("+ c +".class)");
            Bukkit.broadcast(ChatColor.RED + "[skUtilities: ERROR]" + ChatColor.GRAY + " v" + getVer() + ": " + s + " ("+ c +".class)", "skUtilities.error");
        }else if (t == 1){
            Bukkit.getServer().getLogger().warning("[skUtilities] v" + getVer() + ": "  + s + " ("+ c +".class)");
            Bukkit.broadcast(ChatColor.GOLD + "[skUtilities: WARNING]" + ChatColor.GRAY+ " v" + getVer() + ": " + s + " ("+ c +".class)", "skUtilities.warning");
        }
    }
    public static void prSysi(String s){
        Bukkit.getServer().getLogger().info("[skUtilities] v" + getVer() + ": "  + s);
    }
    static void loadErr(String s){
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + " - Failed to load:  " + s + ", due to not using 1.9+!");
    }

    private void updateChk(){
        prSysi("Checking for update now you will be notified if there is an update!");
        String v = "";
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver").openStream()));
            v = ur.readLine();
            ur.close();
        } catch (Exception e) {
            prSys("Error while checking for update!", "Main", 0);
        }
        if (!Objects.equals(getVer(), v)){
            prSysi("A new version of the addon is out v" + v);
            if (getConfig().getBoolean("downloadUpdates", true)) {
                String dln = "plugins" + File.separator + "skUtilities" + File.separator + "skUtilities.v" + v + ".jar";
                if (!new File(dln).exists()) {
                    prSysi("Downloading latest version!");
                    Utils.downloadFile(new File(dln), "https://github.com/tim740/skUtilities/releases/download/v" + v + "/skUtilities.v" + v + ".jar");
                    prSysi("Latest version has been downloaded!");
                }else{
                    prSysi("Latest version of skUtilities (v" + v + ") is already downloaded and ready to use!");
                }
            }else{
                prSysi("You can find the latest version here: https://github.com/tim740/skUtilities/releases/latest");
            }
        }else{
            prSysi("It seems like your using the latest version!");
        }
    }
    private static String getVer(){
        return getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
    }
    private void resetConfig(){
        File pth = new File(getDataFolder().getAbsolutePath() + File.separator + "config.yml");
        File ptho = new File(getDataFolder().getAbsolutePath() + File.separator + "config.old");

        if (ptho.exists()){
            ptho.delete();
        }
        pth.renameTo(ptho);
        saveDefaultConfig();

        prSysi("");
        prSysi("You where using an old version of the config!");
        prSysi("It was copied and renamed to 'config.old'");
        prSysi("A new config has been generated!");
        prSysi("Sorry but all the options have been reset.");
        prSysi("");
    }
}
