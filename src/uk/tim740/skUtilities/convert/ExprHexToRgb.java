package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740.
 */
public class ExprHexToRgb extends SimpleExpression<String> {
  private Expression<String> hex;

  @Override
  @Nullable
  protected String[] get(Event e) {
    String s = hex.getSingle(e).toUpperCase().replace("#", "");
    if (s.length() == 6) {
      int c = (int) Long.parseLong(s, 16);
      return new String[]{Integer.toString((c >> 16) & 0xFF) + ", " + Integer.toString((c >> 8) & 0xFF) + ", " + Integer.toString((c) & 0xFF)};
    } else {
      skUtilities.prSysE("Length must be 6. (FFFFFF)!", getClass().getSimpleName());
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    hex = (Expression<String>) e[0];
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
