package uk.tim740.skUtilities.files;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.files.event.EvtFileWipe;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;

/**
 * Created by tim740 on 20/03/2016
 */
public class SExprFileContents extends SimpleExpression<String> {
    private Expression<String> path;

    @Override
    @Nullable
    protected String[] get(Event e) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(e)));
        try {
            BufferedReader br = new BufferedReader(new FileReader(pth));
            String[] s = br.lines().toArray(String[]::new);
            br.close();
            return s;
        } catch (IOException x) {
            skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName(), x);
        } catch (Exception x) {
            skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
        }
        return null;
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
            File pth = new File(Utils.getDefaultPath(path.getSingle(e)));
            try {
                if (mode == Changer.ChangeMode.SET) {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(pth));
                    for (String aCl : (String[]) delta) {
                        bw.write(aCl);
                        bw.newLine();
                    }
                    bw.close();
                } else {
                    EvtFileWipe efw = new EvtFileWipe(pth);
                    Bukkit.getServer().getPluginManager().callEvent(efw);
                    if (!efw.isCancelled()) {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(pth));
                        bw.write("");
                        bw.close();
                    }
                }
            } catch (IOException x) {
                skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName());
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        path = (Expression<String>) e[0];
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String[].class);
        }
        return null;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
