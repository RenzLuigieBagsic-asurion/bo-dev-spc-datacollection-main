package com.asurion.spc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private StringUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String bytesToHexString(byte[] bytes) {
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static byte[] hexStringToByteArray(String hexStr) {
		byte bArray[] = new byte[hexStr.length() / 2];
		for (int i = 0; i < (hexStr.length() / 2); i++) {
			byte firstNibble = Byte.parseByte(hexStr.substring(2 * i, 2 * i + 1), 16); // [x,y)
			byte secondNibble = Byte.parseByte(hexStr.substring(2 * i + 1, 2 * i + 2), 16);
			int finalByte = (secondNibble) | (firstNibble << 4);
			bArray[i] = (byte) finalByte;
		}
		return bArray;
	}

	public static String regexMatch(String string, String patternString) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(string);

		if (matcher.find()) {
			return string.substring(matcher.start(), matcher.end());
		}

		return "";
	}

	public static boolean hasText(String string) {
		if (null == string) {
			return false;
		}
		return !string.trim().isEmpty();
	}

	public static String toUpperCaseTrimmed(String string) {
		if (null == string) {
			return null;
		}
		return string.trim().toUpperCase();
	}
	
	public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
}
