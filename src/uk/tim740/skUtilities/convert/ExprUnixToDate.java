package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740.
 */
public class ExprUnixToDate extends SimpleExpression <Date>{
    private Expression<Integer> str;

    @Override
    @Nullable
    protected Date[] get(Event arg0) {
        String si = str.getSingle(arg0).toString();
        if (!(si.length() == 10)){
            si = si.substring(0, 10);
        }
        return new Date[]{new Date(Integer.parseInt(si) *1000L)};
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        str = (Expression<Integer>) arg0[0];
        return true;
    }
    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
