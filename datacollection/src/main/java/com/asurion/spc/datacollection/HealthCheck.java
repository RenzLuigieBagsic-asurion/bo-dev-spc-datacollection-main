package com.asurion.spc.datacollection;

import com.asurion.spc.http.HttpClientRequest;
import com.asurion.spc.http.HttpClientResponse;
import com.asurion.spc.http.HttpGetter;
import com.asurion.spc.util.PropertyUtil;

public class HealthCheck {
		
	public static boolean isHealthy() {

		HttpClientRequest httpClientRequest = new HttpClientRequest(PropertyUtil.getPropertyValue("healthcheck.getEP"));
		HttpClientResponse httpClientResponse = HttpGetter.get(httpClientRequest);
		if (httpClientResponse != null && httpClientResponse.getStatusCode() == 200) {
			return true;
    	}
		
		return false;

	}
}