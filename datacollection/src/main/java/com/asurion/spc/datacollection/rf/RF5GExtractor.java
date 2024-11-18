package com.asurion.spc.datacollection.rf;

public class RF5GExtractor implements LogExtractor<RF5GBean> {

private static RF5GExtractor instance;
	
	static {
		instance = new RF5GExtractor ();
	}
	
	public static  RFBean<RF5GBean> extractBean(String[] filecontent) {
		
		return instance.extract(filecontent);
	}
		
	@Override
	public RFBean<RF5GBean> extract(String[] fileContent) {
		RFBean<RF5GBean> bean = new RFBean <>();
		
		boolean boolFoundn261 = false;
		boolean boolFoundB13 = false;
		boolean boolFoundB4 = false;
		boolean boolFoundB5 = false;
		boolean boolFoundB2 = false;

		RF5GBean rf5GBean = new RF5GBean();
		for (int i = 0; i < fileContent.length; i++) {

			if (fileContent[i].contains("Date:")) {
				rf5GBean.setDate(fileContent[i].substring(fileContent[i].indexOf("Date:") +5, 18).trim());
			}
			
			if (fileContent[i].contains("Time:")) {
				rf5GBean.setTime(fileContent[i].substring(fileContent[i].indexOf("Time:") + 5, 61).trim());
			}

			if (fileContent[i].contains("Test Equipment:")) {
				rf5GBean.setEquipment(fileContent[i].substring(fileContent[i].indexOf(":") + 1).trim());
			}

			if (fileContent[i].contains("Model:")) {
				rf5GBean.setModel(fileContent[i].substring(fileContent[i].indexOf(":") + 1).trim());
			}

			if (fileContent[i].contains("Dec IMEI:")) {
				rf5GBean.setImei(fileContent[i].substring(fileContent[i].indexOf(":") + 1).trim());
			}

			if (fileContent[i].contains("Band=n261") && !boolFoundn261) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolFoundn261 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rf5GBean.setN261MaxPower(tmpElement[tmpElement.length - 2]);
			}

			if (fileContent[i].contains("Band=B13") && !boolFoundB13) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolFoundB13 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rf5GBean.setB13MaxPower(tmpElement[tmpElement.length - 2]);
			}

			if (fileContent[i].contains("Band=B4") && !boolFoundB4) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolFoundB4 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rf5GBean.setB4MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			if (fileContent[i].contains("Band=B5") && !boolFoundB5) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolFoundB5 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rf5GBean.setB5MaxPower(tmpElement[tmpElement.length - 2]);
			}
			
			if (fileContent[i].contains("Band=B2") && !boolFoundB2) {
				while (!fileContent[i].contains("Max Power (dBm)")) {
					i++;
				}
				boolFoundB2 = true;
				String[] tmpElement = fileContent[i].trim().split("\\s");
				rf5GBean.setB2MaxPower(tmpElement[tmpElement.length - 2]);
			}
		}
		bean.setRawJson(rf5GBean);
		return bean;
	}
}
