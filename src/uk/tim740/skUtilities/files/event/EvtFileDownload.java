package uk.tim740.skUtilities.files.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.nio.file.Path;

/**
 * Created by tim740 on 22/03/2016
 */
public class EvtFileDownload extends Event implements Cancellable {
  private static final HandlerList hls = new HandlerList();
  private boolean cancel = false;
  private String url;
  private Path file;

  public EvtFileDownload(String surl, Path sfile) {
    cancel = false;
    url = surl;
    file = sfile;
  }

  public String getUrl() {
    return url;
  }

  public Path getEvtFile() {
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
