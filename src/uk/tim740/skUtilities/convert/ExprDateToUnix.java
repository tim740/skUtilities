package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 30/03/2016
 */
public class ExprDateToUnix extends SimpleExpression <Number>{
    private Expression<Date> idate;

    @Override
    @Nullable
    protected Number[] get(Event arg0) {
        return new Number[]{idate.getSingle(arg0).getTimestamp() /1000L};
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        idate = (Expression<Date>) arg0[0];
        return true;
    }
    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
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
