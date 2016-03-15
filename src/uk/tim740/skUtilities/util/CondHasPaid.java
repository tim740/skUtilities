package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by tim740 on 13/03/2016
 */
public class CondHasPaid extends Condition {
    private Expression<Player> user;

    @Override
    public boolean check(Event arg0) {
        Boolean v = false;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://minecraft.net/haspaid.jsp?user=" + user.getSingle(arg0).getName()).openStream()));
            v = Boolean.valueOf(in.readLine());
            in.close();
        } catch (Exception e) {
            skUtilities.prErr(e.getCause().getMessage(), getClass().getSimpleName(), 1);
        }
        return v;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        user = (Expression<Player>) arg0[0];
        return true;
    }
    @Override
    public String toString(Event arg0, boolean arg1) {
        return getClass().getName();
    }


}
