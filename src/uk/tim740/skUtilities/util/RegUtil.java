package uk.tim740.skUtilities.util;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.CauldronLevelChangeEvent;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 22/02/2016
 */
public class RegUtil {
    public static void regU() {
        Skript.registerExpression(ExprLoaded.class,Number.class, ExpressionType.PROPERTY,"number of[ loaded] (0¦(commands|cmds)|1¦functions|2¦s(c|k)ripts|3¦triggers|4¦statements|5¦variables|6¦aliases|7¦plugins|8¦addons|9¦events|10¦effects|11¦expressions|12¦conditions)");
        Skript.registerExpression(ExprGenerateTxt.class,String.class,ExpressionType.PROPERTY,"generate[ random] string with length %integer%");
        Skript.registerExpression(ExprWorldUtil.class,String.class,ExpressionType.PROPERTY,"[world ](0¦(dimension|environment)|1¦type) of %world%", "%world%'s [world ](0¦(dimension|environment)|1¦type)");
        Skript.registerEffect(EffPacketTrial.class, "send[ fake] trial packet to %player%");
        Skript.registerEffect(EffRunScript.class, "run script at %string%");
        Skript.registerEffect(EffVillagerProfession.class, "spawn a %entity% with profession (0¦farmer|1¦librarian|2¦priest|3¦blacksmith|4¦butcher) at %location%");
        Skript.registerEvent("CauldronLevelChange", SimpleEvent.class, CauldronLevelChangeEvent.class, "cauldron[ water] level change");
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Integer.class, new Getter<Integer,CauldronLevelChangeEvent>() {
            @Nullable
            @Override
            public Integer get(CauldronLevelChangeEvent e) {
                return e.getNewLevel();
            }
        }, 0);
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Player.class, new Getter<Player, CauldronLevelChangeEvent>(){
            @Nullable
            @Override
            public Player get(CauldronLevelChangeEvent e) {
                return ((Player) e.getEntity());
            }
        }, 0);
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Entity.class, new Getter<Entity, CauldronLevelChangeEvent>(){
            @Nullable
            @Override
            public Entity get(CauldronLevelChangeEvent e) {
                return e.getEntity();
            }
        }, 0);

        Bukkit.getServer().getLogger().info("[skUtilities] v" + Bukkit.getServer().getPluginManager().getPlugin("skUtilities").getDescription().getVersion() + " loaded util (100% loaded)!");
    }
}
