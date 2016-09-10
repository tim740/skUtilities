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

import javax.annotation.Nullable;
import java.util.Iterator;

/**
 * Created by tim740.
 */
public class ExprLoaded extends SimpleExpression<Number>{
	private int ty;

	@Override
	@Nullable
	protected Number[] get(Event e) {
		if (ty == 0){
			return new Number[]{ScriptLoader.loadedCommands()};
		}else if (ty == 1){
			return new Number[]{ScriptLoader.loadedFunctions()};
		}else if (ty == 2){
			return new Number[]{ScriptLoader.loadedScripts()};
		}else if (ty == 3){
			return new Number[]{ScriptLoader.loadedTriggers()};
		}else if (ty == 4){
			return new Number[]{Skript.getStatements().size()};
		}else if (ty == 5){
			return new Number[]{Variables.numVariables()};
		}else if (ty == 6){
			//System.out.println("aliases loaded " + (ScriptLoader.getScriptAliases()).getClass());
			return new Number[]{ScriptLoader.getScriptAliases().size()};
		}else if (ty == 7){
			return new Number[]{Skript.getEvents().size()};
		}else if (ty == 8){
			return new Number[]{Skript.getEffects().size()};
		}else if (ty == 9){
			int size = 0;
			Iterator<ExpressionInfo<?, ?>> exprs = Skript.getExpressions();
			while(exprs.hasNext()) {
			  exprs.next();
			  size += 1;
			}
			return new Number[]{size};
		}else{
			return new Number[]{Skript.getConditions().size()};
		}
	}

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        ty = arg3.mark;
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
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}