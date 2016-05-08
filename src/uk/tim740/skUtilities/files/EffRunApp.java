package uk.tim740.skUtilities.files;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.skUtilities;

/**
 * Created by tim740
 */
public class EffRunApp extends Effect{
	private Expression<String> path;

	@Override
	protected void execute(Event arg0) {
        File pth = new File(Utils.getDefaultPath() + path.getSingle(arg0));
        if (Desktop.isDesktopSupported()){
            try{
                EvtRunApp era = new EvtRunApp(pth);
                Bukkit.getServer().getPluginManager().callEvent(era);
                if (!era.isCancelled()) {
                    if(!pth.exists()){
                        throw new IOException();
                    }else{
                        Desktop.getDesktop().open(pth);
                    }
                }
            }catch (IOException e){
                skUtilities.prSys("'" + pth + "' isn't a valid path!", getClass().getSimpleName(), 0);
            }
        }else{
            String p = System.getProperty("os.name").toLowerCase();
            Boolean gc = false;
            if (p.contains("solaris")) gc = true;
            if (p.contains("sunos")) gc = true;
            if (p.contains("linux")) gc = true;
            if (p.contains("unix")) gc = true;
            if (gc.equals(true)){
                skUtilities.prSysi("");
                skUtilities.prSysi("Looks like your using a linux based system and don't have");
                skUtilities.prSysi("libgnome installed execute the command below in the terminal ");
                skUtilities.prSysi("'sudo apt-get install libgnome2-0'");
                skUtilities.prSysi("and then restart the system!");
                skUtilities.prSysi("");
            }else{
                skUtilities.prSys("Sorry this OS ('" + p +"')isn't supported!", getClass().getSimpleName(), 0);
            }
            /*skUtilities.prSysi("");
            skUtilities.prSysi("If you using a 'Linux Based System' you could try installing");
            skUtilities.prSysi("libgnome: 'sudo apt-get install libgnome2-0'");
            skUtilities.prSysi("and then restart the system!");
            skUtilities.prSysi("");*/
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}