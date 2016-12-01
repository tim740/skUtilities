package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import uk.tim740.skUtilities.files.*;
import uk.tim740.skUtilities.files.event.*;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 20/03/2016
 */
class RegFiles {
    static void reg() {
        Skript.registerExpression(ExprZipList.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]files in zip[ file] %string%", "[skutil[ities] ]zip[ file] %string%'s files");
        Skript.registerExpression(ExprDirList.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]files in dir[ectory](0¦|1¦ including sub dir[ectorie]s) %string%", "[skutil[ities] ]dir[ectory](0¦|1¦ including sub dir[ectorie]s) %string%'s files");
        Skript.registerExpression(ExprFileLines.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]line count of file %string%", "[skutil[ities] ]file %string%'s line count");
        Skript.registerExpression(ExprFileTimeAttributes.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]file %string%'s (0¦last modified|1¦creation|2¦last access) value", "[skutil[ities] ](0¦last modified|1¦creation|2¦last access) value of file %string%");
        Skript.registerExpression(ExprFileDirSize.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]size of (0¦file|1¦dir[ectory]) %string%", "[skutil[ities] ](0¦file|1¦dir[ectory]) %string%'s size");
        Skript.registerExpression(ExprFileDirSizeBytes.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]size of (0¦file|1¦dir[ectory]) %string% in bytes", "[skutil[ities] ](0¦file|1¦dir[ectory]) %string%'s size in bytes");
        Skript.registerExpression(ExprFileNameExt.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦name|1¦extension) of file %string%", "[skutil[ities] ]file %string%'s (0¦name|1¦extension)");
        Skript.registerExpression(ExprAbsolutePath.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](absolute|complete) path of %string%", "[skutil[ities] ]%string%'s (absolute|complete) path");

        Skript.registerExpression(ExprDiskSpace.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]disk's (0¦total|1¦free|2¦usable) space", "[skutil[ities] ](0¦total|1¦free|2¦usable) space on disk");

        Skript.registerExpression(SExprFileOwner.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]owner of file %string%", "[skutil[ities] ]file %string%'s owner");
        Skript.registerExpression(SExprFileAttribute.class, Boolean.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦readable|1¦writable|2¦hidden) attribute of file %string%", "[skutil[ities] ]file %string%'s (0¦readable|1¦writable|2¦hidden) attribute");
        Skript.registerExpression(SExprFileContents.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]file contents of %string%", "[skutil[ities] ]%string%'s file contents");
        Skript.registerExpression(SExprEditLine.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]line %number% in file %string%", "[skutil[ities] ]file %string%'s line %number%");

        if (Bukkit.getPluginManager().getPlugin("skUtilities").getConfig().getBoolean("loadYaml", true)) {
            Skript.registerExpression(SExprYaml.class, Object.class, ExpressionType.PROPERTY, "[skutil[ities] ]y[a]ml (0¦value|1¦nodes|2¦node[s with] keys|3¦list) %string% (from|of) file %-string%");
        }

        Skript.registerEffect(EffRunApp.class, "[skutil[ities] ]run (script|program|app[lication]|file) at %string%");
        Skript.registerEffect(EffCreateFile.class, "[skutil[ities] ]create (0¦(script|program|app[lication]|[zip ]file)|1¦dir[ectory]) %string%");
        Skript.registerEffect(EffDeleteFile.class, "[skutil[ities] ]delete (0¦(script|program|app[lication]|[zip ]file)|1¦dir[ectory]) %string%");
        Skript.registerEffect(EffFileRenameMove.class, "[skutil[ities] ](0¦rename (file|dir[ectory])|1¦move file|2¦copy file|3¦move dir[ectory]|4¦copy dir[ectory]) %string% to %-string%");
        Skript.registerEffect(EffFileDownload.class, "[skutil[ities] ]download file from %string% to file %-string%");
        Skript.registerEffect(EffZipFiles.class, "[skutil[ities] ]zip file[s] %strings% to zip[ file] %-string%");
        Skript.registerEffect(EffZipDirectory.class, "[skutil[ities] ]zip dir[ectory] %string% to zip[ file] %-string%");
        Skript.registerEffect(EffUnzip.class, "[skutil[ities] ](unzip|extract) %string% to dir[ectory] %-string%");
        Skript.registerEffect(EffInsertLine.class, "[skutil[ities] ]write %string% at line %numbers% to file %-string%");

        Skript.registerCondition(CondFileExists.class, "[skutil[ities] ](script|program|app[lication]|file|dir[ectory]) %string% exists", "[skutil[ities] ](script|program|app[lication]|file) %string% does(n't| not) exist");
        Skript.registerCondition(CondIsFile.class, "[skutil[ities] ]file %string% is a file", "[skutil[ities] ]file %string% is(n'| no)t a file");
        Skript.registerCondition(CondIsSymbolic.class, "[skutil[ities] ]file %string% is (symbolic|shortcut)", "[skutil[ities] ]file %string% is(n'| no)t (symbolic|shortcut)");
        Skript.registerCondition(CondIsExecutable.class, "[skutil[ities] ]file %string% is(n't| not) exec[utable]", "[skutil[ities] ]file %string% is exec[utable]");

        regFE();
    }

