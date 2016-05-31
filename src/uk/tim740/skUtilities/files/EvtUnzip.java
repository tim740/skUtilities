package uk.tim740.skUtilities.files;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.File;

/**
 * Created by tim740 on 23/03/2016
 */
public class EvtUnzip extends Event implements Cancellable {
    private static final HandlerList hls = new HandlerList();
    private boolean cancel = false;
    private File file;
    private String nloc;

    EvtUnzip(File sfile, String snloc) {
        cancel = false;
        file = sfile;
        nloc = snloc;
    }

    public File getEvtFile() {
        return file;
    }
    public String getEvtNloc() {
        return nloc;
    }

    public boolean isCancelled() {
        return cancel;
    }
    public void setCancelled(boolean c) {
        cancel = c;
    }

    @Override
    public HandlerList getHandlers() {
        return hls;
    }
    public static HandlerList getHandlerList() {
        return hls;
    }
}
