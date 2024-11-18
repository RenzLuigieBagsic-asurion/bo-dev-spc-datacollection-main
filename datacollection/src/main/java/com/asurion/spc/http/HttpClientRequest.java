/**
 * 
 */
package com.asurion.spc.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.ContentType;

/**
 * @author djpichay
 *
 */
public class HttpClientRequest {

	private String url;
	private String body;
	private ContentType contentType;
	private Map<String, String> headers;
	
	public HttpClientRequest (final String url) {
		this.url = url;
		this.headers = new HashMap<>();
	}
	
	public HttpClientRequest addHeader(final String name, final String value) {
		this.headers.put(name, value);
		return this;
	}
	
	public HttpClientRequest setBody(String newBody) {
		this.body = newBody;
		return this;
	}
	
	public HttpClientRequest setContentType(ContentType newContentType) {
		this.contentType = newContentType;
		return this;
	}
	
	public String getUrl() {
		return this.url;
	}

	public String getBody() {
		return this.body;
	}

	public ContentType getContentType() {
		return this.contentType;
	}

	public Map<String, String> getHeaders() {
		return this.headers;
	}
	
}
