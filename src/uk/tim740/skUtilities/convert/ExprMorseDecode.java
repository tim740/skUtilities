package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

/**
 * Created by tim740.
 */
public class ExprMorseDecode extends SimpleExpression<String> {
    private Expression<String> string;
    private static final char[] Letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final String[] MorseLet = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---",
            ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        String out = "";
        String s = this.string.getSingle(arg0);
        String userphrase = s;
        String[] words = userphrase.split("\\s\\s\\s");
        String[] wordamt = words;
        for(int i =0; i<wordamt.length; i++){
            String[] letters = words[i].split("\\s");
            for(int in=0; in<letters.length; in++){
                for(int j=0; j<MorseLet.length; j++){
                    if(letters[in].equals(MorseLet[j])){
                        out = out + Letters[j];
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
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        this.string = (Expression<String>) arg0[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return this.getClass().getName();
    }
}
