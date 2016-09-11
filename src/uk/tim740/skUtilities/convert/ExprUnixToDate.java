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
    protected Date[] get(Event e) {
        String si = n.getSingle(e).toString();
        if (!(si.length() == 10)){
            si = si.substring(0, 10);
        }
        try {
            return new Date[]{new Date(Integer.parseInt(si) *1000L)};
        } catch (Exception x) {
            skUtilities.prSysE("'" + si + "' You must use Integers 10 Chars long!", getClass().getSimpleName(), x);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        n = (Expression<Number>) e[0];
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
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
