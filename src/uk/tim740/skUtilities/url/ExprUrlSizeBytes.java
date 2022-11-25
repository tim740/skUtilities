package uk.tim740.skUtilities.url;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tim740 on 14/09/2016
 */
public class ExprUrlSizeBytes extends SimpleExpression<Number> {
  private Expression<String> url;

  @Override
  @Nullable
  protected Number[] get(Event e) {
    try {
      HttpURLConnection c = (HttpURLConnection) new URL(url.getSingle(e)).openConnection();
      c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      Number n = c.getContentLength();
      c.disconnect();
      if (n.intValue() == -1) {
        skUtilities.prSysE("Url: '" + url.getSingle(e) + "' returned no information about the url's size!", getClass().getSimpleName());
      } else {
        return new Number[]{n};
      }
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
