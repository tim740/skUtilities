package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.event.Event;
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
        File pth = new File("plugins" + File.separator + file.getSingle(arg0).replaceAll("/", File.separator));
        File Fzip = new File("plugins" + File.separator + zip.getSingle(arg0).replaceAll("/", File.separator));
        if (Fzip.exists()) {
            String ext = "." + FilenameUtils.getExtension(Fzip.toString());
            String frt = Fzip.toString().replaceAll(ext, "") + " - Copy (" + System.currentTimeMillis() /1000 +")" + ext;
            skUtilities.prEW("File: '" + Fzip + "' already exists, Renaming to: " + frt.substring(frt.lastIndexOf(File.separator) + 1), getClass().getSimpleName(), 1);
            Fzip = new File(frt);
        } try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(Fzip));
            zos.putNextEntry(new ZipEntry(file.getSingle(arg0).replaceAll("/", File.separator)));
            FileInputStream in = new FileInputStream(pth);
            zos.write(new byte[1024], 0, in.read(new byte[1024]));
            in.close();
            zos.closeEntry();
            zos.close();
        } catch (ZipException e) {
            skUtilities.prEW("ZipFile: '" + Fzip + "' doesn't exist!", getClass().getSimpleName(), 0);
        } catch (FileNotFoundException e) {
            skUtilities.prEW("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
        } catch (IOException e) {
            skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
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
