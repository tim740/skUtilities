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
            prSys("It seems like you've disabled updates, you should consider enabling them again!", "Main", 1);
        }
        prSys("Has fully loaded in " + (System.currentTimeMillis() - s) + "ms!", "Main", 2);
    }

    public static void prSys(String s, String c, Integer t) {
        if (t == 0){
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + ": " + s + " ("+ c +".class)");
            Bukkit.broadcast(ChatColor.RED + "[skUtilities: ERROR]" + ChatColor.GRAY + " v" + getVer() + ": " + s + " ("+ c +".class)", "skUtilities.error");
        }else if (t == 1){
            Bukkit.getServer().getLogger().warning("[skUtilities] v" + getVer() + ": "  + s + " ("+ c +".class)");
            Bukkit.broadcast(ChatColor.GOLD + "[skUtilities: WARNING]" + ChatColor.GRAY+ " v" + getVer() + ": " + s + " ("+ c +".class)", "skUtilities.warning");
        }else{
            Bukkit.getServer().getLogger().info("[skUtilities] v" + getVer() + ": "  + s);
            Bukkit.broadcast("[skUtilities: INFO]" + ChatColor.GRAY + " v" + getVer() + ": " + s, "skUtilities.warning");

        }
    }
    static void loadErr(String s){
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + " - Failed to load:  " + s + ", due to being on 1.8!");
    }

    private void updateChk(){
        prSys("Checking for update now you will be notified if there is an update!", "Main", 2);
        String v = "";
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver").openStream()));
            v = ur.readLine();
            ur.close();
        } catch (Exception e) {
            prSys(e.getCause().getMessage(), "Main", 0);
        }
        if (!Objects.equals(getVer(), v)){
            prSys("A new version of the addon is out v" + v, "Main", 1);
            if (getConfig().getBoolean("downloadUpdates", true)) {
                String dln = "plugins" + File.separator + "skUtilities" + File.separator + "skUtilities.v" + v + ".jar";
                if (!new File(dln).exists()) {
                    prSys("Downloading latest version!", "Main", 2);
                    try {
                        ReadableByteChannel rbc = Channels.newChannel(new URL("https://github.com/tim740/skUtilities/releases/download/v" + v + "/skUtilities.v" + v + ".jar").openStream());
                        FileOutputStream fos = new FileOutputStream(dln);
                        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                        fos.close();
                        rbc.close();
                    } catch (Exception e) {
                        skUtilities.prSys(e.getMessage(), getClass().getSimpleName(), 0);
                    }
                    prSys("Latest version has been downloaded!", "Main", 2);
                }else{
                    prSys("A newer version of the addon (v" + v + ") is already updated and ready to use!", "Main", 2);
                }
            }else{
                prSys("You can find the latest version here: https://github.com/tim740/skUtilities/releases/latest", "Main", 2);
            }
        }else{
            prSys("It seems like your using the latest version!", "Main", 2);
        }
    }
    private static String getVer(){
        return getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
    }
}
