package com.asurion.spc.datacollection;

import com.asurion.spc.datacollection.rf.LogProcessor;
import com.asurion.spc.http.HttpClientRequest;
import com.asurion.spc.http.HttpPoster;
import com.asurion.spc.util.PropertyUtil;

public class HeartBeat implements Runnable {

	@Override
	public void run() {
		while (true) {
			sendRequest();
			try {
				Thread.sleep(Long.parseLong(PropertyUtil.getPropertyValue("heartbeat.interval")));
			} catch (InterruptedException e) {
				System.out.println(LogProcessor.writeAppLog("[EXCEPTION ERROR] " + e.getMessage()));
				
				//System.out.println(LogProcessor.timeStamp +  e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private static void sendRequest() {
		
		HttpClientRequest httpClientRequest = new HttpClientRequest(PropertyUtil.getPropertyValue("heartbeat.postEP"));
		httpClientRequest.addHeader("x-asul-pk", PropertyUtil.getPropertyValue("x-asul-pk"));
		httpClientRequest.addHeader("x-asul-sk", PropertyUtil.getPropertyValue("x-asul-sk"));
		httpClientRequest.addHeader("Content-Type", "application/json");
		HttpPoster.post(httpClientRequest);
	}

}
