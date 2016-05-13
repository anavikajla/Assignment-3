/** Go back to programming assignment 1 and optimize the performance of part (e) (only), using any techniques that might help.
The program should take 1 argument which is a path to the top level directory, under which it will compute statistics for the different types of files.

Author: Anavi Kajla
Date: 13 May, 2016 

*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class FileStatistics{
	
	static String[] TYPES = new String[]{"jpg", "png", "gif", "mp4", "mp3", "exe", "psd", "html", "xml"};
    	static Map<String, Long> sizeOfFiles = new HashMap<>();
	static Map<String, Long> numberOfFiles = new HashMap<>();
	
	public static void main(String args[])
	{		
		long startTime = System.currentTimeMillis();
		
		File workingDirectory = new File (".");
		
		for(String extension:TYPES){
			sizeOfFiles.put(extension,Long.valueOf(0)); //initializes value of hashmap for size
			numberOfFiles.put(extension, Long.valueOf(0)); //initializes value of hashmap for count
		}
		
		printFileSizeAndCount(workingDirectory); //call to method
		
		for(String type:sizeOfFiles.keySet())
		{			
			System.out.print(type);
			String formattedSize = String.format("%,d", (sizeOfFiles.get(type))); //formats the number to display with commas every three characters from the right
			System.out.println(numberOfFiles.get(type) + " " + formattedSize);
		}
		
		long endTime = System.currentTimeMillis();
		long netTime = endTime - startTime;
		System.out.println("Program executed in " + netTime + " milliseconds");
	}
	
	/** e) Gives the total size of all the files represented by an extension */
	
	public static void printFileSizeAndCount(File filename)
	{
		File[] listOfAllFiles = filename.listFiles();
		
			for(File file:listOfAllFiles)
			{
				if(file.isFile())
				{
					String filePath = file.getAbsolutePath();
					String fileExt = filePath.substring(filePath.lastIndexOf(".") + 1);
					
					for(String ext:TYPES)
					{
						if(fileExt.equals(ext))
						{
							sizeOfFiles.put(ext, (sizeOfFiles.get(ext) + (long)file.length()));
							numberOfFiles.put(ext, numberOfFiles.get(ext) + 1);
						}
					}
				}
				
				else if(file.isDirectory()) printFileSizeAndCount(file);
			}
	}
}
