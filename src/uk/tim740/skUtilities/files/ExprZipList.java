package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by tim740 on 18/03/2016
 */
public class ExprZipList extends SimpleExpression<String>{
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        File pth = new File("plugins\\" + path.getSingle(arg0).replaceAll("/", "\\"));
        String out = "";
        ZipEntry zEn;
        try {
            ZipInputStream zIs = new ZipInputStream(new BufferedInputStream(new FileInputStream(pth)));
            while ((zEn = zIs.getNextEntry()) != null) {
                if (Objects.equals(out, "")) {
                    out = zEn.getName();
                } else {
                    out = (out + "," + zEn.getName());
                }
            }
            zIs.close();
            return new String[]{out};
        } catch (FileNotFoundException e) {
            skUtilities.prEW("ZipFile: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            return null;
        } catch (IOException e) {
            skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
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
