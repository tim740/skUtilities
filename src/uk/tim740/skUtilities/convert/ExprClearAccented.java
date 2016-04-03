package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by tim740 on 03/04/2016
 */
public class ExprClearAccented extends SimpleExpression<String> {
    private Expression<String> str;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return new String[]{pattern.matcher(Normalizer.normalize(str.getSingle(arg0), Normalizer.Form.NFD)).replaceAll("")};
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
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
