package uk.tim740.skUtilities.util;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 16/06/16
 * Code by @dzikoysk, optimized by @tim740
 */
public class EffRunOpCmd extends Effect{
    private Expression<String> str;
    private Expression<Player> usr;


    @Override
	protected void execute(Event arg0) {
        for (String cmd : str.getArray(arg0)) {
            if (cmd.startsWith("/")) cmd = cmd.substring(1);
            if (usr == null) {
                Skript.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            }else {
                for (CommandSender u : usr.getArray(arg0)) {
                    if (!u.isOp()) {
                        u.setOp(true);
                        Skript.dispatchCommand(u, cmd);
                        u.setOp(false);
                    } else {
                        Skript.dispatchCommand(u, cmd);
                    }
                }
            }
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        usr = (Expression<Player>) arg0[0];
        str = (Expression<String>) arg0[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}