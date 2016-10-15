package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import uk.tim740.skUtilities.url.*;

/**
 * Created by tim740 on 15/10/2016
 */
class RegUrl {
    static void reg() {
        Skript.registerExpression(ExprUrlContents.class, String.class, ExpressionType.PROPERTY, "contents from url %string%", "url %string%'s contents");
        Skript.registerExpression(ExprUrlReadLine.class, String.class, ExpressionType.PROPERTY, "line %number% from url %string%", "url %string%'s line %number%");
        Skript.registerExpression(ExprUrlLines.class, Number.class, ExpressionType.PROPERTY, "line count of url %string%", "url %string%'s line count");
        Skript.registerExpression(ExprUrlSize.class, String.class, ExpressionType.PROPERTY, "size of url %string%", "url %string%'s size");
        Skript.registerExpression(ExprUrlSizeBytes.class, Number.class, ExpressionType.PROPERTY, "size of url %string% in bytes", "url %string%'s size in bytes");
        Skript.registerExpression(ExprUrlResponseCode.class, Integer.class, ExpressionType.PROPERTY, "response code of url %string%", "url %string%'s response code");
        Skript.registerExpression(ExprUrlLastModified.class, Number.class, ExpressionType.PROPERTY, "last modified value of url %string%", "url %string%'s last modified value");

        Skript.registerExpression(ExprUrlSSLVerifier.class, String.class, ExpressionType.PROPERTY, "ssl verifier of url %string%", "url %string%'s ssl verifier");
        Skript.registerExpression(ExprUrlSSLSerialNumber.class, String.class, ExpressionType.PROPERTY, "ssl serial number of url %string%", "url %string%'s ssl serial number");
        Skript.registerExpression(ExprUrlSSLIssueExpire.class, Number.class, ExpressionType.PROPERTY, "ssl (0¦issue|1¦expire) value of url %string%", "url %string%'s ssl (0¦issue|1¦expire) value");
        Skript.registerExpression(ExprUrlSSLAlgorithm.class, String.class, ExpressionType.PROPERTY, "ssl algorithm of url %string%", "url %string%'s ssl algorithm");
    }
}