package uk.tim740.skUtilities.util;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionInfo;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

/**
 * Created by tim740.
 */
public class ExprLoaded extends SimpleExpression<Number> {
  private int ty;

  @Override
  @Nullable
  protected Number[] get(Event e) {
    switch (ty) {
      case 0: {
        return new Number[]{ScriptLoader.loadedCommands()};
      } case 1: {
        return new Number[]{ScriptLoader.loadedFunctions()};
      } case 2: {
        return new Number[]{ScriptLoader.loadedScripts()};
      } case 3: {
        return new Number[]{ScriptLoader.loadedTriggers()};
      } case 4: {
        return new Number[]{Skript.getStatements().size()};
      } case 5: {
        return new Number[]{Variables.numVariables()};
      } case 6: {
        //System.out.println("aliases loaded " + (ScriptLoader.getScriptAliases()).getClass());
        return new Number[]{0};
      } case 7: {
        return new Number[]{Skript.getEvents().size()};
      } case 8: {
        return new Number[]{Skript.getEffects().size()};
      } case 9: {
        int size = 0;
        Iterator<ExpressionInfo<?, ?>> exprs = Skript.getExpressions();
        while (exprs.hasNext()) {
          exprs.next();
          size += 1;
        }
        return new Number[]{size};
      } default: {
        return new Number[]{Skript.getConditions().size()};
      }
    }
  }

  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    ty = p.mark;
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