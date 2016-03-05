package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprMirrorTxt extends SimpleExpression<String>{
    private Expression<String> str;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        int i, len = str.getSingle(arg0).length();
        StringBuilder mir = new StringBuilder(len);
        for (i = (len - 1); i >= 0; i--){
            mir.append(str.getSingle(arg0).charAt(i));
        }
        return new String[]{mir.toString()};
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
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
