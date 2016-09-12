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
import java.util.ArrayList;

/**
 * Created by tim740 on 18/03/2016
 */
public class ExprDirList extends SimpleExpression<String>{
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event e) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(e)));
        ArrayList<String> cl = new ArrayList<>();
        try {
            if (pth.isDirectory()) {
                //noinspection ConstantConditions
                for (File file : pth.listFiles()) {
                    cl.add(String.valueOf(pth + File.separator + file.getName()));
                }
                return cl.toArray(new String[cl.size()]);
            } else {
                skUtilities.prSysE("Directory: '" + pth + File.separator + "' isn't a valid directory!", getClass().getSimpleName());
            }
        }catch (Exception x){
            skUtilities.prSysE("Directory: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
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
