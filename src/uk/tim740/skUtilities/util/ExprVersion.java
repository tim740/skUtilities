package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by tim740 on 10/03/2016
 */
public class ExprVersion extends SimpleExpression<String> {
  private Expression<String> str;

  @Override
  protected String[] get(Event e) {
    String s = str.getSingle(e);
    if (s.equalsIgnoreCase("aliases")) {
      try {
        return new String[]{"v" + new BufferedReader(new FileReader("plugins" + File.separator + "Skript" + File.separator + "aliases-english.sk")).readLine().replaceAll("#! VERSION: ", "").replaceAll("!", "")};
      } catch (Exception x) {
        skUtilities.prSysE(x.getCause().getMessage(), getClass().getSimpleName(), x);
      }
    } else if (s.equalsIgnoreCase("server")) {
      return new String[]{Bukkit.getServer().getVersion()};
    } else if (s.equalsIgnoreCase("os") || s.equalsIgnoreCase("java")) {
      return new String[]{System.getProperty(s.toLowerCase() + ".version")};
    } else {
      try {
        return new String[]{Bukkit.getServer().getPluginManager().getPlugin(s).getDescription().getVersion()};
      } catch (Exception x) {
        skUtilities.prSysE("'" + s + "' isn't a real plugin!", getClass().getSimpleName(), x);
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
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
  public String toString(Event e, boolean b) {
    return getClass().getName();
  }
}
