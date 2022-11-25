package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.apache.commons.io.FileUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 17/03/2016
 */
public class ExprFileDirSize extends SimpleExpression<String> {
  private Expression<String> path;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    try {
      if (ty == 0) {
        return new String[]{skUtilities.getFileSize(Files.size(pth))};
      } else {
        return new String[]{skUtilities.getFileSize(FileUtils.sizeOfDirectory(pth.toFile()))};
      }
    } catch (Exception x) {
      skUtilities.prSysE("'" + pth + "' doesn't exist!", getClass().getSimpleName());
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    path = (Expression<String>) e[0];
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
