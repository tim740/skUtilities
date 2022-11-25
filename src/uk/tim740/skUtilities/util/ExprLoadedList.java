package uk.tim740.skUtilities.util;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by tim740 on 09/09/2016
 */
public class ExprLoadedList extends SimpleExpression<String> {
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    ArrayList<String> cl = new ArrayList<>();
    if (ty == 0) {
      for (Plugin s : Bukkit.getServer().getPluginManager().getPlugins()) {
        cl.add(s.getName());
      }
    } else {
      cl.addAll(Skript.getAddons().stream().map(SkriptAddon::getName).collect(Collectors.toList()));
    }
    return cl.toArray(new String[cl.size()]);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    ty = p.mark;
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
