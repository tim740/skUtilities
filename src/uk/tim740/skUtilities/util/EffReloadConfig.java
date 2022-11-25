package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740 on 27/03/2016
 */
public class EffReloadConfig extends Effect {
  private Expression<String> str;

  @Override
  protected void execute(Event e) {
    try {
      Bukkit.getPluginManager().getPlugin(str.getSingle(e)).reloadConfig();
    } catch (Exception x) {
      skUtilities.prSysE("'" + str + "' isn't a real plugin!", getClass().getSimpleName(), x);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    str = (Expression<String>) e[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
