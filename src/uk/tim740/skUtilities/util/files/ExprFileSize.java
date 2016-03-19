package uk.tim740.skUtilities.util.files;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by tim740 on 17/03/2016
 */
public class ExprFileSize extends SimpleExpression<String>{
	private Expression<String> path;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        File pth = new File("plugins\\" + path.getSingle(arg0).replaceAll("/", "\\"));
        double fs = pth.length();
        DecimalFormat df = new DecimalFormat("#.##");
        if (pth.exists()){
            if (fs <1024){
                return new String[]{pth.length() + "Bytes"};
            }else if (fs <1048576){
                return new String[]{df.format(fs /1024) + "KB"};
            }else if (fs <1073741824) {
                return new String[]{df.format(fs /1048576) + "MB"};
            }else {
                return new String[]{df.format(fs /1073741824) + "GB"};
            }
        }else{
            skUtilities.prEW("'" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
            return null;
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        path = (Expression<String>) arg0[0];
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
