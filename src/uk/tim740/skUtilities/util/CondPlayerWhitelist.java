package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 05/04/2016
 */
public class CondPlayerWhitelist extends Condition {
    private Expression<Player> usr;

    @Override
    public boolean check(Event e) {
        Boolean chk = usr.getSingle(e).isWhitelisted();
        return (isNegated() ? !chk : chk);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        usr = (Expression<Player>) e[0];
        setNegated(i == 1);
        return true;
    }
    @Override
    public String toString(Event e, boolean b) {
        return getClass().getName();
    }


}
