package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;

import ch.njol.skript.Skript;
import org.bukkit.event.Event;

import uk.tim740.skUtilities.convert.Binary.BinInvalid;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprBinDeConvert extends SimpleExpression<String> {
	private int toBin;
	private Expression<String> string;

	@Override
	@Nullable
	protected String[] get(Event arg0){
		Binary bin;
		try{
			bin = new Binary(this.string.getSingle(arg0));
		}catch (BinInvalid e){
            Skript.error("[skUtilities] Binary Strings can only contain 1's, 0's or spaces!");
            return null;
		}
		if (toBin == 0){
			StringBuilder sb = new StringBuilder();
            for (String s : bin.toString().split(" ")) {
                sb.append(Integer.parseInt(s, 2));
            }
			return new String[]{sb.toString()};
		}else if (toBin == 1){
			return new String[]{Integer.toString(Integer.parseInt(bin.toString(), 2))};
		}else if (toBin == 2){
			return new String[]{Integer.toHexString(Integer.parseInt(bin.toString(), 2))};
		}else{
			return new String[]{Integer.toOctalString(Integer.parseInt(bin.toString(), 2))};
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
        toBin = arg3.mark;
        this.string = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return this.getClass().getName();
    }
}
