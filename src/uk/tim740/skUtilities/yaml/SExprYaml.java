package uk.tim740.skUtilities.yaml;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tim740 on 27/11/2016
 */
public class SExprYaml extends SimpleExpression<Object> {
  private Expression<String> ypath, path;
  private int ty;

  @Override
  @Nullable
  protected Object[] get(Event e) {
    File pth = new File(skUtilities.getDefaultPath(path.getSingle(e)));
    String ypth = ypath.getSingle(e);
    FileConfiguration con = YamlConfiguration.loadConfiguration(pth);
    if (con.contains(ypth)) {
      switch (ty) {
        case 0: {
          return CollectionUtils.array(con.get(ypth));
        } case 1: {
          Set<String> n = con.getConfigurationSection(ypth).getKeys(false);
          return n.toArray(new String[n.size()]);
        } case 2: {
          Set<String> n = con.getConfigurationSection(ypth).getKeys(true);
          return n.toArray(new String[n.size()]);
        } case 3: {
          return con.getList(ypth).toArray();
        }
      }
    } else {
      skUtilities.prSysE("Yaml Path: '" + ypth + "' doesn't exist in file '" + pth + "'", getClass().getSimpleName());
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
    File pth = new File(skUtilities.getDefaultPath(path.getSingle(e)));
    String ypth = ypath.getSingle(e);
    FileConfiguration con = YamlConfiguration.loadConfiguration(pth);
    try {
      if (mode == Changer.ChangeMode.DELETE) {
        con.set(ypth, null);
      } else {
        Object v = (delta[0] == null ? "" : delta[0]);
        switch (ty) {
          case 0: {
            if (mode == Changer.ChangeMode.SET) {
              con.set(ypth, delta[0]);
            }
            break;
          }
          case 1:
          case 2:
            if (mode == Changer.ChangeMode.ADD) {
              con.createSection(ypth);
            } else if (mode == Changer.ChangeMode.REMOVE) {
              con.set(ypth + "." + v, null);
            }
            break;
          case 3: {
            if (mode == Changer.ChangeMode.ADD) {
              List obj = con.getList(ypth);
              if (obj == null) {
                obj = new ArrayList<>();
                obj.add(delta[0]);
                con.set(ypth, obj);
              } else {
                obj.add(delta[0]);
              }
            } else if (mode == Changer.ChangeMode.REMOVE) {
              con.getList(ypth).remove(delta[0]);
            }
          }
        }
      }
    } finally {
      try {
        con.save(pth);
      } catch (IOException x) {
        skUtilities.prSysE("Failed to save: '" + pth + "'", getClass().getSimpleName(), x);
      }
    }
  }


  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, ParseResult p) {
    ypath = (Expression<String>) e[0];
    path = (Expression<String>) e[1];
    ty = p.mark;
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
    if (mode == Changer.ChangeMode.DELETE) {
      return CollectionUtils.array(Object.class);
    }
    switch (ty) {
      case 0: {
        if (mode == Changer.ChangeMode.SET) {
          return CollectionUtils.array(Object.class);
        }
        break;
      }
      case 1:
      case 2:
      case 3:
        if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
          return CollectionUtils.array(Object.class);
        }
        break;
    }
    return null;
  }

  @Override
  public Class<?> getReturnType() {
    return Object.class;
  }

  @Override
  public boolean isSingle() {
    return (ty == 0);
  }

  @Override
  public String toString(@Nullable Event e, boolean b) {
    return getClass().getName();
  }
}
