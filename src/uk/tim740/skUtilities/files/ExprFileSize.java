package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 17/03/2016
 */
public class ExprFileSize extends SimpleExpression<String>{
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event e) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(e)));
        try {
            return new String[]{Utils.getFileSize(pth.length())};
        }catch(Exception x){
            skUtilities.prSysE("'" + pth + "' doesn't exist!", getClass().getSimpleName());
        }
        return null;
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        path = (Expression<String>) e[0];
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
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
