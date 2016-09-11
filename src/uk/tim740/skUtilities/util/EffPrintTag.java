package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 02/03/16
 */
public class EffPrintTag extends Effect{
	private Expression<String> str;
    private int ty;

	@Override
	protected void execute(Event e) {
        if (ty == 0){
            Bukkit.getServer().getLogger().info(ChatColor.translateAlternateColorCodes('&', str.getSingle(e)));
        }else if (ty == 1){
            Bukkit.getServer().getLogger().warning(ChatColor.translateAlternateColorCodes('&', str.getSingle(e)));
        }else{
            Bukkit.getServer().getLogger().severe(ChatColor.translateAlternateColorCodes('&', str.getSingle(e)));
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        str = (Expression<String>) e[0];
        ty = p.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}