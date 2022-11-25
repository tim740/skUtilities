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
public class ExprHexaToNum extends SimpleExpression<Number> {
  private Expression<String> str;

  @Override
  @Nullable
  protected Number[] get(Event e) {
    return new Number[]{Integer.parseInt(str.getSingle(e), 16)};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    str = (Expression<String>) e[0];
    return true;
  }

  @Override
  public Class<? extends Number> getReturnType() {
    return Number.class;
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
