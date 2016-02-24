package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import uk.tim740.skUtilities.Main;

import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by tim740 on 23/02/2016
 */
public class ExprEncrypt extends SimpleExpression<String> {
    private Expression<String> string, key, cipher;
    private int type;

    @Override
    @Nullable
    protected String[] get(Event arg0) {
        String[] keySpl = this.key.getSingle(arg0).split("");
        byte[] iKey = new byte[0];
        for (int i=0; i<keySpl.length; i++){
            iKey[i] = new Byte(keySpl[i]);
        }
        String iCipher = this.cipher.getSingle(arg0);
        String iString = this.string.getSingle(arg0);
        Key Ekey = new SecretKeySpec(iKey, iCipher);
        Cipher c = null;
        try {
            c = Cipher.getInstance(iCipher);
        } catch (NoSuchAlgorithmException e) {
            Main.prErr("NoSuchAlgorithmException", getClass().getSimpleName());
        } catch (NoSuchPaddingException e) {
            Main.prErr("NoSuchPaddingException", getClass().getSimpleName());
        }
        if (type == 0){
            byte[] encry = new byte[0];
            try {
                if (c != null) {
                    c.init(Cipher.ENCRYPT_MODE, Ekey);
                    encry = c.doFinal(iString.getBytes());
                }
            }catch (InvalidKeyException e) {
                Main.prErr("InvalidKeyException", getClass().getSimpleName());
            }catch (IllegalBlockSizeException e) {
                Main.prErr("IllegalBlockSizeException", getClass().getSimpleName());
            }catch (BadPaddingException e) {
                Main.prErr("BadPaddingException", getClass().getSimpleName());
            }
            String out = new BASE64Encoder().encode(encry);
            return new String[]{out};
        }else{
            byte[] decry;
            byte[] out = new byte[0];
            try {
                decry = new BASE64Decoder().decodeBuffer(iString);
                if (c != null) {
                    c.init(Cipher.DECRYPT_MODE, Ekey);
                    out = c.doFinal(decry);
                }
            }catch (InvalidKeyException e){
                Main.prErr("InvalidKeyException", getClass().getSimpleName());
            }catch (IOException e){
                Main.prErr("IOException", getClass().getSimpleName());
            }catch (IllegalBlockSizeException e) {
                Main.prErr("IllegalBlockSizeException", getClass().getSimpleName());
            }catch (BadPaddingException e) {
                Main.prErr("BadPaddingException", getClass().getSimpleName());
            }

            return new String[]{Arrays.toString(out)};
        }
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
        type = arg3.mark;
        this.string = (Expression<String>) arg0[0];
        this.cipher = (Expression<String>) arg0[1];
        this.key = (Expression<String>) arg0[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return getClass().getName();
    }
}
