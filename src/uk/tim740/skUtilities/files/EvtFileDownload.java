package uk.tim740.skUtilities.files;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.File;

/**
 * Created by tim740 on 22/03/2016
 */
public class EvtFileDownload extends Event implements Cancellable {
    private static final HandlerList hls = new HandlerList();
    private boolean cancel = false;
    private String url;
    private File file;

    EvtFileDownload(String surl, File sfile) {
        cancel = false;
        url = surl;
        file = sfile;
    }

    public String getUrl() {
        return url;
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
    @SuppressWarnings("unused")
    public static HandlerList getHandlerList() {
        return hls;
    }
}
