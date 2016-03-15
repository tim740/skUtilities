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
        if(Bukkit.getVersion().contains("(MC: 1.9)")) {
            RegEvent.regE();
        }else{
            prErr("Failed to load: ", "Events, due to being on 1.8!", 0);
        }
        Bukkit.getServer().getLogger().info("[skUtilities] v" + Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " has fully loaded!");
    }
    public static void prErr(String s, String c, Integer b) {
        String ver = Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
        Bukkit.getServer().getLogger().severe("[skUtilities] v" + ver + ": "  + s + " ("+ c +")");
        if (b == 1){
            Bukkit.broadcast("(ERROR) [skUtilities] v" + ver + ": " + s + " ("+ c +")", "skUtilities.error");
        }
    }
}
