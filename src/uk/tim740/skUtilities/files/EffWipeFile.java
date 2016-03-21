package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by tim740 on 21/03/2016
 */
public class EffWipeFile extends Effect{
	private Expression<String> path;

	@Override
	protected void execute(Event arg0) {
        File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
        if (pth.exists()) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(pth));
                bw.write("");
                bw.close();
            } catch (IOException e) {
                skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
            }
       } else {
            skUtilities.prEW("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}