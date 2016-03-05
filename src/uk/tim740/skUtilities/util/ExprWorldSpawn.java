package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 05/03/2016
 */
public class ExprWorldSpawn extends SimpleExpression<Location> {
    private Expression<World> world;

    @Override
    @Nullable
    protected Location[] get(Event arg0) {
        return new Location[]{world.getSingle(arg0).getSpawnLocation()};
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        world = (Expression<World>) arg0[0];
        return true;
    }
    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
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
