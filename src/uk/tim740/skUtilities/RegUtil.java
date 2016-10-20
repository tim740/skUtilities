package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.util.Date;
import uk.tim740.skUtilities.util.*;

/**
 * Created by tim740 on 22/02/2016
 */
class RegUtil {
    static void reg() {
        Skript.registerExpression(ExprGetTimeZone.class, String.class, ExpressionType.PROPERTY, "time[ ]zone of server", "server's time[ ]zone");
        Skript.registerExpression(ExprTimeZoneList.class, String.class, ExpressionType.PROPERTY, "[all ]time[ ]zones");
        Skript.registerExpression(ExprTimeInTimeZone.class, Date.class, ExpressionType.PROPERTY, "[current ]time in time[ ]zone %string%", "[current ]time[ ]zone %string%'s time");
        Skript.registerExpression(ExprGetRegion.class, String.class, ExpressionType.PROPERTY, "region of server", "server's region");
        Skript.registerCondition(CondisTimeZone.class, "server is time[ ]zone %string%", "server is(n'| no)t time[ ]zone %string%");

        Skript.registerExpression(ExprGetJsonID.class, String.class, ExpressionType.PROPERTY, "content of json value %string% from text %-string%", "value %string%'s json contents from text %-string%");
        Skript.registerExpression(ExprGetJsonIDS.class, String.class, ExpressionType.PROPERTY, "content of json value's %strings% from text %-string%", "value's %strings%'s json contents from text %-string%");

        Skript.registerExpression(ExprLoaded.class, Number.class, ExpressionType.PROPERTY, "number of[ loaded] (0¦(commands|cmds)|1¦functions|2¦s(c|k)ripts|3¦triggers|4¦statements|5¦variables|6¦aliases|7¦events|8¦effects|9¦expressions|10¦conditions)");
        Skript.registerExpression(ExprLoadedList.class, String.class, ExpressionType.PROPERTY, "(0¦plugins|1¦addons) list", "list of (0¦plugins|1¦addons)");
        Skript.registerExpression(ExprVersion.class, String.class, ExpressionType.PROPERTY, "%string%'s version", "version of %string%");
        Skript.registerExpression(ExprSysTime.class, Number.class, ExpressionType.PROPERTY, "[current ]system (0¦nanos[econds]|1¦millis[econds]|2¦seconds)");
        Skript.registerExpression(ExprRam.class, Number.class, ExpressionType.PROPERTY, "[skutil ](0¦free|1¦total|2¦max) (ram|memory)");
        Skript.registerExpression(ExprCaseLength.class, Number.class, ExpressionType.PROPERTY, "number of (0¦upper|1¦lower)case char[acter]s in %string%");

        Skript.registerEffect(EffPrintTag.class, "print (0¦info|1¦warning|2¦error) %string% to console");
        Skript.registerEffect(EffRunOpCmd.class, "(force|make) %player% run (cmd|command) %string% as op");
        Skript.registerEffect(EffSkReloadAliases.class, "skript reload aliases");
        Skript.registerEffect(EffReloadSkript.class, "reload s(k|c)ript %string%");
        Skript.registerEffect(EffRestartServer.class, "re(0¦start|1¦load) server");

        Skript.registerCondition(CondStartsEndsWith.class, "%string% (0¦starts|1¦ends) with %-string%", "%string% does(n't| not) (0¦start|1¦end) with %-string%");
    }
}
