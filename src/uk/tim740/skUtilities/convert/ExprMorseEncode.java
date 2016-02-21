package uk.tim740.skUtilities.convert;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Created by tim740.
 */
public class ExprMorseEncode extends SimpleExpression<String> {
    private Expression<String> string;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".---.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "--..--", ".-.-.-"};
        char[] eng = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ',', '.'};
        String out = "";
        for (char value : this.string.getSingle(arg0).toLowerCase().toCharArray()) {
            for (int j = 0; j < eng.length; j++) {
                if (eng[j] == value) {
                    out = out + morse[j] + " ";
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
