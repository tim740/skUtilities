package uk.tim740.skUtilities.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
/**
 * Made by @Sashie - Tweaked and Optimised by @tim740
 */
public class EffPacketTrial extends Effect {
	private Expression<Player> player;
	@Override
	protected void execute(@Nullable Event e) {
            String p = Bukkit.getServer().getClass().getPackage().getName();
            String ver = p.substring(p.lastIndexOf(".") + 1, p.length());
            try {
                Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + ver + ".entity.CraftPlayer");
                Class<?> PacketPlayOutGameStateChange = Class.forName("net.minecraft.server." + ver + ".PacketPlayOutGameStateChange");
                Class<?> Packet = Class.forName("net.minecraft.server." + ver + ".Packet");
                Constructor<?> playOutConstructor = PacketPlayOutGameStateChange.getConstructor(Integer.TYPE, Float.TYPE);
                Object packet = playOutConstructor.newInstance(5, 0);
                Object craftPlayerObject = craftPlayer.cast(player.getSingle(e));
                Method getHandleMethod = craftPlayer.getMethod("getHandle", (Class<?>[])new Class[0]);
                Object handle = getHandleMethod.invoke(craftPlayerObject, new Object[0]);
                Object pc = handle.getClass().getField("playerConnection").get(handle);
                Method sPM = pc.getClass().getMethod("sendPacket", Packet);
                sPM.invoke(pc, packet);
            }
            catch (Exception ex) {
            	Skript.warning("[skUtilities] Error: (PacketTrial) Player didn't have a compatible version of Minecraft.");
            }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult){
        this.player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return this.getClass().getName();
    }
}