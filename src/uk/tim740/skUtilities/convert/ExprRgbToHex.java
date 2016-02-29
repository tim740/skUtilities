package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.awt.Color;

/**
 * Created by tim740 on 19/02/2016
 */
public class ExprRgbToHex extends SimpleExpression<String> {
    private Expression<Number> r, g, b;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        String rgb = Integer.toHexString(new Color(Integer.parseInt(r.getSingle(arg0).toString()),Integer.parseInt(g.getSingle(arg0).toString()),Integer.parseInt(b.getSingle(arg0).toString())).getRGB());
        return new String[]{rgb.substring(2, rgb.length())};
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
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        r = (Expression<Number>) arg0[0];
        g = (Expression<Number>) arg0[1];
        b = (Expression<Number>) arg0[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
