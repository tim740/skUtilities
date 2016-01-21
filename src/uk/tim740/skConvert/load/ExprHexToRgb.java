package uk.tim740.skConvert.load;

import java.awt.Color;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprHexToRgb extends SimpleExpression<String>{
	private Expression<String> string;

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
		return "convert hex %string% to rgb";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		if (this.string.getSingle(arg0).contains("#")){
			this.string.getSingle(arg0).replace("#", "");
		}
		if (this.string.getSingle(arg0).length() == 6){
			Color ohex = Color.decode("#" + this.string.getSingle(arg0));
			return new String[]{ohex.toString().replace("java.awt.Color[", "").replace("r=", "").replace("g=", " ").replace("b=", " ").replace("]", "")};
		}else{
			Skript.warning("[skConvert] Error: (HexToRgb) Length must be 6 (FFFFFF)!");
			return null;
		}
	}
}
