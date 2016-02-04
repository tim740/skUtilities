package uk.tim740.skUtilities.conversion;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprBinConvert extends SimpleExpression<String> {
	private Expression<String> string;
	private int fromBin;

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
		fromBin = arg3.mark;
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
		if (fromBin == 0){
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
		}else if (fromBin == 1){
			return new String[]{Integer.toBinaryString(Integer.parseInt(this.string.getSingle(arg0)))};
		}else if (fromBin == 2){
			return new String[]{Integer.toBinaryString(Integer.parseInt(this.string.getSingle(arg0), 16))};
		}else if (fromBin == 3){
			return new String[]{Integer.toBinaryString(Integer.parseInt(this.string.getSingle(arg0), 8))};
		}else{
			return null;
		}
	}
}
