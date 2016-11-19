package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.apache.commons.io.FileUtils;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 17/03/2016
 */
public class ExprFileDirSizeBytes extends SimpleExpression<Number> {
    private Expression<String> path;
    private int ty;

    @Override
    @Nullable
    protected Number[] get(Event e) {
        File pth = new File(skUtilities.getDefaultPath(path.getSingle(e)));
        try {
            if (ty == 0) {
                return new Number[]{pth.length()};
            } else {
                return new Number[]{FileUtils.sizeOfDirectory(pth)};
            }
        } catch (Exception x) {
            skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        path = (Expression<String>) e[0];
        ty = p.mark;
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
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
