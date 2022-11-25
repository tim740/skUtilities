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
public class ExprMirrorTxt extends SimpleExpression<String> {
  private Expression<String> str;

  @Override
  @Nullable
  protected String[] get(Event e) {
    int i, len = str.getSingle(e).length();
    StringBuilder mir = new StringBuilder(len);
    for (i = (len - 1); i >= 0; i--) {
      mir.append(str.getSingle(e).charAt(i));
    }
    return new String[]{mir.toString()};
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
