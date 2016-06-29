package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740.
 */
public class ExprHexToRgb extends SimpleExpression<String>{
	private Expression<String> hex;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String s = hex.getSingle(arg0).toUpperCase().replace("#", "");
		if (s.length() == 6){
            int color = (int)Long.parseLong(s, 16);
            return new String[]{Integer.toString((color >> 16) & 0xFF) + ", " + Integer.toString((color >> 8) & 0xFF) + ", " + Integer.toString((color) & 0xFF)};
        }else{
            skUtilities.prSys("Length must be 6. (FFFFFF)!", getClass().getSimpleName(), 0);
			return null;
		}
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        hex = (Expression<String>) arg0[0];
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
