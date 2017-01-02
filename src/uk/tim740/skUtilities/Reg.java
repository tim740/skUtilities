package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Date;
import ch.njol.skript.util.Getter;
import uk.tim740.skUtilities.convert.*;
import uk.tim740.skUtilities.files.*;
import uk.tim740.skUtilities.files.event.*;
import uk.tim740.skUtilities.url.*;
import uk.tim740.skUtilities.util.*;

import javax.annotation.Nullable;
import java.nio.file.Path;

/**
 * Created by tim740 on 18/12/2016
 */
class Reg {
    static void convert() {
        Skript.registerExpression(ExprToBin.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert (0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal) %string% to bin[ary]", "[skutil[ities] ](0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal) %string% as bin[ary]");
        Skript.registerExpression(ExprToAscii.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert ascii %numbers% to (0¦(text|string)|1¦unicode)", "[skutil[ities] ]ascii %numbers% as (0¦(text|string)|1¦unicode)");

        Skript.registerExpression(ExprFromString.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert (text|string) %string% to (0¦ascii|1¦unicode)", "[skutil[ities] ](text|string) %string% as (0¦ascii|1¦unicode)");
        Skript.registerExpression(ExprFromBin.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert bin[ary] %string% to (0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal)", "[skutil[ities] ]bin[ary] %string% as (0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal)");
        Skript.registerExpression(ExprFromUnicode.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert unicode %string% to (0¦(text|string)|1¦ascii)", "[skutil[ities] ]unicode %string% as (0¦(text|string)|1¦ascii)");

        Skript.registerExpression(ExprHexaToNum.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert hexa[decimal] %string% to num[ber]", "[skutil[ities] ]hexa[decimal] %string% as num[ber]");
        Skript.registerExpression(ExprNumToHexa.class, String.class, ExpressionType.PROPERTY, "convert num[ber] %number% to hexa[decimal]", "[skutil[ities] ]num[ber] %number% as hexa[decimal]");

        Skript.registerExpression(ExprHexToRgb.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert hex %string% to rgb",  "[skutil[ities] ]hex %string% as rgb");
        Skript.registerExpression(ExprRgbToHex.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert rgb %number%, %number%, %number% to hex", "[skutil[ities] ]rgb %number%, %number%, %number% as hex");

        Skript.registerExpression(ExprDateToUnix.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert date %date% to unix[ date]", "[skutil[ities] ]date %date% as unix[ date]");
        Skript.registerExpression(ExprUnixToDate.class, Date.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert unix[ date] %number% to date", "[skutil[ities] ]unix[ date] %number% as date");
        Skript.registerExpression(ExprUnixToFormattedDate.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert unix[ date] %number% to date formatted as %string%", "[skutil[ities] ]unix[ date] %number% as date formatted as %string%");

        Skript.registerExpression(ExprBase64.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦en|1¦de)code base[ ]64 %string%");
        Skript.registerExpression(ExprMorse.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦en|1¦de)code morse[ code] %string%");
        Skript.registerExpression(ExprEncrypt.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦en|1¦de)crypt %string% using %-string% with key %-string%");

        Skript.registerExpression(ExprClearAccented.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]clear accented chars from %string%");
        Skript.registerExpression(ExprHash.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]hash[ed] %string% using %-string%");
        Skript.registerExpression(ExprMirrorTxt.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](mirror[ed]|flip[ped]|reverse[d]) %string%");

        Skript.registerExpression(ExprToUpperLower.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]convert (text|string) %string% to (0¦uppercase|1¦lowercase)");
        Skript.registerExpression(ExprRandomizeString.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]randomize %string%");
    }

    static void files() {
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

        fileEvents();
    }
    private static void fileEvents() {
        Skript.registerExpression(ExprFile.class, Path.class, ExpressionType.SIMPLE, "[event-]file");

        Skript.registerEvent("DownloadFile", SimpleEvent.class, EvtFileDownload.class, "[skutil[ities] ]file download");
        EventValues.registerEventValue(EvtFileDownload.class, String.class, new Getter<String, EvtFileDownload>() {
            @Nullable
            @Override
            public String get(EvtFileDownload e) {
                return e.getUrl();
            }
        }, 0);
        EventValues.registerEventValue(EvtFileDownload.class, Path.class, new Getter<Path, EvtFileDownload>() {
            @Nullable
            @Override
            public Path get(EvtFileDownload e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("RunApp", SimpleEvent.class, EvtRunApp.class, "[skutil[ities] ](file|app|script) (run|execute)");
        EventValues.registerEventValue(EvtRunApp.class, Path.class, new Getter<Path, EvtRunApp>() {
            @Nullable
            @Override
            public Path get(EvtRunApp e) {
                return e.getApp();
            }
        }, 0);

        Skript.registerEvent("FileCreation", SimpleEvent.class, EvtFileCreation.class, "[skutil[ities] ]file creat(ion|e)");
        EventValues.registerEventValue(EvtFileCreation.class, Path.class, new Getter<Path, EvtFileCreation>() {
            @Nullable
            @Override
            public Path get(EvtFileCreation e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileDeletion", SimpleEvent.class, EvtFileDeletion.class, "[skutil[ities] ]file delet(ion|e)");
        EventValues.registerEventValue(EvtFileDeletion.class, Path.class, new Getter<Path, EvtFileDeletion>() {
            @Nullable
            @Override
            public Path get(EvtFileDeletion e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileWipe", SimpleEvent.class, EvtFileWipe.class, "[skutil[ities] ]file (wipe|reset|clear)");
        EventValues.registerEventValue(EvtFileWipe.class, Path.class, new Getter<Path, EvtFileWipe>() {
            @Nullable
            @Override
            public Path get(EvtFileWipe e) {
                return e.getEvtFile();
            }
        }, 0);

        Skript.registerEvent("FileMove", SimpleEvent.class, EvtFileMove.class, "[skutil[ities] ]file move");
        EventValues.registerEventValue(EvtFileMove.class, Path.class, new Getter<Path, EvtFileMove>() {
            @Nullable
            @Override
            public Path get(EvtFileMove e) {
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

        Skript.registerEvent("FileCopy", SimpleEvent.class, EvtFileCopy.class, "[skutil[ities] ]file copy");
        EventValues.registerEventValue(EvtFileCopy.class, Path.class, new Getter<Path, EvtFileCopy>() {
            @Nullable
            @Override
            public Path get(EvtFileCopy e) {
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

        Skript.registerEvent("FileRename", SimpleEvent.class, EvtFileRename.class, "[skutil[ities] ]file rename");
        EventValues.registerEventValue(EvtFileRename.class, Path.class, new Getter<Path, EvtFileRename>() {
            @Nullable
            @Override
            public Path get(EvtFileRename e) {
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

        Skript.registerEvent("FileWrite", SimpleEvent.class, EvtFileWrite.class, "[skutil[ities] ]file write");
        EventValues.registerEventValue(EvtFileWrite.class, Path.class, new Getter<Path, EvtFileWrite>() {
            @Nullable
            @Override
            public Path get(EvtFileWrite e) {
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

        Skript.registerEvent("Unzip", SimpleEvent.class, EvtUnzip.class, "[skutil[ities] ]unzip");
        EventValues.registerEventValue(EvtUnzip.class, Path.class, new Getter<Path, EvtUnzip>() {
            @Nullable
            @Override
            public Path get(EvtUnzip e) {
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

        Skript.registerEvent("FileZip", SimpleEvent.class, EvtUnzip.class, "[skutil[ities] ]file zip");
        EventValues.registerEventValue(EvtFileZip.class, Path.class, new Getter<Path, EvtFileZip>() {
            @Nullable
            @Override
            public Path get(EvtFileZip e) {
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

    static void yaml() {
        Skript.registerExpression(SExprYaml.class, Object.class, ExpressionType.PROPERTY, "[skutil[ities] ]y[a]ml (0¦value|1¦nodes|2¦node[s with] keys|3¦list) %string% (from|of) file %-string%");
        Skript.registerCondition(CondYamlExists.class, "[skutil[ities] ]y[a]ml[ path] %string% in file %-string% exists", "[skutil[ities] ]y[a]ml[ path] %string% in file %-string% does(n't| not) exist");
    }

    static void url() {
        Skript.registerExpression(ExprUrlContents.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]contents from url %string%", "[skutil[ities] ]url %string%'s contents");
        Skript.registerExpression(ExprUrlReadLine.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]line %number% from url %string%", "[skutil[ities] ]url %string%'s line %number%");
        Skript.registerExpression(ExprUrlLines.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]line count of url %string%", "[skutil[ities] ]url %string%'s line count");
        Skript.registerExpression(ExprUrlSize.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]size of url %string%", "[skutil[ities] ]url %string%'s size");
        Skript.registerExpression(ExprUrlSizeBytes.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]size of url %string% in bytes", "[skutil[ities] ]url %string%'s size in bytes");
        Skript.registerExpression(ExprUrlResponseCode.class, Integer.class, ExpressionType.PROPERTY, "[skutil[ities] ]response code of url %string%", "[skutil[ities] ]url %string%'s response code");
        Skript.registerExpression(ExprUrlOnlineState.class, Boolean.class, ExpressionType.PROPERTY, "[skutil[ities] ]online stat(us|e) of url %string%", "[skutil[ities] ]url %string%'s online stat(us|e)");
        Skript.registerExpression(ExprUrlLastModified.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]last modified value of url %string%", "[skutil[ities] ]url %string%'s last modified value");

        Skript.registerExpression(ExprUrlSSLVerifier.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]ssl verifier of url %string%", "[skutil[ities] ]url %string%'s ssl verifier");
        Skript.registerExpression(ExprUrlSSLSerialNumber.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]ssl serial number of url %string%", "[skutil[ities] ]url %string%'s ssl serial number");
        Skript.registerExpression(ExprUrlSSLIssueExpire.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]ssl (0¦issue|1¦expire) value of url %string%", "[skutil[ities] ]url %string%'s ssl (0¦issue|1¦expire) value");
        Skript.registerExpression(ExprUrlSSLAlgorithm.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]ssl algorithm of url %string%", "[skutil[ities] ]url %string%'s ssl algorithm");
        Skript.registerExpression(ExprUrlSSLVersion.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]ssl version of url %string%", "[skutil[ities] ]url %string%'s ssl version");
    }

    static void utils() {
        Skript.registerExpression(ExprGetTimeZone.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]time[ ]zone of server", "[skutil[ities] ]server's time[ ]zone");
        Skript.registerExpression(ExprTimeZoneList.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ][all ]time[ ]zones");
        Skript.registerExpression(ExprTimeInTimeZone.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ][current ]time in time[ ]zone %string%", "[skutil[ities] ][current ]time[ ]zone %string%'s time");
        Skript.registerExpression(ExprGetRegion.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]region of server", "[skutil[ities] ]server's region");
        Skript.registerCondition(CondisTimeZone.class, "[skutil[ities] ]server is time[ ]zone %string%", "[skutil[ities] ]server is(n'| no)t time[ ]zone %string%");

        Skript.registerExpression(ExprGetJsonIDS.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]content of json value(0¦ |1¦'s) %strings% from text %-string%", "[skutil[ities] ]value's %strings%'s json contents from text %-string%");

        Skript.registerExpression(ExprLoaded.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]number of[ loaded] (0¦(commands|cmds)|1¦functions|2¦s(c|k)ripts|3¦triggers|4¦statements|5¦variables|6¦aliases|7¦events|8¦effects|9¦expressions|10¦conditions)");
        Skript.registerExpression(ExprLoadedList.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦plugins|1¦addons) list", "[skutil[ities] ]list of (0¦plugins|1¦addons)");
        Skript.registerExpression(ExprVersion.class, String.class, ExpressionType.PROPERTY, "[skutil[ities] ]%string%'s version", "[skutil[ities] ]version of %string%");
        Skript.registerExpression(ExprSysTime.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ][current ]system (0¦nanos[econds]|1¦millis[econds]|2¦seconds)");
        Skript.registerExpression(ExprRam.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ](0¦free|1¦total|2¦max) (ram|memory)");
        Skript.registerExpression(ExprCaseLength.class, Number.class, ExpressionType.PROPERTY, "[skutil[ities] ]number of (0¦upper|1¦lower)case char[acter]s in %string%");

        Skript.registerEffect(EffRunOpCmd.class, "[skutil[ities] ](force|make) %player% run (cmd|command) %string% as op");
        Skript.registerEffect(EffSkReloadAliases.class, "[skutil[ities] ]skript reload aliases");
        Skript.registerEffect(EffReloadSkript.class, "[skutil[ities] ]reload s(k|c)ript %string%");
        Skript.registerEffect(EffRestartServer.class, "[skutil[ities] ]re(0¦start|1¦load) server");

        Skript.registerCondition(CondStartsEndsWith.class, "[skutil[ities] ]%string% (0¦starts|1¦ends) with %-string%", "[skutil[ities] ]%string% does(n't| not) (0¦start|1¦end) with %-string%");
    }
}
