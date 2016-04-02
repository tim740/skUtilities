package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 02/04/2016
 */
public class ExprToUpperLower extends SimpleExpression<String>{
	private Expression<String> str;
    private int ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        if (ty == 0) {
            return new String[]{str.getSingle(arg0).toUpperCase()};
        }else{
            return new String[]{str.getSingle(arg0).toLowerCase()};
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        ty = arg3.mark;
        return true;
    }
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
