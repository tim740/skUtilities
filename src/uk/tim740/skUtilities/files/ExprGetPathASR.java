package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 28/06/2016
 */
public class ExprGetPathASR extends SimpleExpression<String> {
  private Expression<String> path;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    try {
      switch (ty) {
        case 0:
          return new String[]{pth.normalize().toAbsolutePath().toString()};
        case 1:
          String s = pth.normalize().toAbsolutePath().toString();
          String ps = s.substring(s.lastIndexOf(File.separator));
          s = s.replace(ps, "");
          return new String[]{(s.substring(s.lastIndexOf(File.separator)) + ps).replaceFirst(File.separator, "")};
        case 2:
          return new String[]{Paths.get(skUtilities.getDefaultPath("")).relativize(pth).toString()};
      }
    } catch (Exception x) {
      skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
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
