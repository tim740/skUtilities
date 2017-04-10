package uk.tim740.skUtilities.util;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.apache.commons.lang3.SystemUtils;
import org.bukkit.event.Event;

/**
 * Created by tim740 on 10/04/2017
 */
public class CondisOS extends Condition {
  private int ty;

  @Override
  public boolean check(Event e) {
    Boolean chk = false;
    switch (ty) {
      case (0):
        chk = SystemUtils.IS_OS_WINDOWS;
        break;
      case (1):
        chk = SystemUtils.IS_OS_MAC;
        break;
      case (2):
        chk = SystemUtils.IS_OS_LINUX;
        break;
      case (3):
        chk = SystemUtils.IS_OS_UNIX;
        break;
      case (4):
        chk = SystemUtils.IS_OS_SOLARIS;
        break;
      case (5):
        chk = SystemUtils.IS_OS_SUN_OS;
        break;
      case (6):
        chk = SystemUtils.IS_OS_HP_UX;
        break;
      case (7):
        chk = SystemUtils.IS_OS_AIX;
        break;
      case (8):
        chk = SystemUtils.IS_OS_IRIX;
        break;
      case (9):
        chk = SystemUtils.IS_OS_FREE_BSD;
        break;
      case (10):
        chk = SystemUtils.IS_OS_OPEN_BSD;
        break;
      case (11):
        chk = SystemUtils.IS_OS_NET_BSD;
        break;
    }
    return (isNegated() ? !chk : chk);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    ty = p.mark;
    setNegated(i == 1);
    return true;
  }

  @Override
  public String toString(Event e, boolean b) {
    return getClass().getName();
  }
}
