/**
 * 
 */
package com.asurion.spc.http;

import java.util.Map;

import org.apache.http.client.methods.HttpGet;

/**
 * @author djpichay
 *
 */
public class HttpGetter {

	private HttpGetter() {}

	public static HttpClientResponse get(HttpClientRequest request) {

		HttpGet httpGet = new HttpGet(request.getUrl());
		Map<String, String> headers = request.getHeaders();
		if (null != headers) {
			headers.forEach((k, v) -> httpGet.addHeader(k, v));
		}

		return HttpUtil.request(httpGet);
	}
	
}
