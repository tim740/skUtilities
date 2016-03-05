package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class skUtilities extends JavaPlugin {

	@Override
	public void onEnable() {
        Skript.registerAddon(this);
        RegConvert.regC();
        RegUtil.regU();
        Bukkit.getServer().getLogger().info("[skUtilities] v" + Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " has fully loaded!");
    }
    public static void prErr(String s, String c) {
        String ver = Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + ver + ": "  + s + " ("+ c +")");
        Bukkit.broadcast("(ERROR) [skUtilities] v" + ver + ": " + s + " ("+ c +")", "skUtilities.error");
    }
}
