package com.asurion.spc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.asurion.spc.exception.ServiceException;

public class PropertyUtil {

	private static PropertyUtil instance;

	private Properties properties;

	static {
		String environment = EnvironmentUtil.getEnvironment();
		instance = new PropertyUtil();
		instance.properties = loadLayeredProperties(environment + "/config.properties");
	}

	public static String getPropertyValue(String propertyKey) {
		return instance.properties.getProperty(propertyKey);
	}

	private static Properties loadLayeredProperties(String filename) {
		Properties layeredProps = null;

		try {
			layeredProps = loadPropertiesFromClasspath(filename);
		} catch (Exception e) {
			throw new ServiceException("Error loading layered Properties file" + e);
		}

		return layeredProps;
	}

	private static Properties loadPropertiesFromClasspath(String filename) throws IOException {
		Properties props;
		try (InputStream stream = PropertyUtil.class.getClassLoader().getResourceAsStream(filename)) {
			props = new Properties();
			props.load(stream);
		}

		return props;
	}

}
