package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.apache.commons.io.FilenameUtils;
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
 * Created by tim740 on 17/03/2016
 */
public class EffZipFile extends Effect {
    private Expression<String> file, zip;

    @Override
    protected void execute(Event arg0) {
        File pth = new File(Utils.getDefaultPath() + file.getSingle(arg0));
        File Fzip = new File(Utils.getDefaultPath() + zip.getSingle(arg0));
        EvtFileZip efz = new EvtFileZip(Fzip, pth.toString());
        Bukkit.getServer().getPluginManager().callEvent(efz);
        if (!efz.isCancelled()) {
            if (Fzip.exists()) {
                String ext = "." + FilenameUtils.getExtension(Fzip.toString());
                String frt = Fzip.toString().replaceAll(ext, "") + " - Copy (" + System.currentTimeMillis() / 1000 + ")" + ext;
                skUtilities.prSys("File: '" + Fzip + "' already exists, Renaming to: " + frt.substring(frt.lastIndexOf(File.separator) + 1), getClass().getSimpleName(), 1);
                Fzip = new File(frt);
            }
            try {
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(Fzip));
                zos.putNextEntry(new ZipEntry(file.getSingle(arg0)));
                FileInputStream in = new FileInputStream(pth);
                zos.write(new byte[1024], 0, in.read(new byte[1024]));
                in.close();
                zos.closeEntry();
                zos.close();
            } catch (ZipException e) {
                skUtilities.prSys("ZipFile: '" + Fzip + "' doesn't exist!", getClass().getSimpleName(), 0);
            } catch (FileNotFoundException e) {
                skUtilities.prSys("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
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
