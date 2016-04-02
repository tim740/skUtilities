package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 02/04/2016
 */
public class CondStartsEndsWith extends Condition {
    private Expression<String> str, txt;
    private int ty;

    @Override
    public boolean check(Event arg0) {
        if (ty == 0) {
            return str.getSingle(arg0).startsWith(txt.getSingle(arg0));
        }else{
            return str.getSingle(arg0).endsWith(txt.getSingle(arg0));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        txt = (Expression<String>) arg0[1];
        ty = arg3.mark;
        return true;
    }
    @Override
    public String toString(Event arg0, boolean arg1) {
        return getClass().getName();
    }


}
