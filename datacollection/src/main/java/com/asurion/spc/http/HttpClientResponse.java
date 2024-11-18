/**
 * 
 */
package com.asurion.spc.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author djpichay
 *
 */
public class HttpClientResponse {

	private int statusCode;
	private String response;
	private Map<String,String> headers = new HashMap<>();

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return this.statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the response
	 */
	public String getResponse() {
		return this.response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * 
	 * @param headerName
	 * @return
	 */
	public String getHeader(String headerName) {
		return this.headers.get(headerName);
	}
	/**
	 * 
	 * @param headerName
	 * @param headerValue
	 */
	public void addHeader(String headerName, String headerValue) {
		this.headers.put(headerName, headerValue);
	}
	
	public boolean isGood() {
		return this.statusCode > 199 && this.statusCode < 300;
	}
	
}
