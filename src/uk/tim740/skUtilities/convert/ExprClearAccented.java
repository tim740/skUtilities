package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by tim740 on 03/04/2016
 */
public class ExprClearAccented extends SimpleExpression<String> {
  private Expression<String> str;

  @Override
  @Nullable
  protected String[] get(Event e) {
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return new String[]{pattern.matcher(Normalizer.normalize(str.getSingle(e), Normalizer.Form.NFD)).replaceAll("")};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
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
