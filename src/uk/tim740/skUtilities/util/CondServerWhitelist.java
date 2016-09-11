package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 05/04/2016
 */
public class CondServerWhitelist extends Condition {

    @Override
    public boolean check(Event e) {
        Boolean chk = Bukkit.hasWhitelist();
        return (isNegated() ? !chk : chk);
    }

    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        setNegated(i == 1);
        return true;
    }
    @Override
    public String toString(Event e, boolean b) {
        return getClass().getName();
    }


}
