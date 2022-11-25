package uk.tim740.skUtilities.files;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 20/03/2016
 */
public class SExprFileAttribute extends SimpleExpression<Boolean> {
  private Expression<String> path;
  private int ty;

  @Override
  @Nullable
  protected Boolean[] get(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    try {
      if (ty == 0) {
        return new Boolean[]{Files.isReadable(pth)};
      } else if (ty == 1) {
        return new Boolean[]{Files.isWritable(pth)};
      } else {
        return new Boolean[]{Files.isHidden(pth)};
      }
    } catch (Exception x) {
      skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName(), x);
    }
    return null;
  }

  public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
    if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
      Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
      try {
        Boolean boo = (boolean) delta[0];
        if (ty == 0) {
          if (mode == Changer.ChangeMode.SET) {
            pth.toFile().setReadable(boo);
          } else {
            pth.toFile().setReadable(true);
          }
        } else if (ty == 1) {
          if (mode == Changer.ChangeMode.SET) {
            pth.toFile().setWritable(boo);
          } else {
            pth.toFile().setWritable(true);
          }
        } else {
          try {
            if (mode == Changer.ChangeMode.SET) {
              Files.setAttribute(pth, "dos:hidden", boo);
            } else {
              Files.setAttribute(pth, "dos:hidden", false);
            }
          } catch (Exception x) {
            skUtilities.prSysE("Sorry Windows only!", getClass().getSimpleName(), x);
          }
        }
      } catch (Exception x) {
        skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName(), x);
      }
    }
  }


  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    path = (Expression<String>) e[0];
    ty = p.mark;
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
    if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
      return CollectionUtils.array(Boolean.class);
    }
    return null;
  }

  @Override
  public Class<? extends Boolean> getReturnType() {
    return Boolean.class;
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
