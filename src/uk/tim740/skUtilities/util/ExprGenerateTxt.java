package uk.tim740.skUtilities.util;

import javax.annotation.Nullable;

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
	private Expression<Integer> integer;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String[] chl = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".split("");
		Random ranGen = new Random();
		String out = "";
		for (int i=0; i<this.integer.getSingle(arg0); i++){
			if (out == ""){
				out = (chl[ranGen.nextInt(chl.length)]);
			}else{
				out = (out + chl[ranGen.nextInt(chl.length)]);
			}
		}
		return new String[]{out};
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
        this.integer = (Expression<Integer>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return this.getClass().getName();
    }
}
