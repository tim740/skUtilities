package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import java.util.Objects;

/**
 * Created by tim740.
 */
public class ExprFromString extends SimpleExpression<String>{
	private Expression<String> str;
    private int ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        String out = "";
        if (ty == 0) {
            for (String c : str.getSingle(arg0).split("")) {
                if (Objects.equals(out, "")) {
                    out = (Integer.toString(c.charAt(0)));
                } else {
                    out = (out + "," + Integer.toString(c.charAt(0)));
                }
            }
        }else{
            for (String c : str.getSingle(arg0).split("")) {
                if (c.charAt(0) < 0x10){
                    out = out + "\\u000" + Integer.toHexString(c.charAt(0));
                }else if (c.charAt(0) < 0x100){
                    out = out + "\\u00" + Integer.toHexString(c.charAt(0));
                }else if (c.charAt(0) < 0x1000){
                    out = out +"\\u0" + Integer.toHexString(c.charAt(0));
                }else{
                    out = out +"\\u" + Integer.toHexString(c.charAt(0));
                }
            }
        }
        return new String[]{out};
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
