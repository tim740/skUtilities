package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uk.tim740.skUtilities.convert.RegConvert;
import uk.tim740.skUtilities.util.RegUtil;

public class Main extends JavaPlugin {
    private static String ver = Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion();

	@Override
	public void onEnable() {
        Skript.registerAddon(this);
        RegConvert.regC();
        RegUtil.regU();
        Bukkit.getServer().getLogger().info("[skUtilities] v" + ver + " has fully loaded!");
    }
    public static void prErr(String s, String c) {
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + ver + ": "  + s + " ("+ c +")");
        Bukkit.broadcast("(ERROR) [skUtilities] v" + ver + ": " + s + " ("+ c +")", "skUtilities.error");
    }
}
