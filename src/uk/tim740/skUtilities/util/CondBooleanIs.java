package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 26/12/2016
 */
public class CondBooleanIs extends Condition {
    private Expression<Boolean> boo, boov;

    @Override
    public boolean check(Event e) {
        Boolean chk;
        if (boov.getSingle(e).equals(true)) {
            chk = Boolean.TRUE.equals(boo.getSingle(e));
        } else {
            chk = Boolean.FALSE.equals(boo.getSingle(e));
        }
        return (isNegated() ? !chk : chk);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        boo = (Expression<Boolean>) e[0];
        boov = (Expression<Boolean>) e[1];
        setNegated(i == 1);
        return true;
    }

    @Override
    public String toString(Event e, boolean b) {
        return getClass().getName();
    }


}
