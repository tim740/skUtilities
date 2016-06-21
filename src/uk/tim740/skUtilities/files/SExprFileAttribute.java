package uk.tim740.skUtilities.files;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.Utils;
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
    private int ty;

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
        File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
        if (pth.exists()) {
            if (ty == 0) {
                return new Boolean[]{pth.canRead()};
            } else if (ty == 1) {
                return new Boolean[]{pth.canWrite()};
            } else {
                return new Boolean[]{pth.isHidden()};
            }
        }else{
            skUtilities.prSys("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            return null;
        }
    }
    public void change(Event arg0, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
            File pth = new File(Utils.getDefaultPath(path.getSingle(arg0)));
            if (pth.exists()) {
                Boolean boo = (boolean) delta[0];
                if (ty == 0) {
                    if (mode == Changer.ChangeMode.SET) {
                        pth.setReadable(boo);
                    }else{
                        pth.setReadable(true);
                    }
                } else if (ty == 1) {
                    if (mode == Changer.ChangeMode.SET) {
                        pth.setWritable(boo);
                    }else{
                        pth.setWritable(true);
                    }
                } else {
                    try {
                        if (mode == Changer.ChangeMode.SET) {
                            Files.setAttribute(Paths.get(pth.toString()), "dos:hidden", boo);
                        }else{
                            Files.setAttribute(Paths.get(pth.toString()), "dos:hidden", false);
                        }
                    } catch (IOException e) {
                        skUtilities.prSys("Sorry Windows only!", getClass().getSimpleName(), 0);
                    }
                }
            } else {
                skUtilities.prSys("'" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            }
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        ty = arg3.mark;
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
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
