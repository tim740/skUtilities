package uk.tim740.skUtilities.util;

import ch.njol.skript.aliases.Aliases;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;


/**
 * Created by tim740 on 27/03/16
 */
public class EffSkReloadAliases extends Effect {

  @Override
  protected void execute(Event e) {
    Aliases.clear();
    Aliases.load();
  }

  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}