package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * Created by tim740 on 16/08/2016
 */
public class ExprRandomizeString extends SimpleExpression<String> {
  private Expression<String> str;

  @Override
  @Nullable
  protected String[] get(Event e) {
    char a[] = str.getSingle(e).toCharArray();
    Random r = new Random();
    for (int i = 0; i < a.length - 1; i++) {
      int j = r.nextInt(a.length - 1);
      char t = a[i];
      a[i] = a[j];
      a[j] = t;
    }
    return new String[]{String.valueOf(a)};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
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
