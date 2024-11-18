/**
 * 
 */
package com.asurion.spc.http;

import java.util.Map;

import org.apache.http.entity.StringEntity;

/**
 * @author djpichay
 *
 */
public class HttpDeleter {

	private HttpDeleter() {}

	public static HttpClientResponse delete(HttpClientRequest request) {

		HttpDelete httpDelete = new HttpDelete(request.getUrl());
		Map<String, String> headers = request.getHeaders();
		if (null != headers) {
			headers.forEach((k, v) -> httpDelete.addHeader(k, v));
		}
		if (null != request.getBody()) {
			httpDelete.setEntity(new StringEntity(request.getBody(), request.getContentType()));
		}
		
		return HttpUtil.request(httpDelete);
	}
	
}
