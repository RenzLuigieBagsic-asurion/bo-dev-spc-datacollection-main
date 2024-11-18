package com.asurion.spc.datacollection.rf;

public interface LogExtractor<T> {
	
	 RFBean<T> extract(String fileContent[]);
}
 