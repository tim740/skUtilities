package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tim740 on 22/02/2016
 */
public class ExprMorse extends SimpleExpression<String> {
  private int ty;
  private Expression<String> str;
  private static final char[] engL = {' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  private static final String[] morseL = {"_", "-----", ".----", "..---", "...--", "....-", "......", "-.....", "--...", "---..", "----.",
      ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

  @Override
  @Nullable
  protected String[] get(Event e) {
    String out = "";
    if (ty == 0) {
      for (char v : str.getSingle(e).toLowerCase().toCharArray()) {
        for (int j = 0; j < engL.length; j++) {
          if (engL[j] == v) {
            out += morseL[j] + " ";
            break;
          }
        }
      }
    } else {
      for (String w : str.getSingle(e).split("\\s\\s\\s")) {
        for (String l : w.split("\\s")) {
          for (int j = 0; j < morseL.length; j++) {
            if (l.equals(morseL[j])) {
              out += engL[j];
              break;
            }
          }
        }
      }
    }
    return new String[]{out};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
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
