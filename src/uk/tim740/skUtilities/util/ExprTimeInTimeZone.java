package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by tim740 on 11/09/2016
 */
public class ExprTimeInTimeZone extends SimpleExpression<String> {
  private Expression<String> str;

  @Override
  protected String[] get(Event e) {
    String s = str.getSingle(e);
    String[] sl = new String[0];
    try {
      BufferedReader br = new BufferedReader(new FileReader(new File("plugins/Skript/config.sk").getAbsoluteFile()));
      sl = br.lines().toArray(String[]::new);
      br.close();
    } catch (Exception x) {
      skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
    }
    String sf = "";
    for (String aSl : sl) {
      if (aSl.contains("date format: ")) {
        sf = aSl.replaceFirst("date format: ",  "");
      }
    }
    String ff;
    if (sf.equalsIgnoreCase("default")) {
      ff = new SimpleDateFormat().toPattern();
    } else {
      ff = sf;
    }
    return new String[]{ZonedDateTime.ofInstant(Instant.now(), ZoneId.of(s)).format(DateTimeFormatter.ofPattern(ff))};
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    str = (Expression<String>) e[0];
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
  public String toString(Event e, boolean b) {
    return getClass().getName();
  }
}
