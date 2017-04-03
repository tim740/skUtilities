package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 02/04/2016
 */
public class CondStartsEndsWith extends Condition {
  private Expression<String> str, txt;
  private int ty;

  @Override
  public boolean check(Event e) {
    if (ty == 0) {
      Boolean chk = str.getSingle(e).startsWith(txt.getSingle(e));
      return (isNegated() ? !chk : chk);
    } else {
      Boolean chk = str.getSingle(e).endsWith(txt.getSingle(e));
      return (isNegated() ? !chk : chk);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    str = (Expression<String>) e[0];
    txt = (Expression<String>) e[1];
    ty = p.mark;
    setNegated(i == 1);
    return true;
  }

  @Override
  public String toString(Event e, boolean b) {
    return getClass().getName();
  }
}
