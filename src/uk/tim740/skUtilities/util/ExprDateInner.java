package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by tim740 on 31/01/2017
 */
public class ExprDateInner extends SimpleExpression<String> {
    private Expression<Date> id;
    private int ty;

    @Override
    @Nullable
    protected String[] get(Event e) {
        Date s = id.getSingle(e);
        try {
            LocalDateTime ldt = LocalDateTime.parse(s.toString(), DateTimeFormatter.ofPattern(new SimpleDateFormat().toPattern()));
            if (ty == 0) {
                return new String[]{String.valueOf(ldt.getYear())};
            } else if (ty == 1) {
                return new String[]{String.valueOf(ldt.getMonthValue())};
            } else if (ty == 2) {
                return new String[]{ldt.getMonth().name()};
            } else if (ty == 3) {
                return new String[]{String.valueOf(ldt.getDayOfYear())};
            } else if (ty == 4) {
                return new String[]{String.valueOf(ldt.getDayOfMonth())};
            } else if (ty == 5) {
                return new String[]{String.valueOf(ldt.getDayOfWeek().getValue())};
            } else if (ty == 6) {
                return new String[]{ldt.getDayOfWeek().name()};
            } else if (ty == 7) {
                return new String[]{String.valueOf(ldt.getHour())};
            } else if (ty == 8) {
                return new String[]{String.valueOf(ldt.getMinute())};
            } else if (ty == 9) {
                return new String[]{String.valueOf((id.getSingle(e).getTimestamp() / 1000L) - (ldt.toEpochSecond(ZoneOffset.ofTotalSeconds(ldt.getSecond()))))};
            }
        } catch (Exception x) {
            skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        id = (Expression<Date>) e[0];
        ty = p.mark;
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
