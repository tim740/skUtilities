package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

/**
 * Created by tim740 on 10/03/2016
 */
public class ExprVersion extends SimpleExpression<String> {
    private Expression<String> str;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        if (Objects.equals(str.getSingle(arg0).toLowerCase(), "aliases")) {
            try {
                return new String[]{"v" + new BufferedReader(new FileReader("plugins\\Skript\\aliases-english.sk")).readLine().replaceAll("#! VERSION: ", "").replaceAll("!", "")};
            } catch (Exception e) {
                skUtilities.prEW(e.getCause().getMessage(), getClass().getSimpleName(), 0);
                return null;
            }
        }else if (Objects.equals(str.getSingle(arg0).toLowerCase(), "java")) {
            return new String[]{System.getProperty("java.version")};
        }else {
            try {
                return new String[]{"v" + Bukkit.getServer().getPluginManager().getPlugin(str.getSingle(arg0)).getDescription().getVersion()};
            } catch (Exception e) {
                skUtilities.prEW("'" + str + "' isn't a real plugin!", getClass().getSimpleName(), 0);
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        str = (Expression<String>) arg0[0];
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
