package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.files.event.EvtUnzip;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tim740 on 19/03/2016
 */
public class EffUnzip extends Effect {
    private Expression<String> file, zip;

    @Override
    protected void execute(Event arg0) {
        File Fzip = new File(Utils.getDefaultPath(zip.getSingle(arg0)));
        Path pth = Paths.get(Utils.getDefaultPath(file.getSingle(arg0)));
        EvtUnzip euz = new EvtUnzip(Fzip, pth.toString());
        Bukkit.getServer().getPluginManager().callEvent(euz);
        if (!euz.isCancelled()) {
            try {
                final Map<String, String> env = new HashMap<>();
                env.put("create", "true");
                if (Files.notExists(pth)) {
                    Files.createDirectories(pth);
                }
                try (FileSystem zfs = FileSystems.newFileSystem(URI.create("jar:file:/" + Fzip.getAbsolutePath().replace("\\", "/")), env)) {
                    Files.walkFileTree(zfs.getPath("/"), new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path ftc, BasicFileAttributes ats) throws IOException {
                            Files.copy(ftc, Paths.get(pth.toString(), ftc.toString()), StandardCopyOption.REPLACE_EXISTING);
                            return FileVisitResult.CONTINUE;
                        }
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes ats) throws IOException {
                            final Path dtc = Paths.get(pth.toString(), dir.toString());
                            if (Files.notExists(dtc)) {
                                Files.createDirectory(dtc);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });
                }
            } catch (IOException e) {
                skUtilities.prSysE("ZipFile: '" + Fzip + "' doesn't exist, or doesn't have write permission!", getClass().getSimpleName(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        zip = (Expression<String>) arg0[0];
        file = (Expression<String>) arg0[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
