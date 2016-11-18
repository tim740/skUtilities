package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.util.Date;
import uk.tim740.skUtilities.convert.*;

/**
 * Created by tim740 on 22/02/2016
 */
class RegConvert {
    static void reg() {
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
}
