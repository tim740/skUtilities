package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 10/03/2016
 */
public class ExprSysTime extends SimpleExpression<Integer> {
    private int ty;

    @Override
    @Nullable
    protected Integer[] get(Event arg0) {
        if (ty == 0) {
            return new Integer[]{Math.toIntExact(System.nanoTime())};
        }else if (ty == 1){
            return new Integer[]{Math.toIntExact(System.currentTimeMillis())};
        }else{
            return new Integer[]{Math.toIntExact(System.currentTimeMillis() /1000)};
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        ty = arg3.mark;
        return true;
    }
    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
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
