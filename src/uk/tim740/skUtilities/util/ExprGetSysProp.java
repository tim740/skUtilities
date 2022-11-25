package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.apache.commons.lang3.SystemUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;


/**
 * Created by tim740 on 10/04/2017
 */
public class ExprGetSysProp extends SimpleExpression<String> {
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    switch (ty) {
      case 0:
        return new String[]{SystemUtils.OS_ARCH};
      case 1:
        return new String[]{SystemUtils.OS_NAME};
      case 2:
        return new String[]{SystemUtils.OS_VERSION};
      case 3:
        return new String[]{SystemUtils.getJavaHome().toString()};
      case 4:
        return new String[]{SystemUtils.getUserDir().toString()};
      case 5:
        return new String[]{SystemUtils.getUserHome().toString()};
      case 6:
        return new String[]{SystemUtils.USER_NAME};
      case 7:
        return new String[]{SystemUtils.USER_LANGUAGE};
      case 8:
        return new String[]{SystemUtils.USER_TIMEZONE};
      case 9:
        return new String[]{SystemUtils.LINE_SEPARATOR};
      case 10:
        return new String[]{SystemUtils.FILE_SEPARATOR};
      case 11:
        return new String[]{SystemUtils.PATH_SEPARATOR};
      case 12:
        return new String[]{SystemUtils.FILE_ENCODING};
    }
    return null;
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
