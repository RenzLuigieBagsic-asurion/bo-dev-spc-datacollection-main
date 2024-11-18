/**
 * 
 */
package com.asurion.spc.util;

/**
 * @author KristianEl-Roi.DelosSantos
 *
 * @date 19 May 2021
 */
public class EnvironmentUtil {
	private EnvironmentUtil() {
		throw new IllegalStateException("Utility class");
	}

	private static String env = null;

	static {
		env = System.getenv("ENVIRONMENT");
		if (null == env) {
			env = "dev";
		}
	}

	public static String getEnvironment() {
		return env;
	}

	public static boolean isProd() {
		return "prod".equalsIgnoreCase(env);
	}
}