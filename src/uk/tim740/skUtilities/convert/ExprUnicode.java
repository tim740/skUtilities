package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by tim740 on 07/03/16
 */
public class ExprUnicode extends SimpleExpression<String> {
	private Expression<String> str;
    private int ucTy;

	@Override
	@Nullable
	protected String[] get(Event arg0) {
        if (ucTy == 0) {
           /* Properties p = new Properties();
            try {
                p.load(new StringReader("value="+str.getSingle(arg0)));
            } catch (IOException e) {
                skUtilities.prErr(e.getMessage(), getClass().getSimpleName());
            }
            return new String[]{p.getProperty("value")};*/
            //public static void main(String args[]) throws Exception {
            //String s = "0123456789";
/*            byte ptext[] = new byte[0];
            String out = "";
            try {
                ptext = str.getSingle(arg0).getBytes("UTF8");
            } catch (UnsupportedEncodingException e) {
                skUtilities.prErr(e.getMessage(), getClass().getSimpleName());
            }
            for (byte aPtext : ptext) {
                if (Objects.equals(out, "")){
                    out = Character.toString((char) new Byte(aPtext));
                }else{
                    out = (out + "," + aPtext);
                }
                //System.out.print(aPtext + ",");
            }
            return new String[]{out};*/
           // Charset s = Charset.defaultCharset();
           // StandardCharsets.
            //byte[] bytes = str.getSingle(arg0).getBytes(Charset.forName("UTF-8"));
           // byte[] bytes = str.getSingle(arg0).getBytes();
            //String str = new String(bytes, Charset.forName("UTF-8"));
            //System.out.println(str);
            return new String[]{str.getSingle(arg0), "UTF-16"};
            //return new String[]{Character.toString(str.getSingle(arg0).charAt(0))};//Character.toString( str.getSingle(arg0).charAt(0))};
        }else{
            Properties p = new Properties();
            try {
                p.load(new StringReader("key="+str.getSingle(arg0)));
            } catch (IOException e) {
                skUtilities.prErr(e.getMessage(), getClass().getSimpleName());
            }
            return new String[]{p.getProperty("key")};
        }
	}

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
        ucTy = arg3.mark;
        str = (Expression<String>) arg0[0];
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