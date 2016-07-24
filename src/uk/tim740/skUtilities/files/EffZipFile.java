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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

/**
 * Created by tim740 on 17/03/2016
 */
public class EffZipFile extends Effect {
    private Expression<String> files;
    private Expression<String> zip;

    @Override
    protected void execute(Event arg0) {
        File Fzip = new File(Utils.getDefaultPath(zip.getSingle(arg0)));
        ArrayList<File> cl = new ArrayList<>();
        for (String Spth : files.getAll(arg0)) {
            cl.add(new File(Utils.getDefaultPath(Spth)));
        }
        File[] Fpths = new File[cl.size()];
        File[] s = cl.toArray(Fpths);
        EvtFileZip efz = new EvtFileZip(Fzip, "Files");
        Bukkit.getServer().getPluginManager().callEvent(efz);
        if (!efz.isCancelled()) {
            try {
                FileOutputStream fout = new FileOutputStream(Fzip);
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
            } catch (ZipException e) {
                skUtilities.prSysE("ZipFile: '" + Fzip + "' doesn't exist!", getClass().getSimpleName(), e);
            } catch (FileNotFoundException e) {
                skUtilities.prSysE("Files: '" + Arrays.toString(s) + "' 1 or " + s.length + " Files don't exist!", getClass().getSimpleName(), e);
            } catch (IOException e) {
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        files = (Expression<String>) arg0[0];
        zip = (Expression<String>) arg0[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
