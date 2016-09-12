package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.files.event.EvtFileCopy;
import uk.tim740.skUtilities.files.event.EvtFileMove;
import uk.tim740.skUtilities.files.event.EvtFileRename;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by tim740 on 21/03/2016
 */
public class EffFileRenameMove extends Effect{
	private Expression<String> path, name;
    private int ty;

	@Override
	protected void execute(Event e) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(e)));
        try {
            if (ty == 0) {
                EvtFileRename efn = new EvtFileRename(pth, name.getSingle(e));
                Bukkit.getServer().getPluginManager().callEvent(efn);
                if (!efn.isCancelled()) {
                    pth.renameTo(new File(Utils.getDefaultPath(path.getSingle(e).replaceAll(pth.getName(), name.getSingle(e)))));
                }
            }else if (ty == 1){
                EvtFileMove efm = new EvtFileMove(pth, name.getSingle(e));
                Bukkit.getServer().getPluginManager().callEvent(efm);
                if (!efm.isCancelled()) {
                    Files.move(pth.toPath(), Paths.get(Utils.getDefaultPath(name.getSingle(e) + File.separator + pth.getName())));
                }
            }else{
                EvtFileCopy efc = new EvtFileCopy(pth, name.getSingle(e));
                Bukkit.getServer().getPluginManager().callEvent(efc);
                if (!efc.isCancelled()) {
                    Files.copy(pth.toPath(), Paths.get(Utils.getDefaultPath(name.getSingle(e) + File.separator + pth.getName())));
                }
            }
        } catch (IOException x) {
            skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        path = (Expression<String>) e[0];
        name = (Expression<String>) e[1];
        ty = p.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}