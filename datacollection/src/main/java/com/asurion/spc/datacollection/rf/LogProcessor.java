package com.asurion.spc.datacollection.rf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import com.asurion.spc.datacollection.App;
import com.asurion.spc.datacollection.RF;
import com.asurion.spc.http.HttpClientRequest;
import com.asurion.spc.http.HttpClientResponse;
import com.asurion.spc.http.HttpPoster;
import com.asurion.spc.util.FileHandling;
import com.asurion.spc.util.JsonUtil;
import com.asurion.spc.util.PropertyUtil;
import com.asurion.spc.util.StringUtil;

public class LogProcessor {

	private static boolean isValid;

	
	public static String timeStamp = LocalDate.now() + " " +LocalTime.now() + " > ";
	
	public static String writeAppLog(String data) {
		
		try {
			FileHandling.write(new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + 
					"\\logs\\App_"  + LocalDate.now().toString().replace("-","")+
					".log" ,LocalTime.now().withNano(0).toString() + " > " +data +"\n",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return LocalTime.now().withNano(0).toString() + " > " + data;
	}
	
	
	private static boolean isGU(String fileName, String[] list) {
		for (int i = 0; i < list.length; i++) {
			if (fileName.contains(list[i])) {
				return true;
			}
		}
		return false;
	}

	private static String archiveDirPath(String destPath, String fileName, boolean isGU) {

		String tmpName = fileName.substring(fileName.length() - 18);
		String unitType;
		if (isGU) {
			unitType = "\\GU";
		} else {
			unitType = "\\NonGU";
		}

		String subFolder = unitType + "\\" + tmpName.substring(4, 8) + "\\" + tmpName.substring(2, 4) + "\\"
				+ tmpName.substring(0, 2);

		return destPath + subFolder;
	}

	
	private static String archiveInvalidLogs(String destPath) {
		String unitType = "\\Invalid";
		return destPath + unitType;
	}
	
	
	private static String archiveAbortProcess(String destPath) {
		String unitType = "\\GU\\Abort";
		return destPath + unitType;
	}

	
	private static String archiveFailedProcess(String destPath) {
			String unitType = "\\GU\\Failed";
			return destPath + unitType;
		}


	private static String[] fmt5GData(final RFBean<RF5GBean> rfBean) {
		String[] jsonStrings = new String[256];
		
		int x = 0;
		rfBean.setQual_run(false);
		rfBean.setUser("SYSTEM");

		for (int i = 0; i < RF.parameterSetup.length; i++) {
			
			
			if((rfBean.getRawJson().getImei()).equals(RF.parameterSetup[i].getImei())) {	
				
				rfBean.setParametersetup_id(RF.parameterSetup[i].id);
				rfBean.setImei(RF.parameterSetup[i].getImei());

				if (RF.parameterSetup[i].getParameter_name().contains("BANDN261")
						&& rfBean.getRawJson().getN261MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getN261MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("BANDB4")
						&& rfBean.getRawJson().getB4MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getB4MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("BANDB13")
						&& rfBean.getRawJson().getB13MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getB13MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("BANDB5")
						&& rfBean.getRawJson().getB5MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getB5MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("BANDB2")
						&& rfBean.getRawJson().getB2MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getB2MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}
			}
		}
		
		jsonStrings = new HashSet<>(Arrays.asList(jsonStrings)).toArray(new String[0]);
		List<String> list = new ArrayList<>();
		 for(String s : jsonStrings) {
		       if(s != null && s.length() > 0) {
		          list.add(s);
		       }
		    }
		 return   list.toArray(new String[list.size()]);
		
		
		
		//return jsonStrings;
	}

	private static String[] fmtLTEData(final RFBean<RFLTEBean> rfBean) {
		//String[] jsonStrings = new String[9];
		String[] jsonStrings =   new String[256];
		int x = 0;
		rfBean.setQual_run(false);
		rfBean.setUser("SYSTEM");

		for (int i = 0; i < RF.parameterSetup.length; i++) {
			if((rfBean.getRawJson().getImei()).equals(RF.parameterSetup[i].getImei())) {	
				
				rfBean.setParametersetup_id(RF.parameterSetup[i].id);
				rfBean.setImei(RF.parameterSetup[i].getImei());

				if (RF.parameterSetup[i].getParameter_name().contains("LTE_23230")
						&& rfBean.getRawJson().getLte23230MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getLte23230MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("LTE_20525")
						&& rfBean.getRawJson().getLte20525MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getLte20525MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("LTE_18900")
						&& rfBean.getRawJson().getLte18900MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getLte18900MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("LTE_20175")
						&& rfBean.getRawJson().getLte20175MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getLte20175MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("CDMA2000_384_MS")
						&& rfBean.getRawJson().getCdma384MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getCdma384MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("CDMA2000_135_MS")
						&& rfBean.getRawJson().getCdma135MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getCdma135MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("EVDO_475")
						&& rfBean.getRawJson().getEvdo475MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getEvdo475MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("EVDO_199")
						&& rfBean.getRawJson().getEvdo199MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getEvdo199MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}

				if (RF.parameterSetup[i].getParameter_name().contains("EVDO_25")
						&& rfBean.getRawJson().getEvdo25MaxPower() != null) {
					rfBean.setData_value(Double.parseDouble(rfBean.getRawJson().getEvdo25MaxPower()));
					jsonStrings[x] = JsonUtil.writeAsString(rfBean);
					x++;
				}
			}
		}
		
		
		jsonStrings = new HashSet<>(Arrays.asList(jsonStrings)).toArray(new String[0]);
		List<String> list = new ArrayList<>();
		 for(String s : jsonStrings) {
		       if(s != null && s.length() > 0) {
		          list.add(s);
		       }
		    }
		 return   list.toArray(new String[list.size()]);
		
		//return jsonStrings;
	}

	private static HttpClientResponse postRFLogs(String body) {

		HttpClientRequest httpClientRequest = new HttpClientRequest(PropertyUtil.getPropertyValue("spc.rf_postEP"));
		httpClientRequest.addHeader("x-asul-pk", PropertyUtil.getPropertyValue("x-asul-pk"));
		httpClientRequest.addHeader("x-asul-sk", PropertyUtil.getPropertyValue("x-asul-sk"));
		httpClientRequest.addHeader("Content-Type", "application/json");
		httpClientRequest.setBody(body);
		return HttpPoster.post(httpClientRequest);
	}

public static boolean fileCheck(String srcPath, String fileName, String findContent) {
		boolean retVal = false;
		String tmpName = fileName.substring(fileName.length() - 18);

		if(tmpName.substring(tmpName.length() - 4).equals(".txt") && StringUtil.isNumeric(tmpName.substring(0,tmpName.length() - 4 ))) {
			retVal = true;
		}
		
		if (findContent!=null) {
			try (Scanner scanner = new Scanner(new File(srcPath +"\\"+fileName))) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
				    if(line.toUpperCase().contains(findContent.toUpperCase())) {
				    	retVal= true;
				    	break;
				    }
					retVal= false;
				}
			} catch (FileNotFoundException e) {
				
				System.out.println("exception : "  + e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	
		return retVal;
	}
	
	
	
	//public static void process(String srcPath, String destPath, String[] fileList, String[] guList,String[] machSNList) {
		public static void process(String srcPath, String destPath, String[] fileList) {
			String[] fLogs =null;
			String archiveProcessFailed = archiveFailedProcess(destPath);
			if(Files.exists(Paths.get(archiveProcessFailed))) {
				fLogs =FileHandling.list(archiveProcessFailed);
				
			}
		
		if((fLogs == null ||fLogs.length == 0) && (fileList==null || fileList.length == 0 )) {
			return;
	    }
		
		String[] guList =  RF.getGU();
		String[] machSNList=RF.getMachSerial();
		String[] json = new String[3];
		String archivePath = null;
		
		String archiveProcessAbort = archiveAbortProcess(destPath);
		String archiveLogsInvalid=archiveInvalidLogs(destPath);
		
		for (int i = 0; i < fileList.length; i++) {
			boolean boolGU=isGU(fileList[i],guList);
			// RF log filename format checking
			if(LogProcessor.fileCheck(srcPath,fileList[i] , null)) {
				isValid = true;
				archivePath = archiveDirPath(destPath, fileList[i], boolGU);
			}else {
				isValid = false;
				archivePath= archiveLogsInvalid;
			}
		
			if (boolGU && isValid) {
				String fileContent[] = FileHandling.read(srcPath + "\\" + fileList[i]);
				
				if(LogProcessor.fileCheck(srcPath,fileList[i] , "Abort")) {
					isValid = false;
					archivePath= archiveProcessAbort;
	
				}else {
					isValid = true;
					archivePath= archiveDirPath(destPath, fileList[i], true);
				}
				
				if (isValid) {
				
					for (int j = 0; j < machSNList.length; j++) {
				
						if(LogProcessor.fileCheck(srcPath,fileList[i] , machSNList[j])) {
							isValid = true;
							archivePath= archiveDirPath(destPath, fileList[i], true);
							break;
						}
						
						if(j==machSNList.length-1) {
							isValid = false;
							archivePath =archiveDirPath(destPath, fileList[i], false);
						}
						
					}
			
				}
				if (isValid) {
					if (Arrays.asList(fileContent).contains("M4 for Anritsu Equipment") ) {
						json = fmt5GData(RF5GExtractor.extractBean(fileContent));
					} else if (Arrays.asList(fileContent).contains("M3 for Anritsu Equipment")) {
						json = fmtLTEData(RFLTEExtractor.extractBean(fileContent));
					}else {
						isValid = false;
						archivePath =archiveLogsInvalid;
						System.out.println(writeAppLog("[WARNING] Invalid Log file (No M3/M4 Identifier)  : " + fileList[i]));	
					}
				}
				
				if (isValid) {
					for (int x = 0; x < json.length; x++) {
						if (json[x] != null) {

							if (postRFLogs(json[x]).getStatusCode() != 200) {
								archivePath = archiveProcessFailed;
							}
						
							
						}
					}

				}
				
				
				
				
				
				
			}
			FileHandling.createFolder(archivePath);
			FileHandling.move(srcPath + "\\" + fileList[i], archivePath + "\\" + fileList[i]);
		}
		//Check and Re-process failed logs
		if(fLogs!= null) {
			
			for(int j=0;j<fLogs.length;j++) {
				FileHandling.move(archiveProcessFailed +"\\" + fLogs[j],srcPath+"\\" + fLogs[j]);
			}
		}
		
		//			if(Files.exists(Paths.get(archiveProcessFailed))) {
//			String[] fLogs =FileHandling.list(archiveProcessFailed);
//			if(fLogs!= null) {
//				
//				for(int j=0;j<fLogs.length;j++) {
//					FileHandling.move(archiveProcessFailed +"\\" + fLogs[j],srcPath+"\\" + fLogs[j]);
//				}
//			}
//		}
	}
}
