/**
 * 
 */
package com.asurion.spc.http;

import java.io.IOException;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


import com.asurion.spc.datacollection.rf.LogProcessor;

/**
 * @author djpichay
 *
 */
public class HttpUtil {

//	private final static Logger logger = LogManager.getLogger(HttpUtil.class);

	private HttpUtil() {}

	public static String constructURL(final String host, final String api, Map<String, String> params) {
		String apiUrl;

		StringBuffer sb = new StringBuffer(host);
		if (null != api) {
			sb.append(api);
		}
		StringJoiner sj = new StringJoiner("&");
		if (null != params && !params.isEmpty()) {
			sb.append("?");
			for (String key : params.keySet()) {
				String value = params.get(key);
				if (null == value) {
					continue;
				}
				sj.add(key + "=" + value);
			}
			apiUrl = sb.append(sj.toString()).toString();
		} else {
			apiUrl = sb.toString();
		}

		return apiUrl;
	}

	public static HttpClientResponse request(HttpRequestBase httpRequest) {

		try (CloseableHttpClient httpclient = HttpClientFactory.getHttpClient()) {

			HttpClientResponse clientResponse = new HttpClientResponse();
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws IOException {
					HttpEntity entity = response.getEntity();
					int statusCode = response.getStatusLine().getStatusCode();
					clientResponse.setStatusCode(statusCode);
					
					for (Header header : response.getAllHeaders()) {
						clientResponse.addHeader(header.getName(), header.getValue());
					}
					
					String responseBody = entity != null ? EntityUtils.toString(entity) : null;
					return responseBody;
				}
			};

			clientResponse.setResponse(httpclient.execute(httpRequest, responseHandler));

			if (!clientResponse.isGood()) {
				System.out.println(LogProcessor.writeAppLog("[ERROR] Response code: " + clientResponse.getStatusCode() + " from " + httpRequest.getURI()));
				
				//logger.error("Got response code: [{}] from [{}]", ""+clientResponse.getStatusCode(), httpRequest.getURI());
			
			
			} else {
				System.out.println(LogProcessor.writeAppLog("[INFO] Got HTTP: " +clientResponse.getStatusCode() + " from " + httpRequest.getURI()));
				
				//logger.info("Got HTTP: [{}] from [{}]", ""+clientResponse.getStatusCode(), httpRequest.getURI());
			}

			return clientResponse;
			
		} catch (Exception e) {
			
			System.out.println(LogProcessor.writeAppLog("[EXCEPTION ERROR] " + httpRequest.getURI() + " "+ e.getMessage()));
			
			//System.out.println(LogProcessor.timeStamp + e.getMessage() + " " + httpRequest.getURI());
			//throw new ServiceException(e);
			
		}
	return null;	
	}
	
//	public static String processResponse(HttpClientResponse clientResponse) {
//		
//		if (clientResponse.isGood()) {
//			return clientResponse.getResponse();
//		}
//		
//		throw new ServiceException(new Exception("Error on processing http response"));
//		
//	}

}