    private static void regFE() {
        Skript.registerExpression(ExprFile.class, File.class, ExpressionType.SIMPLE, "[event-]file");

        Skript.registerEvent("DownloadFile", SimpleEvent.class, EvtFileDownload.class, "file download");
        EventValues.registerEventValue(EvtFileDownload.class, String.class, new Getter<String, EvtFileDownload>() {
            @Nullable
            @Override
            public String get(EvtFileDownload e) {
                return e.getUrl();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileDownload.class, File.class, new Getter<File, EvtFileDownload>() {
            @Nullable
            @Override
            public File get(EvtFileDownload e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("RunApp", SimpleEvent.class, EvtRunApp.class, "(file|app|script) (run|execute)");
        EventValues.registerEventValue(EvtRunApp.class, File.class, new Getter<File, EvtRunApp>() {
            @Nullable
            @Override
            public File get(EvtRunApp e) {
                return e.getApp();
            }
        }, 0);

        Skript.registerEvent("FileCreation", SimpleEvent.class, EvtFileCreation.class, "file creat(ion|e)");
        EventValues.registerEventValue(EvtFileCreation.class, File.class, new Getter<File, EvtFileCreation>() {
            @Nullable
            @Override
            public File get(EvtFileCreation e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileDeletion", SimpleEvent.class, EvtFileDeletion.class, "file delet(ion|e)");
        EventValues.registerEventValue(EvtFileDeletion.class, File.class, new Getter<File, EvtFileDeletion>() {
            @Nullable
            @Override
            public File get(EvtFileDeletion e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileWipe", SimpleEvent.class, EvtFileWipe.class, "file (wipe|reset|clear)");
        EventValues.registerEventValue(EvtFileWipe.class, File.class, new Getter<File, EvtFileWipe>() {
            @Nullable
            @Override
            public File get(EvtFileWipe e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileMove", SimpleEvent.class, EvtFileMove.class, "file move");
        EventValues.registerEventValue(EvtFileMove.class, File.class, new Getter<File, EvtFileMove>() {
            @Nullable
            @Override
            public File get(EvtFileMove e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileMove.class, String.class, new Getter<String, EvtFileMove>() {
            @Nullable
            @Override
            public String get(EvtFileMove e) {
                return e.getEvtMFile();
            }
        }, 0);

        Skript.registerEvent("FileCopy", SimpleEvent.class, EvtFileCopy.class, "file copy");
        EventValues.registerEventValue(EvtFileCopy.class, File.class, new Getter<File, EvtFileCopy>() {
            @Nullable
            @Override
            public File get(EvtFileCopy e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileCopy.class, String.class, new Getter<String, EvtFileCopy>() {
            @Nullable
            @Override
            public String get(EvtFileCopy e) {
                return e.getEvtMFile();
            }
        }, 0);

        Skript.registerEvent("FileRename", SimpleEvent.class, EvtFileRename.class, "file rename");
        EventValues.registerEventValue(EvtFileRename.class, File.class, new Getter<File, EvtFileRename>() {
            @Nullable
            @Override
            public File get(EvtFileRename e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileRename.class, String.class, new Getter<String, EvtFileRename>() {
            @Nullable
            @Override
            public String get(EvtFileRename e) {
                return e.getEvtFileName();
            }
        }, 0);

        Skript.registerEvent("FileWrite", SimpleEvent.class, EvtFileWrite.class, "file write");
        EventValues.registerEventValue(EvtFileWrite.class, File.class, new Getter<File, EvtFileWrite>() {
            @Nullable
            @Override
            public File get(EvtFileWrite e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileWrite.class, String.class, new Getter<String, EvtFileWrite>() {
            @Nullable
            @Override
            public String get(EvtFileWrite e) {
                return e.getEvtFileTxt();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileWrite.class, Number.class, new Getter<Number, EvtFileWrite>() {
            @Nullable
            @Override
            public Number get(EvtFileWrite e) {
                return e.getEvtFileLine();
            }
        }, 0);

        Skript.registerEvent("Unzip", SimpleEvent.class, EvtUnzip.class, "unzip");
        EventValues.registerEventValue(EvtUnzip.class, File.class, new Getter<File, EvtUnzip>() {
            @Nullable
            @Override
            public File get(EvtUnzip e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtUnzip.class, String.class, new Getter<String, EvtUnzip>() {
            @Nullable
            @Override
            public String get(EvtUnzip e) {
                return e.getEvtNloc();
            }
        }, 0);

        Skript.registerEvent("FileZip", SimpleEvent.class, EvtUnzip.class, "file zip");
        EventValues.registerEventValue(EvtFileZip.class, File.class, new Getter<File, EvtFileZip>() {
            @Nullable
            @Override
            public File get(EvtFileZip e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileZip.class, String.class, new Getter<String, EvtFileZip>() {
            @Nullable
            @Override
            public String get(EvtFileZip e) {
                return e.getEvtZipFile();
            }
        }, 0);
    }
}
