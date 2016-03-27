package uk.tim740.skUtilities;

import ch.njol.skript.Skript;
import uk.tim740.skUtilities.config.*;

/**
 * Created by tim740 on 27/03/2016
 */
public class RegConfig {
    static void regCo() {
        Skript.registerEffect(EffReloadConfig.class, "reload %string%'s config", "reload config of %string%");
    }
}
