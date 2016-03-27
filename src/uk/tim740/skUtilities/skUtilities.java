package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

public class skUtilities extends JavaPlugin {

	@Override
	public void onEnable() {
        long s = System.currentTimeMillis();
        Skript.registerAddon(this);
        this.saveDefaultConfig();
        getDataFolder().mkdirs();
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
        if (getConfig().getBoolean("loadFiles", true)) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, this::updateChk, 1L, 864000L);
        }else{
            skUtilities.prEW("It seems like you've disabled updates, you should consider enabling them again!", "Main", 1);
        }
        getLogger().info("v" + getVer() + " has fully loaded in " + (System.currentTimeMillis() - s) + "ms!");
    }
    private static String getVer(){
        return Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
    }

    public static void prEW(String s, String c, Integer t) {
        if (t == 0){
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + ": " + s + " ("+ c +".class)");
            Bukkit.broadcast(ChatColor.RED + "[skUtilities: ERROR]" + ChatColor.GRAY + " v" + getVer() + ": " + s + ChatColor.AQUA + " ("+ c +".class)", "skUtilities.error");
        }else{
            Bukkit.getServer().getLogger().warning("[skUtilities] v" + getVer() + ": "  + s + " ("+ c +".class)");
            Bukkit.broadcast(ChatColor.GOLD + "[skUtilities: WARNING]" + ChatColor.GRAY+ " v" + getVer() + ": " + s + ChatColor.AQUA + " ("+ c +".class)", "skUtilities.warning");
        }
    }
    static void loadErr(String s){
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + " - Failed to load:  " + s + ", due to being on 1.8!");
    }

    private void updateChk(){
        skUtilities.prEW("Checking for update now you will be notified if there is an update!", "Main", 1);
        String v = "";
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("https://raw.githubusercontent.com/tim740/skUtilities/master/latest.ver").openStream()));
            v = ur.readLine();
            ur.close();
        } catch (Exception e) {
            skUtilities.prEW(e.getCause().getMessage(), "Main", 0);
        }
        if (!Objects.equals(getVer(), v)){
            skUtilities.prEW("A new version of the aliases is out v" + v, "Main", 1);
            skUtilities.prEW("You can find the latest version here: https://github.com/tim740/skUtilities/releases/latest", "Main", 1);
        }else{
            skUtilities.prEW("It seems like your using the latest version!", "Main", 1);
        }
    }
}
