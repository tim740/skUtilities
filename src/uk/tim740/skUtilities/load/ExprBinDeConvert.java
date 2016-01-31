package uk.tim740.skUtilities.load;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import uk.tim740.skUtilities.load.Binary.BinInvalid;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprBinDeConvert extends SimpleExpression<String> {
	private int toBin;
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
		toBin = arg3.mark;
		this.string = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "convert bin[ary] %string% to (0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal)";
	}

	@Override
	@Nullable
	protected String[] get(Event arg0){
		Binary bin = null;
		try{
			bin = new Binary(this.string.getSingle(arg0));
		}catch (BinInvalid e){
			e.printStackTrace();
			return null;
		}
		if (toBin == 0){
			String[] ss = bin.toString().split(" ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ss.length; i++) { 
				sb.append((char)Integer.parseInt(ss[i], 2));                                                                                                                                                        
			}   
			return new String[]{sb.toString()};
		}else if (toBin == 1){
			return new String[]{Integer.toString(Integer.parseInt(bin.toString(), 2))};
		}else if (toBin == 2){
			return new String[]{Integer.toHexString(Integer.parseInt(bin.toString(), 2))};
		}else if (toBin == 3){
			return new String[]{Integer.toOctalString(Integer.parseInt(bin.toString(), 2))};
		}else{
			return null;
		}
	}
}
