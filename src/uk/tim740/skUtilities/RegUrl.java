package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.tim740.skUtilities.url.*;

/**
 * Created by tim740 on 15/10/2016
 */
class RegUrl {
    static void reg() {
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
}