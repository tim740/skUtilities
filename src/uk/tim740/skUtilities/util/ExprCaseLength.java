package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 15/08/16
 */
public class ExprCaseLength extends SimpleExpression<Number>{
    private Expression<String> str;
	private int ty;

	@Override
	@Nullable
	protected Number[] get(Event arg0) {
	    String s = str.getSingle(arg0);
        Integer n = 0;
		if (ty == 0){
            for (int k = 0; k < s.length(); k++) {
                if (Character.isUpperCase(s.charAt(k))) n++;
            }
		}else{
            for (int k = 0; k < s.length(); k++) {
                if (Character.isLowerCase(s.charAt(k))) n++;
            }
        }
        return new Number[]{n};
	}

	@SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        ty = arg3.mark;
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