package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.files.event.EvtFileCreation;
import uk.tim740.skUtilities.skUtilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by tim740 on 16/03/2016
 */
public class EffCreateFile extends Effect {
  private Expression<String> path;
  private int ty;

  @Override
  protected void execute(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    EvtFileCreation efc = new EvtFileCreation(pth);
    Bukkit.getServer().getPluginManager().callEvent(efc);
    if (!efc.isCancelled()) {
      if (ty == 0) {
        try {
          Path fwn = Paths.get(pth.toString().replace(File.separator + pth.toString().substring(pth.toString().lastIndexOf(File.separator) + 1), ""));
          if (!Files.exists(fwn)) {
            Files.createDirectories(fwn);
          }
          Files.createFile(pth);
        } catch (Exception x) {
          skUtilities.prSysE("File: '" + pth + "' already exists!", getClass().getSimpleName(), x);
        }
      } else {
        try {
          Files.createDirectories(pth);
        } catch (Exception x) {
          skUtilities.prSysE("Directory: '" + pth + "' already exists!", getClass().getSimpleName(), x);
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    path = (Expression<String>) e[0];
    ty = p.mark;
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
