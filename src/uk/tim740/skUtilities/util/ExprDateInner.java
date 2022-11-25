package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

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
    Date d = new Date(id.getSingle(e).getTimestamp() * 1000L);
    try {
      LocalDateTime ldt = LocalDateTime.parse(d.toString(), DateTimeFormatter.ofPattern(new SimpleDateFormat().toPattern()));
      switch (ty) {
        case 0: {
          return new String[]{String.valueOf(ldt.getYear())};
        } case 1: {
          return new String[]{String.valueOf(ldt.getMonthValue())};
        } case 2: {
          return new String[]{ldt.getMonth().name()};
        } case 3: {
          return new String[]{String.valueOf(ldt.getDayOfYear())};
        } case 4: {
          return new String[]{String.valueOf(ldt.getDayOfMonth())};
        } case 5: {
          return new String[]{String.valueOf(ldt.getDayOfWeek().getValue())};
        } case 6: {
          return new String[]{ldt.getDayOfWeek().name()};
        } case 7: {
          return new String[]{String.valueOf(ldt.getHour())};
        } case 8: {
          return new String[]{String.valueOf(ldt.getMinute())};
        } case 9: {
          long ul = (id.getSingle(e).getTimestamp() / 1000L) - (ldt.toEpochSecond(ZoneOffset.ofTotalSeconds(ldt.getSecond())));
          while (ul > 59) {
            if (ul > 3600) {
              ul = (ul - 3600);
            } else {
              ul = (ul - 900);
            }
          }
          return new String[]{String.valueOf(ul)};
        }
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
