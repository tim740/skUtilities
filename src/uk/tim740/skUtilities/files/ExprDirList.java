package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Created by tim740 on 18/03/2016
 */
public class ExprDirList extends SimpleExpression<String> {
  private Expression<String> path;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    ArrayList<String> cl = new ArrayList<>();
    try {
      if (ty == 0) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(pth)) {
          for (Path cf : stream) {
            cl.add(cf.toAbsolutePath().toString());
          }
        }
        return cl.toArray(new String[cl.size()]);
      } else {
        Files.walkFileTree(pth, new SimpleFileVisitor<Path>(){
          @Override
          public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (!attrs.isDirectory()) {
              cl.add(file.toAbsolutePath().toString());
            }
            return FileVisitResult.CONTINUE;
          }
        });
        return cl.toArray(new String[cl.size()]);
      }
    } catch (Exception x) {
      skUtilities.prSysE("Directory: '" + pth + "' doesn't exist!", getClass().getSimpleName(), x);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    path = (Expression<String>) e[0];
    ty = p.mark;
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
