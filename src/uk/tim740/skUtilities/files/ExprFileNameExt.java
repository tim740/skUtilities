package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.google.common.io.Files;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740 on 14/08/2016
 */
public class ExprFileNameExt extends SimpleExpression<String> {
  private Expression<String> path;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    String pth = skUtilities.getDefaultPath(path.getSingle(e));
    try {
      if (ty == 0) {
        return new String[]{Files.getNameWithoutExtension(pth)};
      } else {
        return new String[]{FilenameUtils.getExtension(pth)};
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
