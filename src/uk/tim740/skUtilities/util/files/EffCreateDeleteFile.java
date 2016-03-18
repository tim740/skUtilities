package uk.tim740.skUtilities.util.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

/**
 * Created by tim740 on 16/03/2016
 */
public class EffCreateDeleteFile extends Effect {
    private Expression<String> path;
    private int type;

    @Override
    protected void execute(Event arg0) {
        File pth = new File("plugins\\" + path.getSingle(arg0).replaceAll("/", "\\"));
        if (!pth.exists()) {
            if (type == 0) {
                try {
                    pth.createNewFile();
                } catch (IOException e) {
                    skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
                }
                skUtilities.prEW("Created file: '" + pth + "'", getClass().getSimpleName(), 1);
            } else {
                skUtilities.prEW("'" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            }
        } else {
            if (type == 0) {
                skUtilities.prEW("'" + pth + "' already exists!", getClass().getSimpleName(), 0);
            } else {
                try {
                    pth.delete();
                } catch (Exception e) {
                    skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
                }
                skUtilities.prEW("Deleted file: '" + pth + "'", getClass().getSimpleName(), 1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        type = arg3.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
