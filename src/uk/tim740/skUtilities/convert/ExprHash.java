package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by tim740 on 14/03/2016
 */
public class ExprHash extends SimpleExpression<String> {
    private Expression<String> str, hash;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        MessageDigest hashStr;
        try {
            hashStr = MessageDigest.getInstance(hash.getSingle(arg0).toUpperCase());
            hashStr.update(str.getSingle(arg0).getBytes(), 0, str.getSingle(arg0).length());
            return new String[]{new BigInteger(1, hashStr.digest()).toString(16)};
        } catch (Exception e) {
            skUtilities.prErr(e.getMessage(), getClass().getSimpleName(), 1);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        hash = (Expression<String>) arg0[1];
        return true;
    }
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
