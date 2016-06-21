package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 21/03/2016
 */
public class EffFileDownload extends Effect{
	private Expression<String> url, path;

	@Override
	protected void execute(Event arg0) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
        EvtFileDownload efd = new EvtFileDownload(url.getSingle(arg0), pth);
        Bukkit.getServer().getPluginManager().callEvent(efd);
        if (!efd.isCancelled()) {
            Utils.downloadFile(pth, url.getSingle(arg0));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        url = (Expression<String>) arg0[0];
        path = (Expression<String>) arg0[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}