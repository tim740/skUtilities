package uk.tim740.skUtilities.convert;

import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprToAscii extends SimpleExpression<String>{
	private Expression<Number> num;
    private int ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        if (ty == 0){
            return new String[]{Character.toString((char) Integer.parseInt(num.getSingle(arg0).toString()))};
        }else{
            char ch = (char) Integer.parseInt(num.getSingle(arg0).toString());
            if (ch < 0x10){
                return new String[]{"\\u000" + Integer.toHexString(ch)};
            }else if (ch < 0x100){
                return new String[]{"\\u00" + Integer.toHexString(ch)};
            }else if (ch < 0x1000){
                return new String[]{"\\u0" + Integer.toHexString(ch)};
            }else{
                return new String[]{"\\u" + Integer.toHexString(ch)};
            }
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        num = (Expression<Number>) arg0[0];
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
