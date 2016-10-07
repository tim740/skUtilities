package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 05/10/16
 */
public class ExprGetJsonID extends SimpleExpression<String> {
    private Expression<String> id, t;

    @Override
    @Nullable
    protected String[] get(Event e) {
        try {
            JSONObject j = (JSONObject) new JSONParser().parse(t.getSingle(e));
            return new String[]{j.get(id.getSingle(e)).toString()};
        } catch (ParseException x) {
            skUtilities.prSysE("Error while parsing json!", getClass().getSimpleName(), x);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        id = (Expression<String>) e[0];
        t = (Expression<String>) e[1];
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
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}