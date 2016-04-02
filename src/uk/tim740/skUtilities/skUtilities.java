package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

import static org.bukkit.Bukkit.getPluginManager;

public class skUtilities extends JavaPlugin {

	@Override
	public void onEnable() {
        long s = System.currentTimeMillis();
        Skript.registerAddon(this);
        getDataFolder().mkdirs();
        saveDefaultConfig();
        if (!getConfig().contains("configVersion") || getConfig().getInt("configVersion") != 1){
            File pth = new File("plugins" + File.separator + "skUtilities" + File.separator + "config.yml");
            pth.renameTo(new File("plugins" + File.separator + "skUtilities" + File.separator + "config.old"));
            saveDefaultConfig();
            prSys("You where using an old version of the config, It was copied and renamed to 'config.old' A new config has been generated!", "Main", 2);
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
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::updateChk, 1L, 864000L);
        }else{
            prSysi("It seems like you've disabled updates, you should consider enabling them again!");
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
    private void prSysi(String s){
        getLogger().info("v" + getVer() + ": "  + s);
    }
    static void loadErr(String s){
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + " - Failed to load:  " + s + ", due to being on 1.8!");
    }

    private void updateChk(){
        prSysi("Checking for update now you will be notified if there is an update!");
        String v = "";
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver").openStream()));
            v = ur.readLine();
            ur.close();
        } catch (Exception e) {
            prSys(e.getCause().getMessage(), "Main", 0);
        }
        if (!Objects.equals(getVer(), v)){
            prSysi("A new version of the addon is out v" + v);
            if (getConfig().getBoolean("downloadUpdates", true)) {
                String dln = "plugins" + File.separator + "skUtilities" + File.separator + "skUtilities.v" + v + ".jar";
                if (!new File(dln).exists()) {
                    prSysi("Downloading latest version!");
                    try {
                        ReadableByteChannel rbc = Channels.newChannel(new URL("https://github.com/tim740/skUtilities/releases/download/v" + v + "/skUtilities.v" + v + ".jar").openStream());
                        FileOutputStream fos = new FileOutputStream(dln);
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                        fos.close();
                        rbc.close();
                    } catch (Exception e) {
                        prSys(e.getMessage(), getClass().getSimpleName(), 0);
                    }
                    prSysi("Latest version has been downloaded!");
                }else{
                    prSysi("Latest version of skUtilities (v" + v + ") is already updated and ready to use!");
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
}
