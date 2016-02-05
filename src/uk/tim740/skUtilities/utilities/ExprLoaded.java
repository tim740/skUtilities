package uk.tim740.skUtilities.utilities;

import javax.annotation.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;

public class ExprLoaded extends SimpleExpression<Number>{
	private int type;

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}
	@Override
	public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2, ParseResult arg3) {
		type = arg3.mark;
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return this.getClass().getName();
	}

	@Override
	@Nullable
	protected Number[] get(Event e) {
		if (type == 0){
			return new Number[]{ScriptLoader.loadedCommands()};
		}else if (type == 1){
			return new Number[]{ScriptLoader.loadedFunctions()};
		}else if (type == 2){
			return new Number[]{ScriptLoader.loadedScripts()};
		}else if (type == 3){
			return new Number[]{ScriptLoader.loadedTriggers()};
		}else if (type == 4){
			return new Number[]{Skript.getStatements().size()};
		}else if (type == 5){
			return new Number[]{Variables.numVariables()};
		}else if (type == 6){
			System.out.println("aliases loaded " + ScriptLoader.getScriptAliases().values());
			return new Number[]{ScriptLoader.getScriptAliases().values().size()};
		}else if (type == 7){
			return new Number[]{Bukkit.getServer().getPluginManager().getPlugins().length};
		}else if (type == 8){
			return new Number[]{Skript.getAddons().size()};
		}else if (type == 9){
			return new Number[]{Skript.getEvents().size()};
		}else if (type == 10){
			return new Number[]{Skript.getEffects().size()};
/*		}else if (type == 11){//TODO
			Class[] reTy = null;
			reTy[0] = String.class;
	        reTy[1] = Number.class;
			System.out.println("-" + Skript.getExpressions(String.class));
			int i = Integer.parseInt(Skript.getExpressions(String.class).toString()) + Integer.parseInt(Skript.getExpressions(Number.class).toString()) + Integer.parseInt(Skript.getExpressions(Integer.class).toString()) + Integer.parseInt(Skript.getExpressions(Object.class).toString());
			return new Number[]{((CharSequence) Skript.getExpressions(reTy)).length()};*/
		}else{
			return new Number[]{Skript.getConditions().size()};
		}
	}
}