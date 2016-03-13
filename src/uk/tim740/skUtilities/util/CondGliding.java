package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 13/03/2016
 */
public class CondGliding extends Condition {
    private Expression<LivingEntity> ent;

    @Override
    public boolean check(Event arg0) {
        return ent.getSingle(arg0).isGliding();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        ent = (Expression<LivingEntity>) arg0[0];
        return true;
    }
    @Override
    public String toString(Event arg0, boolean arg1) {
        return getClass().getName();
    }


}
