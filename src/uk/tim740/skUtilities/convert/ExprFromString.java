package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Created by tim740.
 */
public class ExprFromString extends SimpleExpression<String> {
  private Expression<String> str;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    String out = "";
    if (ty == 0) {
      for (String c : str.getSingle(e).split("")) {
        if (Objects.equals(out, "")) {
          out = (Integer.toString(c.charAt(0)));
        } else {
          out += "," + Integer.toString(c.charAt(0));
        }
      }
    } else {
      for (String c : str.getSingle(e).split("")) {
        if (c.charAt(0) < 0x10) {
          out += "\\u000" + Integer.toHexString(c.charAt(0));
        } else if (c.charAt(0) < 0x100) {
          out += "\\u00" + Integer.toHexString(c.charAt(0));
        } else if (c.charAt(0) < 0x1000) {
          out += "\\u0" + Integer.toHexString(c.charAt(0));
        } else {
          out += "\\u" + Integer.toHexString(c.charAt(0));
        }
      }
    }
    return new String[]{out};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    str = (Expression<String>) e[0];
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
