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
import uk.tim740.skUtilities.files.event.EvtFileWrite;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by tim740 on 18/03/2016
 */
public class SExprEditLine extends SimpleExpression<String> {
    private Expression<Number> line;
    private Expression<String> path;

    @Override
    @Nullable
    protected String[] get(Event e) {
        Path pth = Paths.get(Utils.getDefaultPath(path.getSingle(e)));
        try (Stream<String> lines = Files.lines(pth)) {
            //noinspection OptionalGetWithoutIsPresent
            return new String[]{lines.skip(Integer.parseInt(line.getSingle(e).toString()) - 1).findFirst().get()};
        } catch (IOException x) {
            skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName(), x);
        }
        return null;
    }

    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            File pth = new File(Utils.getDefaultPath(path.getSingle(e)));
            EvtFileWrite efw = new EvtFileWrite(pth, (String) delta[0], line.getSingle(e));
            Bukkit.getServer().getPluginManager().callEvent(efw);
            if (!efw.isCancelled()) {
                try {
                    ArrayList<String> cl = new ArrayList<>();
                    cl.addAll(Files.readAllLines(pth.toPath()));
                    cl.set(Integer.parseInt(line.getSingle(e).toString()) - 1, (String) delta[0]);
                    String[] out = new String[cl.size()];
                    BufferedWriter bw = new BufferedWriter(new FileWriter(pth));
                    for (String aCl : cl.toArray(out)) {
                        bw.write(aCl);
                        bw.newLine();
                    }
                    bw.close();
                } catch (IOException x) {
                    skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName(), x);
                } catch (Exception x) {
                    skUtilities.prSysE(x.getMessage(), getClass().getSimpleName());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        line = (Expression<Number>) e[i];
        path = (Expression<String>) e[1 - i];
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
