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
			sizeOfFiles.put(extension,Long.valueOf(0));
			numberOfFiles.put(extension, Long.valueOf(0));
		}
		
		printFileSizeAndCount(workingDirectory);
		
		for(String type:sizeOfFiles.keySet())
		{			
			System.out.print(type);
			String formatSize = String.format("%,d", (sizeOfFiles.get(type)));
			System.out.println(numberOfFiles.get(type) + " " + formatSize);
		}
		
		long endTime = System.currentTimeMillis();
		long netTime = endTime - startTime;
		System.out.println("Program executed in " + netTime + " milliseconds");
	}
	
	/** e) Gives the total size of all the files represented by an extension */
	
	public static void printFileSizeAndCount(File f)
	{
		long size=0;
		File[] path = f.listFiles();
		
			for(File file:path)
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
