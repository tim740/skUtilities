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
public class ExprNumToHexa extends SimpleExpression<String> {
  private Expression<Number> n;

  @Override
  @Nullable
  protected String[] get(Event e) {
    return new String[]{Integer.toHexString(n.getSingle(e).intValue())};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    n = (Expression<Number>) e[0];
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
