package uk.tim740.skUtilities.util;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Created by tim740 on 04/04/2016
 */
public class SExprWhitelist extends SimpleExpression<OfflinePlayer> {

	@Override
	@Nullable
	protected OfflinePlayer[] get(Event arg0) {
        ArrayList<OfflinePlayer> cl = new ArrayList<>();
        cl.addAll(Bukkit.getWhitelistedPlayers());
        return cl.toArray(new OfflinePlayer[cl.size()]);
	}
    public void change(Event arg0, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD) {
            ((OfflinePlayer) delta[0]).setWhitelisted(true);
        }else if (mode == Changer.ChangeMode.REMOVE) {
            ((OfflinePlayer) delta[0]).setWhitelisted(false);
        }else if (mode == Changer.ChangeMode.RESET) {
            for (OfflinePlayer sof : Bukkit.getWhitelistedPlayers()) {
                sof.setWhitelisted(false);
            }
        }else if (mode == Changer.ChangeMode.SET) {
            Bukkit.setWhitelist((Boolean) delta[0]);
        }
    }

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(OfflinePlayer.class);
        }else if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
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
