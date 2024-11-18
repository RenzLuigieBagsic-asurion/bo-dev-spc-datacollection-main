package com.asurion.spc.datacollection;



/**
 * @author Jessel.Buenaventura
 * 
 */

public class App {

	public static void main(String[] args) {
		
		new Thread(new HeartBeat()).start();
		RF.process();
		
	
	
	}
}
