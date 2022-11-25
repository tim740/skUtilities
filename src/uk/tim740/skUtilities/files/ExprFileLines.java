package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.nio.charset.Charset;
import java.nio.file.*;

/**
 * Created by tim740 on 17/03/2016
 */
public class ExprFileLines extends SimpleExpression<Number> {
  private Expression<String> path;

  @Override
  @Nullable
  protected Number[] get(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    try {
      return new Number[]{Files.lines(pth, Charset.defaultCharset()).count()};
    } catch (NoSuchFileException x) {
      skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
    } catch (AccessDeniedException x) {
      skUtilities.prSysE("File: '" + pth + "' is read only!", getClass().getSimpleName(), x);
    } catch (Exception x) {
      skUtilities.prSysE("File: '" + pth + "' " + x.getMessage(), getClass().getSimpleName(), x);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    path = (Expression<String>) e[0];
    return true;
  }

  @Override
  public Class<? extends Number> getReturnType() {
    return Number.class;
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
