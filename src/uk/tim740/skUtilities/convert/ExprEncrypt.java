package uk.tim740.skUtilities.convert;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import uk.tim740.skUtilities.skUtilities;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by tim740 on 23/02/2016
 */
public class ExprEncrypt extends SimpleExpression<String> {
  private Expression<String> str, key, cipher;
  private int ty;

  @Override
  @Nullable
  protected String[] get(Event e) {
    try {
      Cipher c = Cipher.getInstance(cipher.getSingle(e).toUpperCase());
      c.init(ty, new SecretKeySpec(key.getSingle(e).getBytes(), cipher.getSingle(e).toUpperCase()));
      if (ty == Cipher.ENCRYPT_MODE) {
        return new String[]{Base64.getEncoder().encodeToString(c.doFinal(str.getSingle(e).getBytes()))};
      } else {
        return new String[]{new String(c.doFinal(Base64.getDecoder().decode(str.getSingle(e))))};
      }
    } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException x) {
      skUtilities.prSysE(x.getMessage(), getClass().getSimpleName(), x);
    } catch (NoSuchAlgorithmException x) {
      skUtilities.prSysE(x.getMessage() + " '" + cipher.getSingle(e).toUpperCase() + "'", getClass().getSimpleName(), x);
    } catch (InvalidKeyException x) {
      skUtilities.prSysE("Invalid Key: '" + key.getSingle(e) + "'", getClass().getSimpleName(), x);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] e, int i, Kleenean k, SkriptParser.ParseResult p) {
    if (p.mark == 0) {
      ty = Cipher.ENCRYPT_MODE;
    } else {
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
