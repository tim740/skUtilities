package uk.tim740.skUtilities.util;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 13/03/2016
 */
public class SExprGlideMode extends SimpleExpression<Boolean>{
	private Expression<LivingEntity> ent;

	@Override
	@Nullable
	protected Boolean[] get(Event arg0) {
		return new Boolean[]{ent.getSingle(arg0).isGliding()};
	}
    public void change(Event arg0, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            ent.getSingle(arg0).setGliding((boolean) delta[0]);
        }else if (mode == Changer.ChangeMode.RESET) {
            ent.getSingle(arg0).setGliding(false);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        ent = (Expression<LivingEntity>) arg0[0];
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
