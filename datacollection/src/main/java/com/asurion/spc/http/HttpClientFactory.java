/**
 * 
 */
package com.asurion.spc.http;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author djpichay
 *
 */
public class HttpClientFactory {

	private HttpClientFactory() {}

	public static CloseableHttpClient getHttpClient() throws Exception {

		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(X509Certificate[] certs, String authType) {
				//
			}

			@Override
			public void checkServerTrusted(X509Certificate[] certs, String authType) {
				//
			}

		} };

		sslContext.init(null, trustAllCerts, new SecureRandom());

		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
				NoopHostnameVerifier.INSTANCE);

		return HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
	}
	
}
