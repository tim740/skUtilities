package uk.tim740.skUtilities.config;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 27/03/2016
 */
public class EffReloadConfig extends Effect {
    private Expression<String> str;

    @Override
    protected void execute(Event arg0) {
        try {
            Bukkit.getPluginManager().getPlugin(str.getSingle(arg0)).reloadConfig();
        } catch (Exception e) {
            skUtilities.prSys("'" + str + "' isn't a real plugin!", getClass().getSimpleName(), 0);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
