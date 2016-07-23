package uk.tim740.skUtilities.util;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.command.Commands;
import ch.njol.skript.lang.*;
import ch.njol.skript.lang.function.Functions;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;
import java.util.*;

/**
 * Created by tim740 on 27/03/16
 */
public class EffReloadSkript extends Effect{
    private Expression<String> str;
    private final static ScriptLoader.ScriptInfo loadedScripts = new ScriptLoader.ScriptInfo();
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final static Map<Class<? extends Event>, List<Trigger>> triggers = new HashMap<>();
    private final static List<Trigger> selfRegisteredTriggers = new ArrayList<>();


    @Override
	protected void execute(Event arg0) {
        File f = new File("plugins" + File.separator + "Skript" + File.separator + "scripts" + File.separator + str.getSingle(arg0).replaceAll(".sk", "") + ".sk");
        if (f.exists()) {
            if (!f.isDirectory()) {
                unloadScript(f);
                ScriptLoader.loadScripts(new File[]{f});
            }
        }else{
            skUtilities.prSysE("'" + f + "' doesn't exist!", getClass().getSimpleName());
        }
	}
    private static ScriptLoader.ScriptInfo unloadScript(final File script) {
        final ScriptLoader.ScriptInfo r = unloadScript_(script);
        Functions.validateFunctions();
        return r;
    }
    private static ScriptLoader.ScriptInfo unloadScript_(final File script) {
        final ScriptLoader.ScriptInfo info = removeTriggers(script);
        synchronized (loadedScripts) {
            loadedScripts.subtract(info);
        }
        return info;
    }
    private static ScriptLoader.ScriptInfo removeTriggers(final File script) {
        final ScriptLoader.ScriptInfo info = new ScriptLoader.ScriptInfo();
        info.files = 1;

        final Iterator<List<Trigger>> triggersIter = triggers.values().iterator();
        while (triggersIter.hasNext()) {
            final List<Trigger> ts = triggersIter.next();
            for (int i = 0; i < ts.size(); i++) {
                if (script.equals(ts.get(i).getScript())) {
                    info.triggers++;
                    ts.remove(i);
                    i--;
                    if (ts.isEmpty())
                        triggersIter.remove();
                }
            }
        }

        for (int i = 0; i < selfRegisteredTriggers.size(); i++) {
            final Trigger t = selfRegisteredTriggers.get(i);
            if (script.equals(t.getScript())) {
                info.triggers++;
                ((SelfRegisteringSkriptEvent) t.getEvent()).unregister(t);
                selfRegisteredTriggers.remove(i);
                i--;
            }
        }

        info.commands = Commands.unregisterCommands(script);

        info.functions = Functions.clearFunctions(script);

        return info;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        str = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}