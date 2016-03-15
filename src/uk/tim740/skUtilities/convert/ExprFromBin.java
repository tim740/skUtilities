package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import uk.tim740.skUtilities.skUtilities;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprFromBin extends SimpleExpression<String> {
    private Expression<String> str;
    private int toBin;

	@Override
	@Nullable
	protected String[] get(Event arg0){
        String binV = str.getSingle(arg0).trim();
        for (char character : binV.toCharArray()){
            if (character !='0' && character !='1' && character !=' '){
                skUtilities.prErr("Binary Strings can only contain 1's, 0's or spaces!", getClass().getSimpleName(), 1);
                return null;
            }
        }
        String bin = str.getSingle(arg0);
		if (toBin == 0){
			StringBuilder sb = new StringBuilder();
            for (String s : bin.split(" ")) {
                sb.append((char) Integer.parseInt(s, 2));
            }
			return new String[]{sb.toString()};
		}else if (toBin == 1){
			return new String[]{Integer.toString(Integer.parseInt(bin, 2))};
		}else if (toBin == 2){
			return new String[]{Integer.toHexString(Integer.parseInt(bin, 2))};
		}else{
			return new String[]{Integer.toOctalString(Integer.parseInt(bin, 2))};
		}
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        toBin = arg3.mark;
        str = (Expression<String>) arg0[0];
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
