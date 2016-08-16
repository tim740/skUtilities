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
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipalLookupService;

/**
 * Created by tim740 on 15/08/2016
 */
public class SExprFileOwner extends SimpleExpression<String>{
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        Path pth = Paths.get(Utils.getDefaultPath(path.getSingle(arg0)));
        if (Files.exists(pth)) {
            try {
                return new String[]{Files.getOwner(pth).getName()};
            } catch (IOException e) {
                skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
            }
        }else{
            skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName());
        }
        return null;
    }
    public void change(Event arg0, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            Path pth = Paths.get(Utils.getDefaultPath(path.getSingle(arg0)));
            if (Files.exists(pth)) {
                try {
                    String str = (String) delta[0];
                    UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
                    Files.setOwner(pth, lookupService.lookupPrincipalByName(str));
                } catch (IOException e) {
                    skUtilities.prSysE(e.getMessage(), getClass().getSimpleName(), e);
                }
            } else {
                skUtilities.prSysE("'" + pth + "' doesn't exist!", getClass().getSimpleName());
            }
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
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
