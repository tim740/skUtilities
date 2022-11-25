package uk.tim740.skUtilities.files;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipalLookupService;

/**
 * Created by tim740 on 15/08/2016
 */
public class SExprFileOwner extends SimpleExpression<String> {
  private Expression<String> path;

  @Override
  @Nullable
  protected String[] get(Event e) {
    Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
    try {
      return new String[]{Files.getOwner(pth).getName()};
    } catch (Exception x) {
      skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName(), x);
    }
    return null;
  }

  public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
    if (mode == Changer.ChangeMode.SET) {
      Path pth = Paths.get(skUtilities.getDefaultPath(path.getSingle(e)));
      try {
        UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();
        Files.setOwner(pth, lookupService.lookupPrincipalByName((String) delta[0]));
      } catch (Exception x) {
        skUtilities.prSysE("File: '" + pth + "' doesn't exist, or is not readable!", getClass().getSimpleName(), x);
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
    if (mode == Changer.ChangeMode.SET) {
      return CollectionUtils.array(String.class);
    }
    return null;
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
