package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740 on 22/02/2016
 */
public class ExprMorse extends SimpleExpression<String> {
    private int morseTy;
    private Expression<String> string;
    private static final char[] Letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final String[] MorseLet = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
            ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        String out = "";
        if(morseTy == 0){
            for (char value : this.string.getSingle(arg0).toLowerCase().toCharArray()) {
                for (int j = 0; j < Letters.length; j++) {
                    if (Letters[j] == value) {
                        out = out + MorseLet[j] + " ";
                    }
                }
            }
        }else{
            for (String word : this.string.getSingle(arg0).split("\\s\\s\\s")) {
                for (String letter : word.split("\\s")) {
                    for (int j = 0; j < MorseLet.length; j++) {
                        if (letter.equals(MorseLet[j])) {
                            out = out + Letters[j];
                        }
                    }
                }
            }
        }
        return new String[]{out};
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        morseTy = arg3.mark;
        this.string = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
