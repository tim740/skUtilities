package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.time.ZoneId;
import java.util.ArrayList;

/**
 * Created by tim740 on 11/09/2016
 */
public class ExprTimeZoneList extends SimpleExpression<String> {

  @Override
  @Nullable
  protected String[] get(Event e) {
    ArrayList<String> cl = new ArrayList<>();
    cl.addAll(ZoneId.getAvailableZoneIds());
    return cl.toArray(new String[cl.size()]);
  }

  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    return true;
  }

  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public boolean isSingle() {
    return false;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
