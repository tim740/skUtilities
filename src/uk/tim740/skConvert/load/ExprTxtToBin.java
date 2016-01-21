package uk.tim740.skConvert.load;

import org.bukkit.event.Event;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTxtToBin extends SimpleExpression<String>{
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
		return "convert (text|string) %string% to bin[ary]";
	}

	@Override
	@Nullable
	 protected String[] get(Event arg0) {
		  String s = this.string.getSingle(arg0);
		  byte[] by = s.getBytes();
		  StringBuilder bin = new StringBuilder();
		  for (byte b : by)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        bin.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		     bin.append(' ');
		}
		return new String[]{bin.toString()};
	}
}