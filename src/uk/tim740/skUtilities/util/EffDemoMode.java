package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Made by @Sashie - Tweaked by @tim740
 */
public class EffDemoMode extends Effect {
	private Expression<Player> u;

	@Override
	protected void execute(@Nullable Event e) {
        String p = Bukkit.getServer().getClass().getPackage().getName();
        String ver = p.substring(p.lastIndexOf(".") + 1, p.length());
        try {
            Class<?> cPlayer = Class.forName("org.bukkit.craftbukkit." + ver + ".entity.CraftPlayer");
            Class<?> PacketPlayOutGameStateChange = Class.forName("net.minecraft.server." + ver + ".PacketPlayOutGameStateChange");
            Constructor<?> playOutConstructor = PacketPlayOutGameStateChange.getConstructor(Integer.TYPE, Float.TYPE);
            Method getHandleMethod = cPlayer.getMethod("getHandle");
            Object handle = getHandleMethod.invoke(cPlayer.cast(u.getSingle(e)));
            Object pc = handle.getClass().getField("playerConnection").get(handle);
            Method sPM = pc.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server." + ver + ".Packet"));
            sPM.invoke(pc, playOutConstructor.newInstance(5, 0));
        } catch (Exception x) {
            skUtilities.prSysE("Player didn't have a compatible version of Minecraft!", getClass().getSimpleName(), x);
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p){
        u = (Expression<Player>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}