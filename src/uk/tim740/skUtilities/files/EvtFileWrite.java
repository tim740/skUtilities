package uk.tim740.skUtilities.files;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.File;

/**
 * Created by tim740 on 23/03/2016
 */
public class EvtFileWrite extends Event implements Cancellable {
    private static final HandlerList hls = new HandlerList();
    private boolean cancel = false;
    private File file;
    private String txt;
    private Number line;

    EvtFileWrite(File sfile, String stxt, Number sline) {
        cancel = false;
        file = sfile;
        txt = stxt;
        line = sline;
    }

    public File getEvtFile() {
        return file;
    }
    public String getEvtFileTxt() {
        return txt;
    }
    public Number getEvtFileLine() {
        return line;
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