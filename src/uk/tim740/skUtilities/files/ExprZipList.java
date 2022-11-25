package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by tim740 on 18/03/2016
 */
public class ExprZipList extends SimpleExpression<String> {
  private Expression<String> path;

  @Override
  @Nullable
  protected String[] get(Event e) {
    File pth = new File(skUtilities.getDefaultPath(path.getSingle(e)));
    ArrayList<String> cl = new ArrayList<>();
    try (ZipFile zf = new ZipFile(pth)) {
      cl.addAll(zf.stream().collect(Collectors.toList()).stream().map((Function<ZipEntry, String>) ZipEntry::toString).collect(Collectors.toList()));
      return cl.toArray(new String[cl.size()]);
    } catch (Exception x) {
      skUtilities.prSysE("ZipFile: '" + pth + "' doesn't exist, or doesn't have write permission!", getClass().getSimpleName(), x);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    path = (Expression<String>) e[0];
    return true;
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
