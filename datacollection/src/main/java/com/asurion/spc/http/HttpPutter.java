/**
 * 
 */
package com.asurion.spc.http;

import java.util.Map;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

/**
 * @author djpichay
 *
 */
public class HttpPutter {

	private HttpPutter() {}

	public static HttpClientResponse put(HttpClientRequest request) {

		HttpPut httpPut = new HttpPut(request.getUrl());
		Map<String, String> headers = request.getHeaders();
		if (null != headers) {
			headers.forEach((k, v) -> httpPut.addHeader(k, v));
		}
		if (null != request.getBody()) {
			httpPut.setEntity(new StringEntity(request.getBody(), request.getContentType()));
		}

		return HttpUtil.request(httpPut);
	}
	
}
