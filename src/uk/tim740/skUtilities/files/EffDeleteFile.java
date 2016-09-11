package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.files.event.EvtFileDeletion;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 16/03/2016
 */
public class EffDeleteFile extends Effect {
    private Expression<String> path;

    @Override
    protected void execute(Event e) {
        Path pth = Paths.get(Utils.getDefaultPath(path.getSingle(e)));
        try {
            EvtFileDeletion efd = new EvtFileDeletion(pth.toFile());
            Bukkit.getServer().getPluginManager().callEvent(efd);
            if (!efd.isCancelled()) {
                Files.delete(pth);
            }
        } catch (IOException x) {
            skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        path = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
