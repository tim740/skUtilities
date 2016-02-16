package uk.tim740.skUtilities.conversion;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprMorse extends SimpleExpression<String> {
    private int edMorse;
    private Expression<String> string;

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
        edMorse = arg3.mark;
        this.string = (Expression<String>) arg0[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return this.getClass().getName();
    }

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".---.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "--..--", ".-.-.-"};
        char[] eng = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ',', '.'};
        Integer n = -1;
        String out = "";
        if (edMorse == 0){
            char[] s = this.string.getSingle(arg0).toLowerCase().toCharArray();
            for (int i = 0; i < s.length; i++){
                for (int j = 0; j < eng.length; j++){
                    if (eng[j] == s[i]){
                        out = out + morse[j] + " ";
                    }
                }
            }
            return new String[]{out};
        }else{
        /*    String s = this.string.getSingle(arg0).replaceAll("|", " ");
            for (int i=0; i <morse.length; i++) {
                n += 1; //s.replaceAll(morse[n], eng[n]);
                out = (out + s.replaceAll(morse[n], eng[n]));
            }
            //return new String[]{out};*/
        }
        return new String[]{out};
    }
}
