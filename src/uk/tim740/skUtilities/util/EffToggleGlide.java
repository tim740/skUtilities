package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 13/03/2016
 */
public class EffToggleGlide extends Effect {
    private Expression<Boolean> boo;
    private Expression<LivingEntity> entity;

    @Override
    protected void execute(Event arg0) {
        entity.getSingle(arg0).setGliding(boo.getSingle(arg0));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        entity = (Expression<LivingEntity>) arg0[0];
        boo = (Expression<Boolean>) arg0[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
