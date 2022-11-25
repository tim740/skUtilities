package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740.
 */
public class ExprFromBin extends SimpleExpression<String> {
  private Expression<String> str;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    String bin = str.getSingle(e);
    String binV = bin.trim();
    for (char character : binV.toCharArray()) {
      if (character != '0' && character != '1' && character != ' ') {
        skUtilities.prSysE("Binary Strings can only contain 1's, 0's or spaces!", getClass().getSimpleName());
        return null;
      }
    }
    if (ty == 0) {
      StringBuilder sb = new StringBuilder();
      for (String s : bin.split(" ")) {
        sb.append((char) Integer.parseInt(s, 2));
      }
      return new String[]{sb.toString()};
    } else if (ty == 1) {
      return new String[]{Integer.toString(Integer.parseInt(bin, 2))};
    } else if (ty == 2) {
      return new String[]{Integer.toHexString(Integer.parseInt(bin, 2))};
    } else {
      return new String[]{Integer.toOctalString(Integer.parseInt(bin, 2))};
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    ty = p.mark;
    str = (Expression<String>) e[0];
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
