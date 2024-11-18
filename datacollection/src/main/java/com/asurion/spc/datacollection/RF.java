package com.asurion.spc.datacollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.asurion.spc.datacollection.rf.LogProcessor;
import com.asurion.spc.datacollection.rf.ParameterSetup;
import com.asurion.spc.datacollection.rf.ParameterSetupExtactor;
import com.asurion.spc.util.FileHandling;
import com.asurion.spc.util.PropertyUtil;
import com.asurion.spc.util.StringUtil;

public class RF {
	public static ParameterSetup[] parameterSetup;


	
	public static String[] getGU() {
		parameterSetup = ParameterSetupExtactor.extract(ParameterSetupExtactor.getParameterSetup());
		String[] tmp = new String[parameterSetup.length];
		for (int i = 0; i < parameterSetup.length; i++) {
			if(StringUtil.isNumeric(parameterSetup[i].getImei())) {
				tmp[i] = parameterSetup[i].getImei();
			}
			else {
				//System.out.println(LogProcessor.timeStamp + "Invalid Parameter! (ESN)" + parameterSetup[i].getParameter_name()   );
			
				System.out.println(LogProcessor.writeAppLog("[WARNING] Invalid Parameter! (ESN)" + parameterSetup[i].getParameter_name()));
			
			}
		}
		tmp = new HashSet<>(Arrays.asList(tmp)).toArray(new String[0]);
		List<String> list = new ArrayList<>();
		 for(String s : tmp) {
		       if(s != null && s.length() > 0) {
		          list.add(s);
		       }
		    }
		 return   list.toArray(new String[list.size()]);
	}
	
	public static String[] getMachSerial() {
		String[] tmp = new String[parameterSetup.length];
		for (int i = 0; i < parameterSetup.length; i++) {
			tmp[i] = parameterSetup[i].getSerial();
			
		}
		tmp = new HashSet<>(Arrays.asList(tmp)).toArray(new String[0]);
		List<String> list = new ArrayList<>();
		 for(String s : tmp) {
		       if(s != null && s.length() > 0) {
		          list.add(s);
		       }
		    }
		 return   list.toArray(new String[list.size()]);
	}

	public static void process() {
		final String pathSource = PropertyUtil.getPropertyValue("rf.srcPath");
		final String pathDestination = PropertyUtil.getPropertyValue("rf.destPath");
		while (true) {
			if(HealthCheck.isHealthy()) {
				String[] fileList =  FileHandling.list(pathSource);
				if(fileList==null) {
					System.out.println(LogProcessor.writeAppLog("[WARNING] Cannot access source path! Path: " + pathSource));
				}
				//LogProcessor.process(pathSource, pathDestination,fileList, getGU(),getMachSerial());
				LogProcessor.process(pathSource, pathDestination,fileList);
				
				
//				try {
//					Thread.sleep(Long.parseLong(PropertyUtil.getPropertyValue("spc.rf_inteval")));
//				} catch (NumberFormatException e) {
//					System.out.println(LogProcessor.writeAppLog(e.getMessage()));
//					//System.out.println(LogProcessor.timeStamp +  e.getMessage());
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					System.out.println(LogProcessor.writeAppLog(e.getMessage()));
//					//System.out.println(LogProcessor.timeStamp +  e.getMessage());
//					e.printStackTrace();
//				}
			}else {
				System.out.println(LogProcessor.writeAppLog("[WARNING] Health Check  : isHealhty? FALSE"));
				//System.out.println(LogProcessor.timeStamp + "Health Check  : isHealthy? FALSE");
			}
			
			try {
				Thread.sleep(Long.parseLong(PropertyUtil.getPropertyValue("spc.rf_inteval")));
			} catch (NumberFormatException | InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(LogProcessor.writeAppLog(e.getMessage()));
				e.printStackTrace();
			}
		}
	}
}
