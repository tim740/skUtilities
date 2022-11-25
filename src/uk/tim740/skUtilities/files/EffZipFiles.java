package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.files.event.EvtFileZip;
import uk.tim740.skUtilities.skUtilities;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

/**
 * Created by tim740 on 17/03/2016
 */
public class EffZipFiles extends Effect {
  private Expression<String> files;
  private Expression<String> zip;

  @Override
  protected void execute(Event e) {
    Path Fzip = Paths.get(skUtilities.getDefaultPath(zip.getSingle(e)));
    ArrayList<File> cl = new ArrayList<>();
    for (String Spth : files.getAll(e)) {
      cl.add(new File(skUtilities.getDefaultPath(Spth)));
    }
    File[] Fpths = new File[cl.size()];
    File[] s = cl.toArray(Fpths);
    EvtFileZip efz = new EvtFileZip(Fzip, "Files");
    Bukkit.getServer().getPluginManager().callEvent(efz);
    if (!efz.isCancelled()) {
      try {
        FileOutputStream fout = new FileOutputStream(Fzip.toFile());
        ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(fout));
        for (File va : s) {
          FileInputStream fin = new FileInputStream(va);
          zout.putNextEntry(new ZipEntry(va.getName()));
          int il;
          while ((il = fin.read(new byte[1024], 0, 1024)) > 0) {
            zout.write(new byte[1024], 0, il);
          }
          zout.closeEntry();
          fin.close();
        }
        zout.close();
      } catch (ZipException x) {
        skUtilities.prSysE("ZipFile: '" + Fzip + "' doesn't exist!", getClass().getSimpleName(), x);
      } catch (Exception x) {
        skUtilities.prSysE("Files: '" + Arrays.toString(s) + "' 1 or " + s.length + " Files don't exist!", getClass().getSimpleName(), x);
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    files = (Expression<String>) e[0];
    zip = (Expression<String>) e[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
