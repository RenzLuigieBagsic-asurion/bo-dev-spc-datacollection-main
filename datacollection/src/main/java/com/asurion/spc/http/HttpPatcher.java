/**
 * 
 */
package com.asurion.spc.http;

import java.util.Map;

import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;

/**
 * @author djpichay
 *
 */
public class HttpPatcher {

	private HttpPatcher() {}

	public static HttpClientResponse patch(HttpClientRequest request) {

		HttpPatch httpPatch = new HttpPatch(request.getUrl());
		Map<String, String> headers = request.getHeaders();
		if (null != headers) {
			headers.forEach((k, v) -> httpPatch.addHeader(k, v));
		}
		if (null != request.getBody()) {
			httpPatch.setEntity(new StringEntity(request.getBody(), request.getContentType()));
		}

		return HttpUtil.request(httpPatch);
		
	}
	
}
