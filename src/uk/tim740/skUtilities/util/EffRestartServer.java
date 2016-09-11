package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 02/04/16
 */
public class EffRestartServer extends Effect{
    private int ty;

	@Override
	protected void execute(Event e) {
        if (ty == 0) {
            Bukkit.getServer().spigot().restart();
        }else{
            Bukkit.getServer().reload();
        }
    }

    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        ty = p.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}