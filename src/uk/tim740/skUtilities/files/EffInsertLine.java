package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.files.event.EvtFileWrite;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by tim740 on 25/07/2016
 */
public class EffInsertLine extends Effect{
	private Expression<String> txt, path;
    private Expression<Number> line;

	@Override
	protected void execute(Event arg0) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
        EvtFileWrite efw = new EvtFileWrite(pth, txt.getSingle(arg0), line.getSingle(arg0));
        Bukkit.getServer().getPluginManager().callEvent(efw);
        if (!efw.isCancelled()) {
            try {
                ArrayList<String> cl = new ArrayList<>();
                cl.addAll(Files.readAllLines(pth.toPath()));
                cl.add(line.getSingle(arg0).intValue() - 1, txt.getSingle(arg0));
                String[] out = new String[cl.size()];
                BufferedWriter bw = new BufferedWriter(new FileWriter(pth));
                for (String aCl : cl.toArray(out)) {
                    bw.write(aCl);
                    bw.newLine();
                }
                bw.close();
            } catch (Exception e) {
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName());
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        txt = (Expression<String>) arg0[0];
        line = (Expression<Number>) arg0[1];
        path = (Expression<String>) arg0[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}