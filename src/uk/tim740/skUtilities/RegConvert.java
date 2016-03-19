package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.tim740.skUtilities.convert.*;

/**
 * Created by tim740 on 22/02/2016
 */
class RegConvert {
    static void regC() {
        Skript.registerExpression(ExprToBin.class, String.class, ExpressionType.PROPERTY, "convert (0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal) %string% to bin[ary]");
        Skript.registerExpression(ExprToAscii.class, String.class, ExpressionType.PROPERTY, "convert ascii %number% to (0¦(text|string)|1¦unicode)");
        Skript.registerExpression(ExprToString.class, String.class, ExpressionType.PROPERTY, "convert (text|string) %string% to (0¦ascii|1¦unicode)");

        Skript.registerExpression(ExprFromBin.class, String.class, ExpressionType.PROPERTY, "convert bin[ary] %string% to (0¦(text|string)|1¦decimal|2¦hexa[decimal]|3¦octal)");
        Skript.registerExpression(ExprFromUnicode.class, String.class, ExpressionType.PROPERTY, "convert unicode %string% to (0¦(text|string)|1¦ascii)");

        Skript.registerExpression(ExprHexaToNum.class, String.class, ExpressionType.PROPERTY, "convert hexa[decimal] %string% to num[ber]");
        Skript.registerExpression(ExprNumToHexa.class, String.class, ExpressionType.PROPERTY, "convert num[ber] %number% to hexa[decimal]");

        Skript.registerExpression(ExprHexToRgb.class, String.class, ExpressionType.PROPERTY, "convert hex %string% to rgb");
        Skript.registerExpression(ExprRgbToHex.class, String.class, ExpressionType.PROPERTY, "convert rgb %number%, %number%, %number% to hex");

        Skript.registerExpression(ExprBase64.class, String.class, ExpressionType.PROPERTY, "(0¦en|1¦de)code base[ ]64 %string%");
        Skript.registerExpression(ExprMorse.class, String.class, ExpressionType.PROPERTY, "(0¦en|1¦de)code morse[ code] %string%");
        Skript.registerExpression(ExprEncrypt.class, String.class, ExpressionType.PROPERTY, "(0¦en|1¦de)crypt %string% using %-string% with key %-string%");
        Skript.registerExpression(ExprHash.class, String.class, ExpressionType.PROPERTY, "hash[ed] %string% using %-string%");

        Skript.registerExpression(ExprUnixToDate.class, String.class, ExpressionType.PROPERTY, "convert unix[ date] %string% to date[ formatted as %-string%]");
        Skript.registerExpression(ExprMirrorTxt.class, String.class, ExpressionType.PROPERTY, "mirror[ed] %string%");
    }
}
