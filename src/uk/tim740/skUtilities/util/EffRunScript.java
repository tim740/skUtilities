package uk.tim740.skUtilities.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import uk.tim740.skUtilities.Main;

/**
 * Created by tim740
 */
public class EffRunScript extends Effect{
	private Expression<String> path;

	@Override
	protected void execute(Event arg0) {
		String pth = new File("plugins\\", this.path.getSingle(arg0)).getPath().replaceAll("/", Matcher.quoteReplacement(File.separator));
		try{
			if(!new File(pth).exists()){
				throw new IOException();
			}else{
				Desktop.getDesktop().open(new File(pth));
			}
		}catch (IOException e){
            Main.prErr("'" + pth + "' isn't a valid path!");
		}
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        this.path = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return this.getClass().getName();
    }
}