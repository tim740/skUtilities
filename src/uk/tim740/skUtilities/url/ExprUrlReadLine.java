package uk.tim740.skUtilities.url;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by tim740 on 13/09/2016
 */
public class ExprUrlReadLine extends SimpleExpression<String> {
  private Expression<Number> line;
  private Expression<String> url;

  @Override
  @Nullable
  protected String[] get(Event e) {
    try {
      Integer n = (line.getSingle(e).intValue() - 1);
      BufferedReader ur = new BufferedReader(new InputStreamReader(new URL(url.getSingle(e)).openStream()));
      String[] s = ur.lines().toArray(String[]::new);
      ur.close();
      return new String[]{s[n]};
    } catch (IOException x) {
      skUtilities.prSysE("Error Reading from: '" + url.getSingle(e) + "' Is the site down?", getClass().getSimpleName(), x);
    } catch (Exception x) {
      skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    line = (Expression<Number>) e[i];
    url = (Expression<String>) e[1 - i];
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
