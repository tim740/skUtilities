package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

/**
 * Created by tim740 on 22/07/2016
 */
public class EffZipDirectory extends Effect {
    private Expression<String> file, zip;

    @Override
    protected void execute(Event arg0) {
        File Dpth = new File(Utils.getDefaultPath(file.getSingle(arg0)));
        File Fzip = new File(Utils.getDefaultPath(zip.getSingle(arg0)));
        EvtFileZip efz = new EvtFileZip(Fzip, Dpth.toString());
        Bukkit.getServer().getPluginManager().callEvent(efz);
        if (!efz.isCancelled()) {
            try {
                FileOutputStream fout = new FileOutputStream(Fzip);
                ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(fout));
                File[] s = Dpth.listFiles();
                assert s != null;
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
            } catch (ZipException e) {
                skUtilities.prSys("ZipFile: '" + Fzip + "' doesn't exist!", getClass().getSimpleName(), 0);
            } catch (FileNotFoundException e) {
                skUtilities.prSys("Directory: '" + Dpth + "' doesn't exist!", getClass().getSimpleName(), 0);
            } catch (IOException e) {
                skUtilities.prSys(e.getMessage(), getClass().getSimpleName(), 0);
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
