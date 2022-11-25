package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.apache.commons.io.IOUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.io.IOException;

/**
 * Created by tim740 on 10/04/17
 */
public class EffRunCmd extends Effect {
  static String o = "";
  private Expression<String> cmdr;

  @Override
  protected void execute(Event e) {
    String cmd = cmdr.getSingle(e);
    try {
      Process rp = Runtime.getRuntime().exec(cmd);
      rp.waitFor();
      o = IOUtils.toString(rp.getInputStream());
    } catch (IOException | InterruptedException x) {
      skUtilities.prSysE("Can't run Command: '" + cmd + "' - '" + x.getMessage(), getClass().getSimpleName() + "'", x);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    cmdr = (Expression<String>) e[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}