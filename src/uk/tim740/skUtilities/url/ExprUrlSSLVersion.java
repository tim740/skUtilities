package uk.tim740.skUtilities.url;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * Created by tim740 on 15/10/2016
 */
public class ExprUrlSSLVersion extends SimpleExpression<Number> {
    private Expression<String> url;

    @Override
    @Nullable
    protected Number[] get(Event e) {
        try {
            HttpsURLConnection c = (HttpsURLConnection) new URL(url.getSingle(e)).openConnection();
            c.connect();
            for (Certificate cert : c.getServerCertificates()) {
                if (cert instanceof X509Certificate) {
                    X509Certificate sc = (X509Certificate) cert;
                    c.disconnect();
                    return new Number[]{sc.getVersion()};
                }
            }
        } catch (IOException x) {
            skUtilities.prSysE("Error Reading from: '" + url.getSingle(e) + "' Is the site down?", getClass().getSimpleName(), x);
        } catch (Exception x) {
            skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
        url = (Expression<String>) e[0];
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
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
