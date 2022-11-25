package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by tim740 on 07/03/16
 */
public class ExprBase64 extends SimpleExpression<String> {
  private Expression<String> b64;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    if (ty == 0) {
      return new String[]{Base64.getEncoder().encodeToString(b64.getSingle(e).getBytes(StandardCharsets.UTF_8))};
    } else {
      return new String[]{new String(Base64.getDecoder().decode(b64.getSingle(e).getBytes(StandardCharsets.UTF_8)))};
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    ty = p.mark;
    b64 = (Expression<String>) e[0];
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