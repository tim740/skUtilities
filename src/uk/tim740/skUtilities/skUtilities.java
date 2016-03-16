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
            prEW("Failed to load: ", "Events, due to being on 1.8!", 0, 0);
        }
        Bukkit.getServer().getLogger().info("[skUtilities] v" + Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " has fully loaded!");
    }

    public static void prEW(String s, String c, Integer b, Integer p) {
        String ver = Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion();
        if (p == 0){
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + ver + ": "  + s + " ("+ c +")");
            if (b == 1){
                Bukkit.broadcast("(ERROR) [skUtilities] v" + ver + ": " + s + " ("+ c +")", "skUtilities.error");
            }
        }else{
            Bukkit.getServer().getLogger().warning("[skUtilities] v" + ver + ": "  + s + " ("+ c +")");
            if (b == 1){
                Bukkit.broadcast("(WARNING) [skUtilities] v" + ver + ": " + s + " ("+ c +")", "skUtilities.warning");
            }
        }
    }
}
