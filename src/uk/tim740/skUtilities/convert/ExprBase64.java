package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by tim740 on 07/03/16
 */
public class ExprBase64 extends SimpleExpression<String> {
	private Expression<String> b64;
    private int ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        if (ty == 0) {
            return new String[]{Base64.getEncoder().encodeToString(b64.getSingle(arg0).getBytes(StandardCharsets.UTF_8))};
        }else{
            return new String[]{new String(DatatypeConverter.parseBase64Binary(b64.getSingle(arg0)), StandardCharsets.UTF_8)};
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        ty = arg3.mark;
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