package uk.tim740.skUtilities.convert;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprUnixToDate extends SimpleExpression <String>{
	private Expression<String> str, format;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        String si = Bukkit.getPluginManager().getPlugin("skUtilities").getConfig().getString("unixDateFormat");
        if (format != null){
            si = format.getSingle(arg0);
        }
        return new String[]{new SimpleDateFormat(si).format(new Date(Long.valueOf((str.getSingle(arg0) + "a").replace("000a", "").replace("a", "")) *1000L))};
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        format = (Expression<String>) arg0[1];
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
