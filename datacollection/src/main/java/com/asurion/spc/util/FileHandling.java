package com.asurion.spc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.asurion.spc.datacollection.rf.LogProcessor;
/**
 * @author Jessel.Buenaventura
 *
 */

public class FileHandling {
	
	private static FileWriter writer;

	public static boolean createFolder(String dirFolder) {
		File file = new File(dirFolder); 
		return file.mkdirs();
	}

	public static String[] list(String srcPath) {
		File folder = new File(srcPath);
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles == null) {
		
		return null;
		}
		List<String> lines = new ArrayList<>();
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    lines.add(listOfFiles[i].getName());
		  }
		}
		 return lines.toArray(new String[lines.size()]);
	}
	
	public static boolean rename(String oldFileName, String newFileName) {
		File oldFile = new File(oldFileName);
		File newFile = new File(newFileName);
		return oldFile.renameTo(newFile);
	}
		
	public static boolean move(String srcFile, String destFile) {
		try {
				
			Files.move(Paths.get(srcFile), Paths.get(destFile),StandardCopyOption.REPLACE_EXISTING);
		}
		catch(IOException e) {
			System.out.println(LogProcessor.writeAppLog(e.getMessage()));
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public static boolean copy(String srcFile, String destFile) {
		try {
			Files.copy(Paths.get(srcFile), Paths.get(destFile));
		}
		catch(IOException e) {
			System.out.println(LogProcessor.writeAppLog(e.getMessage()));
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean delete(String srcFile) {
		try {
			Files.delete(Paths.get(srcFile));
		}
		catch(IOException e) {
			System.out.println(LogProcessor.writeAppLog(e.getMessage()));
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String[] read(String filename){
		List<String> lines = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
			for(String line; (line = br.readLine()) != null; ) {
				lines.add(line.trim());
		    }
		}
		catch(IOException e) {
			System.out.println(LogProcessor.writeAppLog(e.getMessage()));
			e.printStackTrace();
		}
		return lines.toArray(new String[lines.size()]);
	}
	
	public static void write(String filename, String data, boolean append) throws IOException {
		
		
		File file = new File(filename);
		 
		 String subDir = file.getParent();	
		 if(subDir!=null) {
			 createFolder(subDir);
		 }
		   writer = null;
		 try {   
		       
			 writer = new FileWriter(file.getAbsolutePath(),append);
		        writer.write(data);
		    } catch (IOException e) {
		    	System.out.println(LogProcessor.writeAppLog(e.getMessage()));
		    	e.printStackTrace(); 
		    } finally {
		        if (writer != null)
					writer.close();
		    }
	}
	
}
