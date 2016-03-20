package uk.tim740.skUtilities.files;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by tim740 on 20/03/2016
 */
public class SExprFileAttribute extends SimpleExpression<Boolean>{
	private Expression<String> path;
    private int type;

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
        File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
        if (pth.exists()) {
            if (type == 0) {
                return new Boolean[]{pth.canRead()};
            } else if (type == 1) {
                return new Boolean[]{pth.canWrite()};
            } else {
                return new Boolean[]{pth.isHidden()};
            }
        }else{
            skUtilities.prEW("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            return null;
        }
    }
    public void change(Event arg0, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
            Boolean boo = (boolean) delta[0];
            if (pth.exists()) {
                if (type == 0){
                    pth.setReadable(boo);
                }else if (type == 1){
                    pth.setWritable(boo);
                }else{
                    try {
                        Files.setAttribute(Paths.get(pth.toString()), "dos:hidden", boo);
                    } catch (IOException e) {
                        skUtilities.prEW("Sorry Windows only!", getClass().getSimpleName(), 0);
                    }
                }
            } else {
                skUtilities.prEW("'" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            }
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        type = arg3.mark;
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
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
