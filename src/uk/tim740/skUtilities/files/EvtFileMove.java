package uk.tim740.skUtilities.files;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.File;

/**
 * Created by tim740 on 22/03/2016
 */
public class EvtFileMove extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private File file;
    private String mfile;

    EvtFileMove(File sfile, String smfile) {
        cancel = false;
        file = sfile;
        mfile = smfile;
    }

    public File getEvtFile() {
        return file;
    }
    public String getEvtMFile() {
        return mfile;
    }

    public boolean isCancelled() {
        return cancel;
    }
    public void setCancelled(boolean c) {
        cancel = c;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
