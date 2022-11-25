package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tim740 on 24/09/2017
 */
public class ExprCpuCores extends SimpleExpression<Number> {

  @Override
  @Nullable
  protected Number[] get(Event e) {
    return new Number[]{Runtime.getRuntime().availableProcessors()};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
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
