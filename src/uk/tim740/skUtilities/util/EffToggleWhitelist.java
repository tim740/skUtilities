package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 05/04/16
 */
public class EffToggleWhitelist extends Effect{
    private int ty;

	@Override
	protected void execute(Event arg0) {
        if (ty == 0) {
            Bukkit.setWhitelist(true);
        }else{
            Bukkit.setWhitelist(false);
        }
    }

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        ty = arg3.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}