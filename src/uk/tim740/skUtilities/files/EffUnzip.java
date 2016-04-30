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
import java.util.zip.ZipInputStream;

/**
 * Created by tim740 on 19/03/2016
 */
public class EffUnzip extends Effect {
    private Expression<String> file, zip;

    @Override
    protected void execute(Event arg0) {
        File Fzip = new File(Utils.getDefaultPath() + zip.getSingle(arg0));
        File pth = new File(Utils.getDefaultPath() + file.getSingle(arg0));
        if (Fzip.exists()) {
            try{
                EvtUnzip euz = new EvtUnzip(Fzip, pth.toString());
                Bukkit.getServer().getPluginManager().callEvent(euz);
                if (!euz.isCancelled()) {
                    if (!pth.exists()) {
                        pth.mkdir();
                    }
                    ZipInputStream zis = new ZipInputStream(new FileInputStream(Fzip));
                    ZipEntry ze = zis.getNextEntry();
                    while (ze != null) {
                        File nf = new File(pth + File.separator + ze.getName());
                        new File(nf.getParent()).mkdirs();
                        FileOutputStream fos = new FileOutputStream(nf);
                        int len;
                        while ((len = zis.read(new byte[1024])) > 0) {
                            fos.write(new byte[1024], 0, len);
                        }
                        fos.close();
                        ze = zis.getNextEntry();
                    }
                    zis.closeEntry();
                    zis.close();
                }
            }catch (IOException e){
                skUtilities.prSys(e.getMessage(), getClass().getSimpleName(), 0);
            }
        }else{
            skUtilities.prSys("ZipFile: '" + Fzip + "' doesn't exist!", getClass().getSimpleName(), 0);
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
