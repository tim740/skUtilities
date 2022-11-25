package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

/**
 * Created by tim740 on 19/02/2016
 */
public class ExprRgbToHex extends SimpleExpression<String> {
  private Expression<Number> r, g, b;

  @Override
  @Nullable
  protected String[] get(Event e) {
    String rgb = Integer.toHexString(new Color(Integer.parseInt(r.getSingle(e).toString()), Integer.parseInt(g.getSingle(e).toString()), Integer.parseInt(b.getSingle(e).toString())).getRGB());
    return new String[]{rgb.substring(2, rgb.length())};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    r = (Expression<Number>) e[0];
    g = (Expression<Number>) e[1];
    b = (Expression<Number>) e[2];
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
