package uk.tim740.skUtilities.convert;

import java.awt.Color;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprHexToRgb extends SimpleExpression<String>{
	private Expression<String> string;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		this.string.getSingle(arg0).replace("#", "");
		if (this.string.getSingle(arg0).length() == 6){
			Color ohex = Color.decode("#" + this.string.getSingle(arg0));
			return new String[]{ohex.toString().replace("java.awt.Color[", "").replace("r=", "").replace("g=", " ").replace("b=", " ").replace("]", "")};
		}else{
			Skript.warning("[skUtilities] Error: (HexToRgb) Length must be 6. (FFFFFF)");
			return null;
		}
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
        return this.getClass().getName();
    }
}
