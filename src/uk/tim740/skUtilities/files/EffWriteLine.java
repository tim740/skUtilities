package uk.tim740.skUtilities.files;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by tim740 on 19/03/2016
 */
public class EffWriteLine extends Effect{
    private Expression<Number> line;
	private Expression<String> path, str;

	@Override
	protected void execute(Event arg0) {
        File pth = new File("plugins" + File.separator + path.getSingle(arg0).replaceAll("/", File.separator));
        if (pth.exists()) {
            try {
                ArrayList<String> cl = new ArrayList<>();
                String inLi;
                BufferedReader br = new BufferedReader(new FileReader(pth));
                while ((inLi = br.readLine()) != null) {
                    cl.add(inLi);
                }
                br.close();
                cl.set(Integer.parseInt(line.getSingle(arg0).toString()) - 1, str.getSingle(arg0));
                String[] out = new String[cl.size()];
                BufferedWriter bw = new BufferedWriter(new FileWriter(pth));
                for (String aCl : cl.toArray(out)) {
                    bw.write(aCl);
                    bw.newLine();
                }
                bw.close();
            } catch (Exception e) {
                skUtilities.prEW(e.getMessage(), getClass().getSimpleName(), 0);
            }
        } else {
            skUtilities.prEW("File: '" + pth + "' doesn't exist!", getClass().getSimpleName(), 0);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        line = (Expression<Number>) arg0[0];
        path = (Expression<String>) arg0[1];
        str = (Expression<String>) arg0[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}