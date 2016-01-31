package uk.tim740.skUtilities.load;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprHexaToNum extends SimpleExpression<String>{
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
		return "convert hexa[decimal] %string% to num[ber]";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		return new String[]{Integer.toString(Integer.parseInt(this.string.getSingle(arg0), 16))};
	}
}
