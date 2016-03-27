package uk.tim740.skUtilities.util;

import ch.njol.skript.aliases.Aliases;
import ch.njol.skript.lang.*;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 27/03/16
 */
public class EffSkReloadAliases extends Effect{

	@Override
	protected void execute(Event arg0) {
            Aliases.clear();
            Aliases.load();
	}

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}