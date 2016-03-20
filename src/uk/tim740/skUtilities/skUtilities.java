package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class skUtilities extends JavaPlugin {

	@Override
	public void onEnable() {
        Skript.registerAddon(this);
        RegConvert.regC();
        RegUtil.regU();
        RegFiles.regF();
        if(Bukkit.getVersion().contains("(MC: 1.9)")) {
            RegEvent.regE();
        }else{
            loadErr("Events, due to being on 1.8!");
        }
        Bukkit.getServer().getLogger().info("[skUtilities] v" + getVer() + " has fully loaded!");
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
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + getVer() + " - Failed to load:  " + s);
    }
}
