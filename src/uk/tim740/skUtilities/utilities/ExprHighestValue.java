package uk.tim740.skUtilities.utilities;

import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprHighestValue extends SimpleExpression<String>{
	private Expression<String> string;
	private int num;

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
		num = arg3.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return this.getClass().getName();
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		return null;
	}
}
