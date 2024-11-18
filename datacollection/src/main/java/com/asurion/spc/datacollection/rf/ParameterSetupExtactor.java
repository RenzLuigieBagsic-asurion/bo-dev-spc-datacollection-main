package com.asurion.spc.datacollection.rf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.asurion.spc.http.HttpClientRequest;
import com.asurion.spc.http.HttpClientResponse;
import com.asurion.spc.http.HttpGetter;
import com.asurion.spc.util.PropertyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParameterSetupExtactor {

	public static String getParameterSetup() {

		HttpClientRequest httpClientRequest = new HttpClientRequest(PropertyUtil.getPropertyValue("spc.rf_getEP"));
		httpClientRequest.addHeader("x-asul-pk", PropertyUtil.getPropertyValue("x-asul-pk"));
		httpClientRequest.addHeader("x-asul-sk", PropertyUtil.getPropertyValue("x-asul-sk"));
		HttpClientResponse httpClientResponse = HttpGetter.get(httpClientRequest);

		if (httpClientResponse.getStatusCode() == 200) {
				return httpClientResponse.getResponse();
		}
		return null;

	}

	public static ParameterSetup[] extract(String json) {
		ParameterSetup[] rfPS = null;
		ObjectMapper mapper = new ObjectMapper();
		String tmp = null;
		
		if (json.contains("200")) {
			tmp = json.substring(28, json.length() - 1);
			try {
				rfPS =mapper.readValue(tmp, ParameterSetup[].class);
				rfPS = new HashSet<>(Arrays.asList(rfPS)).toArray(new ParameterSetup[0]);
				List<ParameterSetup> list = new ArrayList<>();
				 for(ParameterSetup s : rfPS) {
				       if(s.parameter_name.contains("_TCP-ANT-") && s.getActive() && s.getSerial() != null) {
				          list.add(s);
				       }
				    }
				rfPS=list.toArray(new ParameterSetup[list.size()]);
				for (ParameterSetup rfParamSetup : rfPS) {
					rfParamSetup.setImei( rfParamSetup.parameter_name.substring(3, 18));
				}
			}
			catch(IOException e) {
				System.out.println( LogProcessor.writeAppLog("[WARNING] " + e.getMessage()));
				//System.out.println(LogProcessor.timeStamp +  e.getMessage());
				e.printStackTrace();
			}
		}
		return rfPS;
	}



}
