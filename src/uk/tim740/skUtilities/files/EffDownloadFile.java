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
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by tim740 on 21/03/2016
 */
public class EffDownloadFile extends Effect{
	private Expression<String> url, path;

	@Override
	protected void execute(Event arg0) {
        File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
        try {
            EvtDownloadFile efd = new EvtDownloadFile(url.getSingle(arg0), pth);
            Bukkit.getServer().getPluginManager().callEvent(efd);
            if (!efd.isCancelled()) {
                ReadableByteChannel rbc = Channels.newChannel(new URL(url.getSingle(arg0)).openStream());
                FileOutputStream fos = new FileOutputStream(pth);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                fos.close();
                rbc.close();
            }
        } catch (Exception e) {
            skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
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