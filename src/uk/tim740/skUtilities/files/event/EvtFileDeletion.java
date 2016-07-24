package uk.tim740.skUtilities.files.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.File;

/**
 * Created by tim740 on 22/03/2016
 */
public class EvtFileDeletion extends Event implements Cancellable {
    private static final HandlerList hls = new HandlerList();
    private boolean cancel = false;
    private File file;

    public EvtFileDeletion(File sfile) {
        cancel = false;
        file = sfile;
    }

    public File getEvtFile() {
        return file;
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
