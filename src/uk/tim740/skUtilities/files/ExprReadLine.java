package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by tim740 on 18/03/2016
 */
public class ExprReadLine extends SimpleExpression<String>{
    private Expression<Number> line;
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
        if (pth.exists()){
            try (Stream<String> lines = Files.lines(Paths.get(pth.toString()))) {
                //noinspection OptionalGetWithoutIsPresent
                return new String[]{lines.skip(Integer.parseInt(line.getSingle(arg0).toString()) -1).findFirst().get()};
            }catch (IOException e) {
                skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
                return null;
            }
        }else{
            skUtilities.prEW("'" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            return null;
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        line = (Expression<Number>) arg0[0];
        path = (Expression<String>) arg0[1];
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
