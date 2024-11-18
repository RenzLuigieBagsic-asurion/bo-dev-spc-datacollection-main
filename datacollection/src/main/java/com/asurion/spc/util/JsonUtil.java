package com.asurion.spc.util;

import com.asurion.spc.exception.ServiceException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author KristianEl-Roi.DelosSantos
 *
 * @date 28 Jun 2021
 */
public class JsonUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static <T> T convertJsonString(Class<T> clazz, String body) {

		T params = null;
		try {
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			params = mapper.readValue(body, clazz);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return params;
	}

	
	public static <T> String writeAsString(T t) {

		String body = null;

		try {
			body = new ObjectMapper().writeValueAsString(t);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return body;

	}
}
