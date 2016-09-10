package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import uk.tim740.skUtilities.util.*;

/**
 * Created by tim740 on 22/02/2016
 */
class RegUtil {
    static void regU() {
        Skript.registerEffect(EffReloadWhitelist.class, "reload whitelist");
        Skript.registerExpression(SExprWhitelist.class, OfflinePlayer.class, ExpressionType.PROPERTY, "whitelist");
        Skript.registerCondition(CondServerWhitelist.class, "server is whitelisted", "server is(n't| no)t whitelisted");
        Skript.registerCondition(CondPlayerWhitelist.class, "%player% is whitelisted", "%player% is(n't| no)t whitelisted");

        Skript.registerExpression(ExprServerIP.class, String.class, ExpressionType.PROPERTY, "[skutil ]ip of server", "[skutil ]server's ip");
        Skript.registerExpression(ExprLoaded.class, Number.class, ExpressionType.PROPERTY, "number of[ loaded] (0¦(commands|cmds)|1¦functions|2¦s(c|k)ripts|3¦triggers|4¦statements|5¦variables|6¦aliases|7¦events|8¦effects|9¦expressions|10¦conditions)");
        Skript.registerExpression(ExprLoadedList.class,String.class,ExpressionType.PROPERTY,"(0¦plugins|1¦addons) list", "list of (0¦plugins|1¦addons)");
        Skript.registerExpression(ExprVersion.class, String.class, ExpressionType.PROPERTY, "%string%'s version", "version of %string%");
        Skript.registerExpression(ExprSysTime.class, Integer.class, ExpressionType.PROPERTY, "[current ]system (0¦nanos[econds]|1¦millis[econds]|2¦seconds)");
        Skript.registerExpression(ExprRam.class, Number.class, ExpressionType.PROPERTY, "[skutil ](0¦free|1¦total|2¦max) (ram|memory)");
        Skript.registerExpression(ExprFontNames.class, String.class, ExpressionType.PROPERTY, "[all ][system ]font names");
        Skript.registerExpression(ExprCaseLength.class, Number.class, ExpressionType.PROPERTY, "number of (0¦upper|1¦lower)case char[acter]s in %string%");

        Skript.registerEffect(EffDemoMode.class, "send[ fake] trial packet to %player%");
        Skript.registerEffect(EffPrintTag.class, "print (0¦info|1¦warning|2¦error) %string% to console");
        Skript.registerEffect(EffRunOpCmd.class, "(force|make) %player% run (cmd|command) %string% as op");
        Skript.registerEffect(EffSkReloadAliases.class, "skript reload aliases");
        Skript.registerEffect(EffReloadSkript.class, "reload s(k|c)ript %string%");
        Skript.registerEffect(EffRestartServer.class, "re(0¦start|1¦load) server");

        Skript.registerCondition(CondStartsEndsWith.class, "%string% (0¦starts|1¦ends) with %-string%", "%string% does(n't| not) (0¦start|1¦end) with %-string%");

        if (Bukkit.getVersion().contains("(MC: 1.9") || Bukkit.getVersion().contains("(MC: 1.1")) {
            RegUtil19.regU();
        } else {
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + Bukkit.getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " - Unable to load: TypeProfession, requires 1.9+!");
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + Bukkit.getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " - Unable to load: EffVillagerProfession, requires 1.9+!");
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + Bukkit.getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " - Unable to load: SExprGlideMode, requires 1.9+!");
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + Bukkit.getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " - Unable to load: EvtCauldronLevelChange, requires 1.9+!");
            Bukkit.getServer().getLogger().severe("[skUtilities] v" + Bukkit.getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " - Unable to load: EvtGlideToggle, requires 1.9+!");
        }
    }
}
