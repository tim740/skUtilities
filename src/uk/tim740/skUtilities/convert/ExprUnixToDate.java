package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;

/**
 * Created by tim740.
 */
public class ExprUnixToDate extends SimpleExpression <Date>{
    private Expression<Number> n;

    @Override
    @Nullable
    protected Date[] get(Event arg0) {
        String si = n.getSingle(arg0).toString();
        if (!(si.length() == 10)){
            si = si.substring(0, 10);
        }
        try {
            return new Date[]{new Date(Integer.parseInt(si) *1000L)};
        } catch (Exception e) {
            skUtilities.prSysE("'" + si + "' You must use Integers 10 Chars long!", getClass().getSimpleName(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        n = (Expression<Number>) arg0[0];
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
