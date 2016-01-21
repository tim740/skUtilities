package uk.tim740.skConvert;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.tim740.skConvert.load.*;

public class Main extends JavaPlugin implements Listener {

	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
		@Override
		public void onEnable() {
			 final PluginManager pluginManager = getServer().getPluginManager();
			 pluginManager.registerEvents(this, this);
			 getServer().getPluginManager().registerEvents(this, this);
			 Skript.registerAddon(this);
			 if (plugin == null){
				 plugin = this;
			 }
		Skript.registerExpression(ExprBinConvert.class,String.class,ExpressionType.PROPERTY,"convert bin[ary] %string% to (0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal)");

		Skript.registerExpression(ExprTxtToBin.class,String.class,ExpressionType.PROPERTY,"convert (text|string) %string% to bin[ary]");
		Skript.registerExpression(ExprOctalToBin.class,String.class,ExpressionType.PROPERTY,"convert octal %string% to bin[ary]");
		Skript.registerExpression(ExprDecimalToBin.class,String.class,ExpressionType.PROPERTY,"convert decimal %string% to bin[ary]");
		Skript.registerExpression(ExprHexaToBin.class,String.class,ExpressionType.PROPERTY,"convert hexa[decimal] %string% to bin[ary]");

		Skript.registerExpression(ExprAsciiToTxt.class,String.class,ExpressionType.PROPERTY,"convert ascii %string% to (text|string)");
		Skript.registerExpression(ExprTxtToAscii.class,String.class,ExpressionType.PROPERTY,"convert (text|string) %string% to ascii");

		Skript.registerExpression(ExprHexaToNum.class,String.class,ExpressionType.PROPERTY,"convert hexa[decimal] %string% to num[ber]");
		Skript.registerExpression(ExprNumToHexa.class,String.class,ExpressionType.PROPERTY,"convert num[ber] %string% to hexa[decimal]");

		Skript.registerExpression(ExprHexToRgb.class,String.class,ExpressionType.PROPERTY,"convert hex %string% to rgb");
		Skript.registerExpression(ExprUnixToDate.class,String.class,ExpressionType.PROPERTY,"convert unix[ date] %string% to date");

		getLogger().info(ChatColor.YELLOW + "[skConvert] v" + Bukkit.getServer().getPluginManager().getPlugin("skConvert").getDescription().getVersion() + " has loaded successfully!");
		}
}

