package uk.tim740.skUtilities.conversion;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprMorseDecode extends SimpleExpression<String> {
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
/*        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".---.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", "--..--", ".-.-.-"};
        char[] eng = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', ',', '.'};*/
        String out = "";
        String[] s = this.string.getSingle(arg0).replaceAll("|", " ").split("\\s+");
        for (int i = 0; i < s.length; i++){
            System.out.println("[value in] " + s[i]);
            if (s[i] == (".-")) {
                out = out + "a";
            }
            if (s[i] == ("-..."))
                out = out + "b";
            if (s[i] == ("-.-."))
                out = out + "c";
            if (s[i] == ("-.."))
                out = out + "d";
            if (s[i] == ("."))
                out = out + "e";
            if (s[i] == ("..-."))
                out = out + "f";
            if (s[i] == ("--."))
                out = out + "g";
            if (s[i] == ("...."))
                out = out + "h";
            if (s[i] == (".."))
                out = out + "i";
            if (s[i] == (".---"))
                out = out + "j";
            if (s[i] == ("-.-"))
                out = out + "k";
            if (s[i] == (".-.."))
                out = out + "l";
            if (s[i] == ("--"))
                out = out + "m";
            if (s[i] == ("-."))
                out = out + "n";
            if (s[i] == ("---")){
                out = out + "o";
            }
            if (s[i] == (".--."))
                out = out + "p";
            if (s[i] == ("--.-"))
                out = out + "q";
            if (s[i] == (".-."))
                out = out + "r";
            if (s[i] == ("..."))
                out = out + "s";
            if (s[i] == ("-"))
                out = out + "t";
            if (s[i] == ("..-"))
                out = out + "u";
            if (s[i] == ("...-"))
                out = out + "v";
            if (s[i] == (".--"))
                out = out + "w";
            if (s[i] == ("-..-"))
                out = out + "x";
            if (s[i] == ("-.--"))
                out = out + "y";
            if (s[i] == ("--.."))
                out = out + "z";
            if (s[i] == ("-----"))
                out = out + "0";
            if (s[i] == (".----"))
                out = out + "1";
            if (s[i] == ("..---"))
                out = out + "2";
            if (s[i] == ("...--"))
                out = out + "3";
            if (s[i] == ("....-"))
                out = out + "4";
            if (s[i] == ("....."))
                out = out + "5";
            if (s[i] == ("-...."))
                out = out + "6";
            if (s[i] == ("--..."))
                out = out + "7";
            if (s[i] == ("---.."))
                out = out + "8";
            if (s[i] == ("----."))
                out = out + "9";
            if (s[i] == (" "))
                out = out + "";
        }
        System.out.println("[value out] " + out);
        /*for (int i = 0; i < s.length; i++){
            for (int j = 0; j < morse.length; j++){
                if (morse[j] == s[i]){
                    out = out + eng[j] + " ";
                }
            }
        }*/
       /* for (int m = 0; m < morse.length; m++)
        {
            if (s.equals(morse[m]))
                System.out.print(eng[m]);
        }
        System.out.print(" ");*/
        /*    String s = this.string.getSingle(arg0).replaceAll("|", " ");
            for (int i=0; i <morse.length; i++) {
                n += 1; //s.replaceAll(morse[n], eng[n]);
                out = (out + s.replaceAll(morse[n], eng[n]));
            }
            //return new String[]{out};*/
        return new String[]{out};
    }
}
