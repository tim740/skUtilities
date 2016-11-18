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
        Skript.registerExpression(ExprGetTimeZone.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]time[ ]zone of server", "[skutil[ities] ]server's time[ ]zone");
        Skript.registerExpression(ExprTimeZoneList.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ][all ]time[ ]zones");
        Skript.registerExpression(ExprTimeInTimeZone.class, Date.class, ExpressionType.PROPERTY, "[skutil[ities] ][current ]time in time[ ]zone %string%", "[skutil[ities] ][current ]time[ ]zone %string%'s time");
        Skript.registerExpression(ExprGetRegion.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]region of server", "[skutil[ities] ]server's region");
        Skript.registerCondition(CondisTimeZone.class, "[skutil[ities] ]server is time[ ]zone %string%", "[skutil[ities] ]server is(n'| no)t time[ ]zone %string%");

        Skript.registerExpression(ExprGetJsonID.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]content of json value %string% from text %-string%", "[skutil[ities] ]value %string%'s json contents from text %-string%");
        Skript.registerExpression(ExprGetJsonIDS.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]content of json value's %strings% from text %-string%", "[skutil[ities] ]value's %strings%'s json contents from text %-string%");

        Skript.registerExpression(ExprLoaded.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]number of[ loaded] (0¦(commands|cmds)|1¦functions|2¦s(c|k)ripts|3¦triggers|4¦statements|5¦variables|6¦aliases|7¦events|8¦effects|9¦expressions|10¦conditions)");
        Skript.registerExpression(ExprLoadedList.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦plugins|1¦addons) list", "[skutil[ities] ]list of (0¦plugins|1¦addons)");
        Skript.registerExpression(ExprVersion.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]%string%'s version", "[skutil[ities] ]version of %string%");
        Skript.registerExpression(ExprSysTime.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ][current ]system (0¦nanos[econds]|1¦millis[econds]|2¦seconds)");
        Skript.registerExpression(ExprRam.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦free|1¦total|2¦max) (ram|memory)");
        Skript.registerExpression(ExprCaseLength.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]number of (0¦upper|1¦lower)case char[acter]s in %string%");

        Skript.registerEffect(EffPrintTag.class, "[skutil[ities] ]print (0¦info|1¦warning|2¦error) %string% to console");
        Skript.registerEffect(EffRunOpCmd.class, "[skutil[ities] ](force|make) %player% run (cmd|command) %string% as op");
        Skript.registerEffect(EffSkReloadAliases.class, "[skutil[ities] ]skript reload aliases");
        Skript.registerEffect(EffReloadSkript.class, "[skutil[ities] ]reload s(k|c)ript %string%");
        Skript.registerEffect(EffRestartServer.class, "[skutil[ities] ]re(0¦start|1¦load) server");

        Skript.registerCondition(CondStartsEndsWith.class, "[skutil[ities] ]%string% (0¦starts|1¦ends) with %-string%", "[skutil[ities] ]%string% does(n't| not) (0¦start|1¦end) with %-string%");
    }
}
