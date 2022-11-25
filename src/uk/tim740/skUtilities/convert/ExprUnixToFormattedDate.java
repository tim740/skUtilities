package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tim740 on 11/09/16
 */
public class ExprUnixToFormattedDate extends SimpleExpression<String> {
  private Expression<Number> n;
  private Expression<String> f;

  @Override
  @Nullable
  protected String[] get(Event e) {
    String un = n.getSingle(e).toString();
    if (!(un.length() == 10)) {
      un = un.substring(0, 10);
    }
    return new String[]{new SimpleDateFormat(f.getSingle(e)).format(new Date(Long.valueOf(un) * 1000L))};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    n = (Expression<Number>) e[0];
    f = (Expression<String>) e[1];
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
