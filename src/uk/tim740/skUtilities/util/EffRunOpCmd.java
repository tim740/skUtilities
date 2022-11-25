package uk.tim740.skUtilities.util;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;


/**
 * Created by tim740 on 16/06/16
 * Code by @dzikoysk, optimized by @tim740
 */
public class EffRunOpCmd extends Effect {
  private Expression<String> str;
  private Expression<Player> usr;


  @Override
  protected void execute(Event e) {
    for (String cmd : str.getArray(e)) {
      if (cmd.startsWith("/")) cmd = cmd.substring(1);
      if (usr == null) {
        Skript.dispatchCommand(Bukkit.getConsoleSender(), cmd);
      } else {
        for (CommandSender u : usr.getArray(e)) {
          if (!u.isOp()) {
            u.setOp(true);
            Skript.dispatchCommand(u, cmd);
            u.setOp(false);
          } else {
            Skript.dispatchCommand(u, cmd);
          }
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    usr = (Expression<Player>) e[0];
    str = (Expression<String>) e[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}