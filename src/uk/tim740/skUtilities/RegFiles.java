package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.tim740.skUtilities.files.*;

/**
 * Created by tim740 on 20/03/2016
 */
class RegFiles {
    static void regF() {
        Skript.registerExpression(ExprFileLines.class,Number.class,ExpressionType.PROPERTY,"line count of (script|program|app[lication]|file) %string%", "(script|program|app[lication]|file) %string%'s line count");
        Skript.registerExpression(ExprFileSize.class,String.class,ExpressionType.PROPERTY,"file size of %string%", "%string%'s file size");
        Skript.registerExpression(ExprZipList.class,String.class,ExpressionType.PROPERTY,"files in zip[ file] %string%", "zip[ file] %string%'s files");
        Skript.registerExpression(ExprDirList.class,String.class,ExpressionType.PROPERTY,"files in dir[ectory] %string%", "dir[ectory] %string%'s files");
        Skript.registerExpression(ExprReadLine.class,String.class,ExpressionType.PROPERTY,"line %number% in file %string%", "file %string%'s line %number%");
        Skript.registerExpression(ExprFileContents.class,String.class,ExpressionType.PROPERTY,"file contents of %string%", "%string%'s file contents");
        Skript.registerExpression(ExprFileLastModified.class,Number.class,ExpressionType.PROPERTY,"file %string%'s last modified value", "last modified value of file %string%");

        Skript.registerExpression(SExprFileAttribute.class,Boolean.class,ExpressionType.PROPERTY,"(0¦readable|1¦writable|2¦hidden) attribute of file %string%", "file %string%'s (0¦readable|1¦writable|2¦hidden) attribute");

        Skript.registerEffect(EffRunApp.class, "run (script|program|app[lication]|file) at %string%");
        Skript.registerEffect(EffCreateDeleteFile.class, "(0¦create|1¦delete) (script|program|app[lication]|[zip ]file) %string%");
        Skript.registerEffect(EffWriteLine.class, "set line %number% in file %string% to %-string%");
        Skript.registerEffect(EffRenameFile.class, "rename file %string% to %-string%");
        Skript.registerEffect(EffMoveFile.class, "move file %string% to %-string%");
        Skript.registerEffect(EffWipeFile.class, "(clear|wipe) file %string%");

        Skript.registerEffect(EffZipAddFile.class, "add (script|program|app[lication]|file) %string% to zip[ file] %-string%");
        Skript.registerEffect(EffUnzip.class, "(unzip|extract) %string% to dir[ectory] %-string%");


        Skript.registerCondition(CondFileExists.class, "[(script|program|app[lication]|file) ]%string% exists");
    }
}
