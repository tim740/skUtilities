package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tim740 on 15/08/16
 */
public class ExprCaseLength extends SimpleExpression<Number> {
  private Expression<String> str;
  private int ty;

  @Override
  @Nullable
  protected Number[] get(Event e) {
    String s = str.getSingle(e);
    Integer n = 0;
    if (ty == 0) {
      for (int i = 0; i < s.length(); i++) {
        if (Character.isUpperCase(s.charAt(i))) n++;
      }
    } else {
      for (int i = 0; i < s.length(); i++) {
        if (Character.isLowerCase(s.charAt(i))) n++;
      }
    }
    return new Number[]{n};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    str = (Expression<String>) e[0];
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