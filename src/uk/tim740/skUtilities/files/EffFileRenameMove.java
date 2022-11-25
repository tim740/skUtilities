package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.files.event.EvtFileCopy;
import uk.tim740.skUtilities.files.event.EvtFileMove;
import uk.tim740.skUtilities.files.event.EvtFileRename;
import uk.tim740.skUtilities.skUtilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by tim740 on 21/03/2016
 */
public class EffFileRenameMove extends Effect {
  private Expression<String> path, name;
  private int ty;

  @Override
  protected void execute(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    try {
      if (ty == 0) {
        EvtFileRename efn = new EvtFileRename(pth, name.getSingle(e));
        Bukkit.getServer().getPluginManager().callEvent(efn);
        if (!efn.isCancelled()) {
          pth.toFile().renameTo(new File(skUtilities.getDefaultPath(path.getSingle(e).replaceAll(pth.toFile().getName(), name.getSingle(e)))));
        }
      } else if (ty == 1) {
        EvtFileMove efm = new EvtFileMove(pth, name.getSingle(e));
        Bukkit.getServer().getPluginManager().callEvent(efm);
        if (!efm.isCancelled()) {
          Files.move(pth, Paths.get(skUtilities.getDefaultPath(name.getSingle(e) + File.separator + pth.toFile().getName())));
        }
      } else if (ty == 2) {
        EvtFileCopy efc = new EvtFileCopy(pth, name.getSingle(e));
        Bukkit.getServer().getPluginManager().callEvent(efc);
        if (!efc.isCancelled()) {
          Files.copy(pth, Paths.get(skUtilities.getDefaultPath(name.getSingle(e) + File.separator + pth.toFile().getName())));
        }
      } else if (ty == 3) {
        EvtFileMove efm = new EvtFileMove(pth, name.getSingle(e));
        Bukkit.getServer().getPluginManager().callEvent(efm);
        if (!efm.isCancelled()) {
          copyDir(pth, Paths.get(skUtilities.getDefaultPath(name.getSingle(e) + File.separator + pth.toFile().getName())));
          Files.walkFileTree(pth, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path f, BasicFileAttributes attrs) throws IOException {
              Files.delete(f);
              return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path d, IOException exc) throws IOException {
              Files.delete(d);
              return FileVisitResult.CONTINUE;
            }
          });
        }
      } else {
        EvtFileCopy efc = new EvtFileCopy(pth, name.getSingle(e));
        Bukkit.getServer().getPluginManager().callEvent(efc);
        if (!efc.isCancelled()) {
          copyDir(pth, Paths.get(skUtilities.getDefaultPath(name.getSingle(e) + File.separator + pth.toFile().getName())));
        }
      }
    } catch (Exception x) {
      skUtilities.prSysE("File/Directory: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
    }
  }

  private void copyDir(Path pth, Path pf) throws IOException {
    Files.walk(pth).forEach(mpath -> {
      try {
        Files.copy(mpath, Paths.get(mpath.toString().replace(pth.toString(), pf.toString())));
      } catch (IOException x) {
        skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
      }
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    path = (Expression<String>) e[0];
    name = (Expression<String>) e[1];
    ty = p.mark;
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}