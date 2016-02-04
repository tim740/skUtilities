package uk.tim740.skUtilities.conversion;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprUnixToDate extends SimpleExpression <String>{
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
		return this.getClass().getName();
	}

	@Override
	@Nullable
	protected String[] get(Event arg0) {
//	    return new Date[]{new Date(Long.valueOf(this.string.getSingle(arg0)).longValue() * 1000L)};
		String s = (this.string.getSingle(arg0)+"a").replace("000a", "").replace("a", "");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		return new String[]{sdf.format(new Date(Long.valueOf(s).longValue()*1000L))};
	}
}
