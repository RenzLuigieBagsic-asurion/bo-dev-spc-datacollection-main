/**
 * 
 */
package com.asurion.spc.http;

import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * @author djpichay
 *
 */
public class HttpPoster {

	private HttpPoster() {}

	public static HttpClientResponse post(HttpClientRequest request) {

		HttpPost httpPost = new HttpPost(request.getUrl());
		Map<String, String> headers = request.getHeaders();
		if (null != headers) {
			headers.forEach((k, v) -> httpPost.addHeader(k, v));
		}
		if (null != request.getBody()) {
			httpPost.setEntity(new StringEntity(request.getBody(), request.getContentType()));
		}

		return HttpUtil.request(httpPost);
	}
	
}
