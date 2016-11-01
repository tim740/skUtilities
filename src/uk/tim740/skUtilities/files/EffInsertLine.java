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
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by tim740 on 25/07/2016
 */
public class EffInsertLine extends Effect {
    private Expression<String> txt, path;
    private Expression<Number> line;

    @Override
    protected void execute(Event e) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(e)));
        EvtFileWrite efw = new EvtFileWrite(pth, txt.getSingle(e), 0);
        Bukkit.getServer().getPluginManager().callEvent(efw);
        if (!efw.isCancelled()) {
            try {
                ArrayList<String> cl = new ArrayList<>();
                cl.addAll(Files.readAllLines(pth.toPath(), Charset.defaultCharset()));
                for (Number cn : line.getAll(e)) {
                    if ((cn.intValue() -1) > cl.size()) {
                        Integer dn = ((cn.intValue() -1) -cl.size());
                        for (int n = 0; n < dn; n++) {
                            cl.add("");
                        }
                    }
                    cl.add(cn.intValue() - 1, txt.getSingle(e));
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(pth));
                for (String aCl : cl.toArray(new String[cl.size()])) {
                    bw.write(aCl);
                    bw.newLine();
                }
                bw.close();
            } catch (IOException x) {
                skUtilities.prSysE("File: '" + pth + "' doesn't exist!, or is not readable!", getClass().getSimpleName(), x);
            } catch (Exception x) {
                skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        txt = (Expression<String>) e[0];
        line = (Expression<Number>) e[1];
        path = (Expression<String>) e[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}