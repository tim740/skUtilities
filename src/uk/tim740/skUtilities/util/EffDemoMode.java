package uk.tim740.skUtilities.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import uk.tim740.skUtilities.skUtilities;

/**
 * Made by @Sashie - Tweaked by @tim740
 */
public class EffDemoMode extends Effect {
	private Expression<Player> player;
	@Override
	protected void execute(@Nullable Event e) {
        String p = Bukkit.getServer().getClass().getPackage().getName();
        String ver = p.substring(p.lastIndexOf(".") + 1, p.length());
        try {
            Class<?> cPlayer = Class.forName("org.bukkit.craftbukkit." + ver + ".entity.CraftPlayer");
            Class<?> PacketPlayOutGameStateChange = Class.forName("net.minecraft.server." + ver + ".PacketPlayOutGameStateChange");
            Constructor<?> playOutConstructor = PacketPlayOutGameStateChange.getConstructor(Integer.TYPE, Float.TYPE);
            Method getHandleMethod = cPlayer.getMethod("getHandle", (Class<?>[])new Class[0]);
            Object handle = getHandleMethod.invoke(cPlayer.cast(player.getSingle(e)));
            Object pc = handle.getClass().getField("playerConnection").get(handle);
            Method sPM = pc.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server." + ver + ".Packet"));
            sPM.invoke(pc, playOutConstructor.newInstance(5, 0));
        }
        catch (Exception ex) {
            skUtilities.prEW("Player didn't have a compatible version of Minecraft!", getClass().getSimpleName(), 0);
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult){
        player = (Expression<Player>) exprs[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return getClass().getName();
    }
}