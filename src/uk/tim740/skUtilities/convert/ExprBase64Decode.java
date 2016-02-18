package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;
import javax.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprBase64Decode extends SimpleExpression<String> {
	private int bDeco;
	private Expression<String> string;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		byte[] by = DatatypeConverter.parseBase64Binary(this.string.getSingle(arg0));
		if (bDeco == 0){
			return new String[]{new String(by, StandardCharsets.UTF_8)};
		}else if (bDeco == 1){
			return new String[]{new String(by, StandardCharsets.US_ASCII)};
		}else{
			return new String[]{new String(by, StandardCharsets.ISO_8859_1)};
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
        bDeco = arg3.mark;
        this.string = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return this.getClass().getName();
    }
}