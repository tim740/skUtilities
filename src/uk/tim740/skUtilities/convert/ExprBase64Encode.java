package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprBase64Encode extends SimpleExpression<String> {
	private int bEnc;
	private Expression<String> b64;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String s = b64.getSingle(arg0);
		byte[] auby;
		if (bEnc == 0){
			auby = s.getBytes(StandardCharsets.UTF_8);
		}else if (bEnc == 1){
			auby = s.getBytes(StandardCharsets.US_ASCII);
		}else{
			auby  = s.getBytes(StandardCharsets.ISO_8859_1);	
		}
		return new String[]{Base64.getEncoder().encodeToString(auby)};
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
        bEnc = arg3.mark;
        b64 = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}