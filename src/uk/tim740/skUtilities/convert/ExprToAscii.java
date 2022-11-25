package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tim740.
 */
public class ExprToAscii extends SimpleExpression<String> {
  private Expression<Number> n;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    if (ty == 0) {
      return new String[]{Character.toString((char) Integer.parseInt(n.getSingle(e).toString()))};
    } else {
      char ch = (char) Integer.parseInt(n.getSingle(e).toString());
      if (ch < 0x10) {
        return new String[]{"\\u000" + Integer.toHexString(ch)};
      } else if (ch < 0x100) {
        return new String[]{"\\u00" + Integer.toHexString(ch)};
      } else if (ch < 0x1000) {
        return new String[]{"\\u0" + Integer.toHexString(ch)};
      } else {
        return new String[]{"\\u" + Integer.toHexString(ch)};
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    n = (Expression<Number>) e[0];
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
