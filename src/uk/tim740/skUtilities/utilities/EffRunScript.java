package uk.tim740.skUtilities.utilities;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;


public class EffRunScript extends Effect{
	private Expression<String> path;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult){
		this.path = (Expression<String>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return this.getClass().getName();
	}

	@Override
	protected void execute(Event arg0) {
		String pth = Bukkit.getServer().getWorldContainer().getAbsolutePath().replace(".", "") + "plugins\\" + this.path.getSingle(arg0).replaceAll("/", Matcher.quoteReplacement(File.separator));
		System.out.println("value " + pth);
		try{
			Runtime.getRuntime().exec(pth);
		}catch (IOException e){
			Skript.warning("[skUtilities] Error: (RunScript) '" + pth + "' isn't a valid path.");
		}
	}
}