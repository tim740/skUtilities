package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 21/03/2016
 */
public class EffFileRenameMove extends Effect{
	private Expression<String> path, name;
    private int type;

	@Override
	protected void execute(Event arg0) {
        File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
        if (pth.exists()) {
            if (type == 0) {
                EvtFileRename efn = new EvtFileRename(pth, name.getSingle(arg0));
                Bukkit.getServer().getPluginManager().callEvent(efn);
                if (!efn.isCancelled()) {
                    pth.renameTo(new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator).replaceAll(pth.getName(), name.getSingle(arg0))));
                }
            }else{
                EvtFileMove efm = new EvtFileMove(pth, name.getSingle(arg0));
                Bukkit.getServer().getPluginManager().callEvent(efm);
                if (!efm.isCancelled()) {
                    pth.renameTo(new File("plugins" + File.separator + name.getSingle(arg0).replaceAll("/", File.separator) + File.separator + pth.getName()));
                }
            }
        } else {
            skUtilities.prSys("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        name = (Expression<String>) arg0[1];
        type = arg3.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}