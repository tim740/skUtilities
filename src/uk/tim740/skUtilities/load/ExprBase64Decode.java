package uk.tim740.skUtilities.load;

import javax.annotation.Nullable;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprBase64Decode extends SimpleExpression<String> {
	private int bDecode;
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
		bDecode = arg3.mark;
		this.string = (Expression<String>) arg0[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "decode %string% to base[ ]64 using (0¦utf-8|1¦ascii|2¦iso-8859-1)";
	}
	@Override
	@Nullable
	protected String[] get(Event arg0) {
		String decoty = null;
		if (bDecode == 0){
			decoty = new String("UTF-8");
		}else if (bDecode == 1){
			decoty = new String("US_ASCII");
		}else{
			decoty = new String("ISO_8859_1");
		}
		String s = this.string.getSingle(arg0);
		try {
			byte[] deby = s.getBytes(decoty);
			String enco = Base64.getEncoder().encodeToString(deby);
			byte[] deco = Base64.getDecoder().decode(enco);
			return new String[]{deco.toString()};
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}
	}
}