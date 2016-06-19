package uk.tim740.skUtilities.util;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.*;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 16/06/16
 * Code by dzikoysk
 */
public class EffRunOpCmd extends Effect{
    private Expression<String> str;
    private Expression<Player> usr;


    @Override
	protected void execute(Event arg0) {
        for (String command : this.str.getArray(arg0)) {
            if (command.startsWith("/")) command = command.substring(1);
            if (this.usr == null) {
                Skript.dispatchCommand(Bukkit.getConsoleSender(), command);
            }else {
                for (CommandSender sender : this.usr.getArray(arg0)) {
                    if (!sender.isOp()) {
                        sender.setOp(true);
                        Skript.dispatchCommand(sender, command);
                        sender.setOp(false);
                    } else {
                        Skript.dispatchCommand(sender, command);
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