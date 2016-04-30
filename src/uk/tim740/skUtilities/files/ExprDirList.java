package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by tim740 on 18/03/2016
 */
public class ExprDirList extends SimpleExpression<String>{
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        String pth = (Utils.getDefaultPath() + path.getSingle(arg0));
        try {
            ArrayList<String> cl = new ArrayList<>();
            Files.walk(Paths.get(pth)).forEach(dfp -> {
                if (Files.isRegularFile(dfp)) {
                    cl.add(String.valueOf(dfp));
                }
            });
            return cl.toArray(new String[cl.size()]);
        } catch (FileNotFoundException e) {
            skUtilities.prSys("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            return null;
        } catch (IOException e) {
            skUtilities.prSys(e.getMessage(), getClass().getSimpleName(), 0);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        return true;
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
