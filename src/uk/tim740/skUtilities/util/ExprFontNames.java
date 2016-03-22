package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by tim740 on 21/03/2016
 */
public class ExprFontNames extends SimpleExpression<String> {

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        ArrayList<String> cl = new ArrayList<>();
        for (Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
            cl.add(f.getFontName());
        }
        String[] out = new String[cl.size()];
        return cl.toArray(out);
    }

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
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
