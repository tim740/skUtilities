package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.files.event.EvtFileDownload;
import uk.tim740.skUtilities.skUtilities;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 21/03/2016
 */
public class EffFileDownload extends Effect {
  private Expression<String> url, path;

  @Override
  protected void execute(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    EvtFileDownload efd = new EvtFileDownload(url.getSingle(e), pth);
    Bukkit.getServer().getPluginManager().callEvent(efd);
    if (!efd.isCancelled()) {
      skUtilities.downloadFile(pth, url.getSingle(e));
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    url = (Expression<String>) e[0];
    path = (Expression<String>) e[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}