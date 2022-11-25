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
public class ExprToBin extends SimpleExpression<String> {
  private Expression<String> str;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    if (ty == 0) {
      byte[] by = str.getSingle(e).getBytes();
      StringBuilder bin = new StringBuilder();
      for (byte b : by) {
        int val = b;
        for (int i = 0; i < 8; i++) {
          bin.append((val & 128) == 0 ? 0 : 1);
          val <<= 1;
        }
        bin.append(' ');
      }
      return new String[]{bin.toString()};
    } else if (ty == 1) {
      return new String[]{Integer.toBinaryString(Integer.parseInt(str.getSingle(e)))};
    } else if (ty == 2) {
      return new String[]{Integer.toBinaryString(Integer.parseInt(str.getSingle(e), 16))};
    } else {
      return new String[]{Integer.toBinaryString(Integer.parseInt(str.getSingle(e), 8))};
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    ty = p.mark;
    str = (Expression<String>) e[0];
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
