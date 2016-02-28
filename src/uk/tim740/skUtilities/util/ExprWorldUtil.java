package uk.tim740.skUtilities.util;

import javax.annotation.Nullable;
import org.bukkit.World;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740 on 28/02/2016
 */
public class ExprWorldUtil extends SimpleExpression<String> {
    private Expression<World> world;
    private int Wty;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        if (Wty == 0){
            return new String[]{this.world.getSingle(arg0).getEnvironment().toString()};
        }else {
            return new String[]{this.world.getSingle(arg0).getWorldType().toString()};
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        this.world = (Expression<World>) arg0[0];
        Wty = arg3.mark;
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
