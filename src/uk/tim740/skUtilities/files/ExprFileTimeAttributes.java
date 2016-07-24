package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;

/**
 * Created by tim740 on 21/03/2016
 */
public class ExprFileTimeAttributes extends SimpleExpression<Number>{
	private Expression<String> path;
    private int ty;

	@Override
	@Nullable
	protected Number[] get(Event arg0) {
        Path pth = Paths.get(Utils.getDefaultPath(path.getSingle(arg0)));
        try {
            if (ty == 0) {
                return new Number[]{Files.readAttributes(pth, BasicFileAttributes.class).lastModifiedTime().to(TimeUnit.SECONDS)};
            }else if (ty == 1){
                return new Number[]{Files.readAttributes(pth, BasicFileAttributes.class).creationTime().to(TimeUnit.SECONDS)};
            }else{
                return new Number[]{Files.readAttributes(pth, BasicFileAttributes.class).lastAccessTime().to(TimeUnit.SECONDS)};
            }
        } catch (IOException e) {
            skUtilities.prSysE("File: '" + pth + "' doesn't exist, or doesn't have write permission!", getClass().getSimpleName(), e);
        }
        return null;
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        ty = arg3.mark;
        return true;
    }
    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
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
