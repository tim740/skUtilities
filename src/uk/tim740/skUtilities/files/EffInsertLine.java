package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.files.event.EvtFileWrite;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Created by tim740 on 25/07/2016
 */
public class EffInsertLine extends Effect {
    private Expression<String> txt, path;
    private Expression<Number> line;

    @Override
    protected void execute(Event e) {
        Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
        EvtFileWrite efw = new EvtFileWrite(pth, txt.getSingle(e), 0);
        Bukkit.getServer().getPluginManager().callEvent(efw);
        if (!efw.isCancelled()) {
            try {
                ArrayList<String> cl = new ArrayList<>();
                cl.addAll(Files.readAllLines(pth, Charset.defaultCharset()));
                for (Number cn : line.getAll(e)) {
                    if ((cn.intValue() -1) > cl.size()) {
                        Integer dn = ((cn.intValue() -1) -cl.size());
                        for (int n = 0; n < dn; n++) {
                            cl.add("");
                        }
                    }
                    cl.add(cn.intValue() - 1, txt.getSingle(e));
                }
                Files.write(pth, "".getBytes());
                for (String aCl : cl.toArray(new String[cl.size()])) {
                    Files.write(pth, (aCl + "\n").getBytes(), StandardOpenOption.APPEND);
                }
            } catch (Exception x) {
                skUtilities.prSysE("File: '" + pth + "' doesn't exist!, or is not readable!", getClass().getSimpleName(), x);
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