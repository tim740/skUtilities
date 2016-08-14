package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;

import java.util.Objects;

/**
 * Created by tim740 on 14/08/2016
 */
public class CondHasExtension extends Condition {
    private Expression<String> path, ext;

    @Override
    public boolean check(Event arg0) {
        Boolean pth = (Objects.equals(FilenameUtils.getExtension(Utils.getDefaultPath(path.getSingle(arg0))), ext.getSingle(arg0)));
        return (isNegated() ? !pth : pth);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        ext = (Expression<String>) arg0[1];
        setNegated(arg1 == 1);
        return true;
    }
    @Override
    public String toString(Event arg0, boolean arg1) {
        return getClass().getName();
    }


}
