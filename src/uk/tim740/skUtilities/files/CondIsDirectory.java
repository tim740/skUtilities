package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 12/02/2017
 */
public class CondIsDirectory extends Condition {
  private Expression<String> path;

  @Override
  public boolean check(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    if (!Files.exists(pth)) return false;
    Boolean b = Files.isDirectory(pth);
    return (isNegated() ? !b : b);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    path = (Expression<String>) e[0];
    setNegated(i == 1);
    return true;
  }

  @Override
  public String toString(Event e, boolean b) {
    return getClass().getName();
  }
}
