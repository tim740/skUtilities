package uk.tim740.skUtilities.files;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.files.event.EvtFileWipe;
import uk.tim740.skUtilities.skUtilities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Created by tim740 on 20/03/2016
 */
public class SExprFileContents extends SimpleExpression<String> {
  private Expression<String> path;

  @Override
  @Nullable
  protected String[] get(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    try {
      ArrayList<String> cl = new ArrayList<>();
      cl.addAll(Files.readAllLines(pth, Charset.defaultCharset()));
      return cl.toArray(new String[cl.size()]);
    } catch (NoSuchFileException x) {
      skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
    } catch (Exception x) {
      skUtilities.prSysE("File: '" + pth + "' " + x.getMessage(), getClass().getSimpleName(), x);
    }
    return null;
  }

  public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
    if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
      Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
      try {
        if (mode == Changer.ChangeMode.SET) {
          Files.write(pth, "".getBytes());
          for (String aCl : (String[]) delta) {
            Files.write(pth, (aCl + "\n").getBytes(), StandardOpenOption.APPEND);
          }
        } else {
          EvtFileWipe efw = new EvtFileWipe(pth);
          Bukkit.getServer().getPluginManager().callEvent(efw);
          if (!efw.isCancelled()) {
            Files.write(pth, "".getBytes());
          }
        }
      } catch (NoSuchFileException x) {
        skUtilities.prSysE("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
      } catch (AccessDeniedException x) {
        skUtilities.prSysE("File: '" + pth + "' is read only!", getClass().getSimpleName(), x);
      } catch (IndexOutOfBoundsException x) {
        try {
          skUtilities.prSysE("File: '" + pth + "' only contain's" + Files.lines(pth, Charset.defaultCharset()).count() + "lines!", getClass().getSimpleName(), x);
        } catch (IOException x1) {
          skUtilities.prSysE("File: '" + pth + "' " + x.getMessage(), getClass().getSimpleName(), x1);
        }
      } catch (Exception x) {
        skUtilities.prSysE("File: '" + pth + "' " + x.getMessage(), getClass().getSimpleName(), x);
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    path = (Expression<String>) e[0];
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
    if (mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.SET) {
      return CollectionUtils.array(String[].class);
    }
    return null;
  }

  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public boolean isSingle() {
    return false;
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
