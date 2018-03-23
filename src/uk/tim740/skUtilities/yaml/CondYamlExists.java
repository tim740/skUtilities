package uk.tim740.skUtilities.yaml;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import java.io.File;

/**
 * Created by tim740 on 17/12/2016
 */
public class CondYamlExists extends Condition {
  private Expression<String> path, yaml;

  @Override
  public boolean check(Event e) {
    File pth = new File(skUtilities.getDefaultPath(path.getSingle(e)));
    FileConfiguration con = YamlConfiguration.loadConfiguration(pth);
    Boolean v = con.contains(yaml.getSingle(e));
    return (isNegated() ? !v : v);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    yaml = (Expression<String>) e[0];
    path = (Expression<String>) e[1];
    setNegated(i == 1);
    return true;
  }

  @Override
  public String toString(Event e, boolean b) {
    return getClass().getName();
  }
}
