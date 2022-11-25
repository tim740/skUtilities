package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tim740 on 10/03/2016
 */
public class ExprRam extends SimpleExpression<Number> {
  private int ty;

  @Override
  @Nullable
  protected Number[] get(Event e) {
    if (ty == 0) {
      return new Number[]{Runtime.getRuntime().freeMemory() / 1000000L};
    } else if (ty == 1) {
      return new Number[]{Runtime.getRuntime().totalMemory() / 1000000L};
    } else {
      return new Number[]{Runtime.getRuntime().maxMemory() / 1000000L};
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    ty = p.mark;
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
