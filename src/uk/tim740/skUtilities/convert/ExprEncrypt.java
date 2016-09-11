package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import uk.tim740.skUtilities.skUtilities;

import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by tim740 on 23/02/2016
 */
public class ExprEncrypt extends SimpleExpression<String> {
    private Expression<String> str, key, cipher;
    private int ty;

    @Override
    @Nullable
    protected String[] get(Event e) {
        byte[] cout = new byte[0];
        Cipher c = null;
        try{
            c = Cipher.getInstance(cipher.getSingle(e).toUpperCase());
            c.init(ty, new SecretKeySpec(key.getSingle(e).getBytes(), cipher.getSingle(e).toUpperCase()));
        }catch (Exception x){
            skUtilities.prSysE(x.getMessage() + " '"+ cipher.getSingle(e).toUpperCase() +"'", getClass().getSimpleName(), x);
        }
        assert c != null;
        if (ty == Cipher.ENCRYPT_MODE){
            try{
                cout = c.doFinal(str.getSingle(e).getBytes());
            }catch (Exception x){
                skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
            }
            return new String[]{new BASE64Encoder().encode(cout)};
        }else{
            byte[] decry;
            String out = "";
            try{
                decry = new BASE64Decoder().decodeBuffer(str.getSingle(e));
                cout = c.doFinal(decry);
            }catch (Exception x) {
                skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
            }for (byte aCout : cout) {
                out = (out + Character.toString((char) new Byte(aCout).intValue()));
            }
            return new String[]{out};
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
        if (p.mark == 0){
            ty = Cipher.ENCRYPT_MODE;
        }else{
            ty = Cipher.DECRYPT_MODE;
        }
        str = (Expression<String>) e[0];
        cipher = (Expression<String>) e[1];
        key = (Expression<String>) e[2];
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
    public String toString(@Nullable Event e, boolean b) {
        return getClass().getName();
    }
}
