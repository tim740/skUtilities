package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by tim740 on 11/09/2016
 */
public class ExprTimeInTimeZone extends SimpleExpression<Date> {
    private Expression<String> str;

    @Override
    @Nullable
    protected Date[] get(Event e) {
        String s = str.getSingle(e);
        if (s.contains("-")) {
            s = s.replace('-', '+');
        }else if (s.contains("+")) {
            s = s.replace('+', '-');
        }
        try {
            return new Date[]{new Date(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(s)).toEpochSecond() *1000L)};
        }catch (Exception x){
            skUtilities.prSysE("'" + s + "' is not a valid TimeZone!", getClass().getSimpleName(), x);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        str = (Expression<String>) e[0];
        return true;
    }
    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
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
