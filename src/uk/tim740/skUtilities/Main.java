package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uk.tim740.skUtilities.convert.RegConvert;
import uk.tim740.skUtilities.util.RegUtil;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
        Skript.registerAddon(this);
        RegConvert.regC();
        RegUtil.regU();
    }
    public static void prErr(String s) {
        Bukkit.getServer().getLogger().severe("[skUtilities] " + s);
    }
}
