package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uk.tim740.skUtilities.skUtilities;

import java.util.ArrayList;

/**
 * Created by tim740 on 05/10/16
 */
public class ExprGetJsonIDS extends SimpleExpression<String> {
  private Expression<String> ids;
  private Expression<String> t;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    try {
      if (ty == 0) {
        JSONObject j = (JSONObject) new JSONParser().parse(t.getSingle(e));
        return new String[]{j.get(ids.getSingle(e)).toString()};
      } else {
        JSONObject j = (JSONObject) new JSONParser().parse(t.getSingle(e));
        ArrayList<String> cl = new ArrayList<>();
        for (String cid : ids.getAll(e)) {
          cl.add(j.get(cid).toString());
        }
        return cl.toArray(new String[cl.size()]);
      }
    } catch (ParseException x) {
      skUtilities.prSysE("Error while parsing json, if your having issues i recommend switching to `skript-json`", getClass().getSimpleName(), x);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    ids = (Expression<String>) e[0];
    t = (Expression<String>) e[1];
    ty = p.mark;
    return true;
  }

  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public boolean isSingle() {
    return (ty == 0);
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}