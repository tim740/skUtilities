package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 15/02/2017
 */
public class ExprFilePathSeparator extends SimpleExpression<String> {
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    if (ty == 0) {
      return new String[]{File.separator};
    } else {
      return new String[]{File.pathSeparator};
    }
  }

  @SuppressWarnings("unchecked")
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
