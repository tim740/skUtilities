package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
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
        File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
        if (!pth.exists()) {
            if (type == 0) {
                try {
                    EvtFileCreation efc = new EvtFileCreation(pth);
                    Bukkit.getServer().getPluginManager().callEvent(efc);
                    if (!efc.isCancelled()) {
                        pth.createNewFile();
                    }
                } catch (IOException e) {
                    skUtilities.prSys(e.getMessage(), getClass().getSimpleName(), 0);
                }

            } else {
                skUtilities.prSys("'" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            }
        } else {
            if (type == 0) {
                skUtilities.prSys("'" + pth + "' already exists!", getClass().getSimpleName(), 0);
            } else {
                try {
                    EvtFileDeletion efd = new EvtFileDeletion(pth);
                    Bukkit.getServer().getPluginManager().callEvent(efd);
                    if (!efd.isCancelled()) {
                        pth.delete();
                    }
                } catch (Exception e) {
                    skUtilities.prSys(e.getMessage(), getClass().getSimpleName(), 0);
                }
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
