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

import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by tim740 on 22/07/2016
 */
public class EffZipDirectory extends Effect {
  private Expression<String> file, zip;

  @Override
  protected void execute(Event e) {
    Path Dpth = Paths.get(skUtilities.getDefaultPath(file.getSingle(e)));
    Path Fzip = Paths.get(skUtilities.getDefaultPath(zip.getSingle(e)));
    EvtFileZip efz = new EvtFileZip(Fzip, Dpth.toString());
    Bukkit.getServer().getPluginManager().callEvent(efz);
    if (!efz.isCancelled()) {
      try {
        final Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        try (final FileSystem zfs = FileSystems.newFileSystem(URI.create("jar:file:/" + Fzip.normalize().toAbsolutePath().toString().replace("\\", "/")), env);
             final Stream<Path> files = Files.walk(Dpth)) {
          final Path rt = zfs.getPath("/");
          files.forEach(cf -> {
            try {
              final Path to = rt.resolve(Dpth.relativize(cf).toString());
              if (Files.isDirectory(cf)) {
                Files.createDirectories(to);
              } else {
                Files.copy(cf, to);
              }
            } catch (Exception x) {
              skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
            }
          });
        }
      } catch (FileSystemAlreadyExistsException x) {
        skUtilities.prSysE("ZipFile: '" + Fzip + "' already exists!", getClass().getSimpleName(), x);
      } catch (Exception x) {
        skUtilities.prSysE("Directory: '" + Dpth + "' doesn't exist, or doesn't have write permission!", getClass().getSimpleName(), x);
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    file = (Expression<String>) e[0];
    zip = (Expression<String>) e[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
