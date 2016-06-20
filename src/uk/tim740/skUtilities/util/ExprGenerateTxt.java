package uk.tim740.skUtilities.util;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprGenerateTxt extends SimpleExpression<String>{
	private Expression<Number> in;
    private static final String[] chl = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".split("");

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		Random ranGen = new Random();
		String out = "";
		for (int i=0; i<Integer.parseInt(in.getSingle(arg0).toString()); i++){
			if (Objects.equals(out, "")){
				out = (chl[ranGen.nextInt(chl.length)]);
			}else{
                out += chl[ranGen.nextInt(chl.length)];
			}
		}
		return new String[]{out};
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        in = (Expression<Number>) arg0[0];
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
