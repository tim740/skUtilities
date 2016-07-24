package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.files.event.EvtFileZip;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by tim740 on 22/07/2016
 */
public class EffZipDirectory extends Effect {
    private Expression<String> file, zip;

    @Override
    protected void execute(Event arg0) {
        Path Dpth = Paths.get(Utils.getDefaultPath(file.getSingle(arg0)));
        File Fzip = new File(Utils.getDefaultPath(zip.getSingle(arg0)));
        EvtFileZip efz = new EvtFileZip(Fzip, Dpth.toString());
        Bukkit.getServer().getPluginManager().callEvent(efz);
        if (!efz.isCancelled()) {
            try {
                final Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                final URI uri = URI.create("jar:file:/" + Fzip.getAbsolutePath().replace("\\", "/"));
                try (final FileSystem zfs = FileSystems.newFileSystem(uri, env);
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
                        } catch (IOException e) {
                            skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
                        }
                    });
                }
            } catch (FileSystemAlreadyExistsException e) {
                skUtilities.prSysE("ZipFile: '" + Fzip + "' already exists!", getClass().getSimpleName(), e);
            } catch (FileNotFoundException e) {
                skUtilities.prSysE("Directory: '" + Dpth + "' doesn't exist, or doesn't have write permission!", getClass().getSimpleName(), e);
            } catch (IOException e) {
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        file = (Expression<String>) arg0[0];
        zip = (Expression<String>) arg0[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
