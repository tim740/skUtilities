package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;
import javax.xml.bind.DatatypeConverter;
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
public class ExprBase64 extends SimpleExpression<String> {
	private Expression<String> b64;
    private int b64Ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        if (b64Ty == 0) {
            return new String[]{Base64.getEncoder().encodeToString(b64.getSingle(arg0).getBytes(StandardCharsets.UTF_8))};
        }else{
            return new String[]{new String(DatatypeConverter.parseBase64Binary(b64.getSingle(arg0)), StandardCharsets.UTF_8)};
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        b64Ty = arg3.mark;
        b64 = (Expression<String>) arg0[0];
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