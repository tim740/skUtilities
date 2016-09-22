package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tim740 on 14/09/2016
 */
public class ExprUrlSizeBytes extends SimpleExpression<Number> {
    private Expression<String> url;

    @Override
    @Nullable
    protected Number[] get(Event e) {
        try {
            HttpURLConnection s = (HttpURLConnection) new URL(url.getSingle(e)).openConnection();
            Number n = s.getContentLength();
            s.disconnect();
            if (n.intValue() == -1) {
                skUtilities.prSysE("Url: '" + url.getSingle(e) + "' returned no information about the url's size!", getClass().getSimpleName());
            } else {
                return new Number[]{n};
            }
        } catch (Exception x) {
            skUtilities.prSysE("Error Reading from: '" + url.getSingle(e) + "' Is the site down?", getClass().getSimpleName(), x);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        url = (Expression<String>) e[0];
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
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
