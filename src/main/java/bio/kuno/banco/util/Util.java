package bio.kuno.banco.util;

public class Util {
	public static boolean isNotEmpty(String param) {
		return param != null && param.trim().length() >0;
	}
}