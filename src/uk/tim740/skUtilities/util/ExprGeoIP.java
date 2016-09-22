package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tim740 on 11/09/16
 */
public class ExprGeoIP extends SimpleExpression<String> {
    private Expression<Player> u;
    private int ty;

    @Override
    @Nullable
    protected String[] get(Event e) {
        try {
            BufferedReader ur = new BufferedReader(new InputStreamReader(new URL("http://freegeoip.net/json/" + u.getSingle(e).getAddress().getAddress().getHostAddress()).openStream()));
            JSONObject j = (JSONObject) new JSONParser().parse(ur.readLine());
            ur.close();
            if (ty == 0) {
                return new String[]{j.get("country_code").toString()};
            } else if (ty == 1) {
                return new String[]{j.get("region_code").toString()};
            } else if (ty == 2) {
                return new String[]{j.get("country_name").toString()};
            } else if (ty == 3) {
                return new String[]{j.get("region_name").toString()};
            } else if (ty == 4) {
                return new String[]{j.get("city").toString()};
            } else {
                return new String[]{j.get("time_zone").toString()};
            }
        } catch (ParseException x) {
            skUtilities.prSysE("Error while parsing json!", getClass().getSimpleName(), x);
        } catch (MalformedURLException x) {
            skUtilities.prSysE("Error while checking GeoIp Stats!, is the site down?", getClass().getSimpleName(), x);
        } catch (IOException x) {
            skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        u = (Expression<Player>) e[0];
        ty = p.mark;
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
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}