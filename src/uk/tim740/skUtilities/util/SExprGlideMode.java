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
	protected Boolean[] get(Event e) {
		return new Boolean[]{ent.getSingle(e).isGliding()};
	}
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            ent.getSingle(e).setGliding((boolean) delta[0]);
        }else if (mode == Changer.ChangeMode.RESET) {
            ent.getSingle(e).setGliding(false);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        ent = (Expression<LivingEntity>) e[0];
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
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
