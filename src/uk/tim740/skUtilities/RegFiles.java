package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import uk.tim740.skUtilities.files.*;

import javax.annotation.Nullable;
import java.io.File;

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
        Skript.registerExpression(ExprFileTimeAttributes.class,Number.class,ExpressionType.PROPERTY,"file %string%'s (0¦last modified|1¦creation|2¦last access) value", "(0¦last modified|1¦creation|2¦last access) value of file %string%");

        Skript.registerExpression(SExprFileAttribute.class,Boolean.class,ExpressionType.PROPERTY,"(0¦readable|1¦writable|2¦hidden) attribute of file %string%", "file %string%'s (0¦readable|1¦writable|2¦hidden) attribute");
        Skript.registerExpression(SExprFileContents.class,String.class,ExpressionType.PROPERTY,"file contents of %string%", "%string%'s file contents");

        Skript.registerEffect(EffRunApp.class, "run (script|program|app[lication]|file) at %string%");
        Skript.registerEffect(EffCreateDeleteFile.class, "(0¦create|1¦delete) (script|program|app[lication]|[zip ]file) %string%");
        Skript.registerEffect(EffWriteLine.class, "set line %number% in file %string% to %-string%");
        Skript.registerEffect(EffRenameFile.class, "rename file %string% to %-string%");
        Skript.registerEffect(EffMoveFile.class, "move file %string% to %-string%");

        Skript.registerEffect(EffDownloadFile.class, "download file from %string% to file %-string%");

        Skript.registerEffect(EffZipFile.class, "add (script|program|app[lication]|file) %string% to zip[ file] %-string%");
        Skript.registerEffect(EffUnzip.class, "(unzip|extract) %string% to dir[ectory] %-string%");

        Skript.registerCondition(CondFileExists.class, "[(script|program|app[lication]|file) ]%string% exists");

        Skript.registerExpression(ExprFile.class, File.class, ExpressionType.SIMPLE, "[event-]file");
    }

    static void regFE() {
        Skript.registerEvent("DownloadFile", SimpleEvent.class, EvtFileDownload.class, "file download");
        EventValues.registerEventValue(EvtFileDownload.class, String.class, new Getter<String,EvtFileDownload>() {
            @Nullable
            @Override
            public String get(EvtFileDownload e) {
                return e.getUrl();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileDownload.class, File.class, new Getter<File,EvtFileDownload>() {
            @Nullable
            @Override
            public File get(EvtFileDownload e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("RunApp", SimpleEvent.class, EvtRunApp.class, "(file|app|script) (run|execute)");
        EventValues.registerEventValue(EvtRunApp.class, File.class, new Getter<File,EvtRunApp>() {
            @Nullable
            @Override
            public File get(EvtRunApp e) {
                return e.getApp();
            }
        }, 0);

        Skript.registerEvent("FileCreation", SimpleEvent.class, EvtFileCreation.class, "file creat(ion|e)");
        EventValues.registerEventValue(EvtFileCreation.class, File.class, new Getter<File,EvtFileCreation>() {
            @Nullable
            @Override
            public File get(EvtFileCreation e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileDeletion", SimpleEvent.class, EvtFileDeletion.class, "file delet(ion|e)");
        EventValues.registerEventValue(EvtFileDeletion.class, File.class, new Getter<File,EvtFileDeletion>() {
            @Nullable
            @Override
            public File get(EvtFileDeletion e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileWipe", SimpleEvent.class, EvtFileWipe.class, "file (wipe|reset|clear)");
        EventValues.registerEventValue(EvtFileWipe.class, File.class, new Getter<File,EvtFileWipe>() {
            @Nullable
            @Override
            public File get(EvtFileWipe e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileMove", SimpleEvent.class, EvtFileMove.class, "file move");
        EventValues.registerEventValue(EvtFileMove.class, File.class, new Getter<File,EvtFileMove>() {
            @Nullable
            @Override
            public File get(EvtFileMove e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileMove.class, String.class, new Getter<String,EvtFileMove>() {
            @Nullable
            @Override
            public String get(EvtFileMove e) {
                return e.getEvtMFile();
            }
        }, 0);

        Skript.registerEvent("FileRename", SimpleEvent.class, EvtFileRename.class, "file rename");
        EventValues.registerEventValue(EvtFileRename.class, File.class, new Getter<File,EvtFileRename>() {
            @Nullable
            @Override
            public File get(EvtFileRename e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileRename.class, String.class, new Getter<String,EvtFileRename>() {
            @Nullable
            @Override
            public String get(EvtFileRename e) {
                return e.getEvtFileName();
            }
        }, 0);

        Skript.registerEvent("Unzip", SimpleEvent.class, EvtUnzip.class, "unzip");
        EventValues.registerEventValue(EvtUnzip.class, File.class, new Getter<File,EvtUnzip>() {
            @Nullable
            @Override
            public File get(EvtUnzip e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtUnzip.class, String.class, new Getter<String,EvtUnzip>() {
            @Nullable
            @Override
            public String get(EvtUnzip e) {
                return e.getEvtNloc();
            }
        }, 0);

        Skript.registerEvent("FileZip", SimpleEvent.class, EvtUnzip.class, "file zip");
        EventValues.registerEventValue(EvtFileZip.class, File.class, new Getter<File,EvtFileZip>() {
            @Nullable
            @Override
            public File get(EvtFileZip e) {
                return e.getEvtFile();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileZip.class, String.class, new Getter<String,EvtFileZip>() {
            @Nullable
            @Override
            public String get(EvtFileZip e) {
                return e.getEvtZipFile();
            }
        }, 0);
    }
}
