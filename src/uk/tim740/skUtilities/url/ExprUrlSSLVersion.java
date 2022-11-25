package uk.tim740.skUtilities.url;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

;

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
      c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      c.connect();
      for (Certificate cert : c.getServerCertificates()) {
        if (cert instanceof X509Certificate) {
          c.disconnect();
          X509Certificate sc = (X509Certificate) cert;
          return new Number[]{sc.getVersion()};
        }
      }
    } catch (Exception x) {
      skUtilities.prSysE("Error Reading from: '" + url.getSingle(e) + "' Is the site down?", getClass().getSimpleName(), x);
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
