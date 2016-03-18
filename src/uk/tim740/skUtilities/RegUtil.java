package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Bukkit;
import uk.tim740.skUtilities.util.*;
import uk.tim740.skUtilities.util.files.*;

/**
 * Created by tim740 on 22/02/2016
 */
public class RegUtil {
    public static void regU() {
        Skript.registerExpression(ExprLoaded.class,Number.class, ExpressionType.PROPERTY,"number of[ loaded] (0¦(commands|cmds)|1¦functions|2¦s(c|k)ripts|3¦triggers|4¦statements|5¦variables|6¦aliases|7¦plugins|8¦addons|9¦events|10¦effects|11¦expressions|12¦conditions)");
        Skript.registerExpression(ExprGenerateTxt.class,String.class,ExpressionType.PROPERTY,"generate[ random] string with length %integer%");
        Skript.registerExpression(ExprWorldUtil.class,String.class,ExpressionType.PROPERTY,"[world ](0¦(dimension|environment)|1¦type) of %world%", "%world%'s [world ](0¦(dimension|environment)|1¦type)");
        Skript.registerExpression(ExprVersion.class,String.class,ExpressionType.PROPERTY,"%string%'s version", "version of %string%");
        Skript.registerExpression(ExprSysTime.class,Number.class,ExpressionType.PROPERTY,"[current ]system (0¦nanos[econds]|1¦millis[econds]|2¦seconds)");

        Skript.registerExpression(ExprFileLines.class,Number.class,ExpressionType.PROPERTY,"line count of (script|program|app[lication]|file) %string%", "(script|program|app[lication]|file) %string%'s line count");
        Skript.registerExpression(ExprFileSize.class,String.class,ExpressionType.PROPERTY,"file size of %string%", "%string%'s file size");
        Skript.registerExpression(ExprZipList.class,String.class,ExpressionType.PROPERTY,"files in zip[ file] %string%", "zip[ file] %string%'s files");
        Skript.registerExpression(ExprDirList.class,String.class,ExpressionType.PROPERTY,"files in dir[ectory] %string%", "dir[ectory] %string%'s files");

        Skript.registerEffect(EffRunApp.class, "run (script|program|app[lication]|file) at %string%");
        Skript.registerEffect(EffZipAddFile.class, "(0¦add|1¦(remove|delete)) (script|program|app[lication]|file) %string% at zip[ file] %-string%");
        Skript.registerEffect(EffCreateDeleteFile.class, "(0¦create|1¦delete) (script|program|app[lication]|[zip ]file) %string%");
        Skript.registerEffect(EffZipAddFile.class, "add (script|program|app[lication]|file) %string% to zip[ file] %-string%");

        Skript.registerEffect(EffDemoMode.class, "send[ fake] trial packet to %player%");
        Skript.registerEffect(EffPrintTag.class, "print (0¦info|1¦warning|2¦error) %string% to console");
        Skript.registerEffect(EffVillagerProfession.class, "spawn a %entity% with profession (0¦farmer|1¦librarian|2¦priest|3¦blacksmith|4¦butcher) at %location%");

        Skript.registerCondition(CondFileExists.class, "[(script|program|app[lication]|file) ]%string% exists");
        if(Bukkit.getVersion().contains("(MC: 1.9)")) {
            Skript.registerEffect(EffToggleGlide.class, "set %entity%'s glide (state|ability|mode) to %boolean%");

            Skript.registerCondition(CondGliding.class, "%entity% is gliding");
        }else{
            skUtilities.loadErr("Failed to load: CondGliding & EffToggleGlide, due to being on 1.8!");
        }
    }
}
