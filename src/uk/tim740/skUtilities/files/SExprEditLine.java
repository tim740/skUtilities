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
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by tim740 on 18/03/2016
 */
public class SExprEditLine extends SimpleExpression<String>{
    private Expression<Number> line;
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
        if (pth.exists()){
            try (Stream<String> lines = Files.lines(Paths.get(pth.toString()))) {
                //noinspection OptionalGetWithoutIsPresent
                return new String[]{lines.skip(Integer.parseInt(line.getSingle(arg0).toString()) -1).findFirst().get()};
            }catch (IOException e) {
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
                return null;
            }
        }else{
            skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName());
            return null;
        }
	}
    public void change(Event arg0, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
            if (pth.exists()) {
                EvtFileWrite efw = new EvtFileWrite(pth, (String) delta[0], line.getSingle(arg0));
                Bukkit.getServer().getPluginManager().callEvent(efw);
                if (!efw.isCancelled()) {
                    try {
                        ArrayList<String> cl = new ArrayList<>();
                        String inLi;
                        BufferedReader br = new BufferedReader(new FileReader(pth));
                        while ((inLi = br.readLine()) != null) {
                            cl.add(inLi);
                        }
                        br.close();
                        cl.set(Integer.parseInt(line.getSingle(arg0).toString()) - 1, (String) delta[0]);
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
            } else {
                skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName());
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        line = (Expression<Number>) arg0[0];
        path = (Expression<String>) arg0[1];
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
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
