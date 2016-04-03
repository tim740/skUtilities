package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.assist.FileSize;

import javax.annotation.Nullable;
import java.io.File;

/**
 * Created by tim740 on 03/04/2016
 */
public class ExprDiskSpace extends SimpleExpression<String>{
    private int ty;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        if (ty == 0){
            return new String[]{FileSize.getSize(new File(File.separator).getTotalSpace())};
        }else if (ty == 1){
            return new String[]{FileSize.getSize(new File(File.separator).getFreeSpace())};
        }else{
            return new String[]{FileSize.getSize(new File(File.separator).getUsableSpace())};
        }
	}

    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        ty = arg3.mark;
        return true;
    }
    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
