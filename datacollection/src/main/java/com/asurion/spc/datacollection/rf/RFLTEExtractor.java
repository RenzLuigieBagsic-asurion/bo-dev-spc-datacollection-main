
package com.asurion.spc.datacollection.rf;

public class RFLTEExtractor implements LogExtractor<RFLTEBean> {
	
	private static RFLTEExtractor instance;
	
	static {
		instance = new RFLTEExtractor ();
	}

	public static  RFBean<RFLTEBean> extractBean(String[] filecontent) {
		
		return instance.extract(filecontent);
	}
	
	@Override
	public RFBean<RFLTEBean> extract(String[] fileContent) {

		RFBean<RFLTEBean> bean = new RFBean <>();
				
		boolean boolLte23230 = false;
		boolean boolLte20525 = false;
		boolean boolLte18900 = false;
		boolean boolLte20175 = false;
		boolean boolCdma384 = false;
		boolean boolCdma135 = false;
		boolean boolEvdo475 = false;
		boolean boolEvdo199 = false;
		boolean boolEvdo25 = false;

		RFLTEBean rfLTEBean = new RFLTEBean();
		for (int i = 0; i < fileContent.length; i++) {

			if (fileContent[i].contains("Date:")) {
				rfLTEBean.setDate(fileContent[i].substring(fileContent[i].indexOf("Date:") +5, 18).trim());
			}
			
			if (fileContent[i].contains("Time:")) {
				rfLTEBean.setTime(fileContent[i].substring(fileContent[i].indexOf("Time:") + 5, 61).trim());
			}
			
			
			if (fileContent[i].contains("Test Equipment:")) {
				rfLTEBean.setEquipment(fileContent[i].substring(fileContent[i].indexOf(":") + 1).trim());
			}

			if (fileContent[i].contains("Model:")) {
				rfLTEBean.setModel(fileContent[i].substring(fileContent[i].indexOf(":") + 1).trim());
			}

			if (fileContent[i].contains("Dec IMEI:")) {
				rfLTEBean.setImei(fileContent[i].substring(fileContent[i].indexOf(":") + 1).trim());
			}
			
			if (fileContent[i].contains("23230: Max Power") && !boolLte23230) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolLte23230 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setLte23230MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			
			if (fileContent[i].contains("20525: Max Power") && !boolLte20525) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolLte20525 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setLte20525MaxPower(tmpElement[tmpElement.length - 2]);
				
			}
			
			if (fileContent[i].contains("18900: Max Power") && !boolLte18900) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolLte18900 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setLte18900MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			if (fileContent[i].contains("20175: Max Power") && !boolLte20175) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolLte20175 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setLte20175MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			if (fileContent[i].contains("384:MS Max Power") && !boolCdma384) {
				while (!fileContent[i].contains("Max Power")) {
					i++;
				}
				boolCdma384 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setCdma384MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			if (fileContent[i].contains("135:MS Max Power") && !boolCdma135) {
				while (!fileContent[i].contains("Max Power")) {
					i++;
				}
				boolCdma135 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setCdma135MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			if (fileContent[i].contains("475: Max Power") && !boolEvdo475) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolEvdo475 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setEvdo475MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			
			if (fileContent[i].contains("199: Max Power") && !boolEvdo199) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolEvdo199 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setEvdo199MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			if (fileContent[i].contains("25: Max Power") && !boolEvdo25) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolEvdo25 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rfLTEBean.setEvdo25MaxPower(tmpElement[tmpElement.length - 2]);
			}
		}
		bean.setRawJson(rfLTEBean);
		return bean;
	}
}
