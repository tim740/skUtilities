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
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by tim740 on 18/03/2016
 */
public class ExprZipList extends SimpleExpression<String>{
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
        ArrayList<String> cl = new ArrayList<>();
        try (ZipFile zf = new ZipFile(pth)) {
            cl.addAll(zf.stream().collect(Collectors.toList()).stream().map((Function<ZipEntry, String>) ZipEntry::toString).collect(Collectors.toList()));
            return cl.toArray(new String[cl.size()]);
        } catch (IOException e) {
            skUtilities.prSysE("ZipFile: '" + pth + "' doesn't exist, or doesn't have write permission!", getClass().getSimpleName(), e);
        }
        return null;
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
