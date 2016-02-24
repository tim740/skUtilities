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
public class ExprTxtToAscii  extends SimpleExpression<String>{
	private Expression<String> string;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String out = "";
		for(String c : this.string.getSingle(arg0).split("")) {
			if (Objects.equals(out, "")){
				out = (Integer.toString(c.charAt(0)));
			}else{
				out = (out + "," + Integer.toString(c.charAt(0)));
			}
		}
		return new String[]{out};
	}

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        this.string = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
