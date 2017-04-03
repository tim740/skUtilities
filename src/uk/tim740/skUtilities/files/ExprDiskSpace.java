package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 03/04/2016
 */
public class ExprDiskSpace extends SimpleExpression<String> {
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    if (ty == 0) {
      return new String[]{skUtilities.getFileSize(new File(File.separator).getTotalSpace())};
    } else if (ty == 1) {
      return new String[]{skUtilities.getFileSize(new File(File.separator).getFreeSpace())};
    } else {
      return new String[]{skUtilities.getFileSize(new File(File.separator).getUsableSpace())};
    }
  }

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
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
