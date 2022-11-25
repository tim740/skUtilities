package uk.tim740.skUtilities.url;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by tim740 on 13/09/2016
 */
public class ExprUrlContents extends SimpleExpression<String> {
  private Expression<String> url;

  @Override
  @Nullable
  protected String[] get(Event e) {
    try {
      BufferedReader ur = new BufferedReader(new InputStreamReader(new URL(url.getSingle(e)).openStream()));
      String[] s = ur.lines().toArray(String[]::new);
      ur.close();
      return s;
    } catch (Exception x) {
      skUtilities.prSysE("Error Reading from: '" + url.getSingle(e) + "' Is the site down?", getClass().getSimpleName(), x);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    url = (Expression<String>) e[0];
    return true;
  }

  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public boolean isSingle() {
    return false;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
