package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Created by tim740 on 02/04/2016
 */
public class CondisTimeZone extends Condition {
  private Expression<String> str;

  @Override
  public boolean check(Event e) {
    String s = str.getSingle(e).toUpperCase();
    if (!ZoneId.getAvailableZoneIds().contains(s)) {
      skUtilities.prSysE("'" + s + "' is not a valid TimeZone!", getClass().getSimpleName());
    }
    Boolean chk = s.equals(TimeZone.getTimeZone(TimeZone.getDefault().getID()).getDisplayName(false, TimeZone.SHORT));
    return (isNegated() ? !chk : chk);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    str = (Expression<String>) e[0];
    setNegated(i == 1);
    return true;
  }

  @Override
  public String toString(Event e, boolean b) {
    return getClass().getName();
  }
}
