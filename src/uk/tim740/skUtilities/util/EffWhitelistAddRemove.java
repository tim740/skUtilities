package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.*;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 27/03/16
 */
public class EffWhitelistAddRemove extends Effect{
    private Expression<Player> usr;
    private int ty;


    @Override
	protected void execute(Event arg0) {
        if (ty == 0){
            usr.getSingle(arg0).setWhitelisted(true);
        }else{
            usr.getSingle(arg0).setWhitelisted(false);
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        ty = arg3.mark;
        usr = (Expression<Player>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}