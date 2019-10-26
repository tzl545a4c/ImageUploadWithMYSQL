package Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FileSortByDate 
{
	private ArrayList<String> FileList = new ArrayList<String>();
	private String FilePath = "";
	
	public FileSortByDate(String _FilePath)
	{
		this.FilePath = _FilePath;
	}
	
	public ArrayList<String> Sort()
	{
		Map<Long, String> FileDate = new HashMap<Long, String>();
		ArrayList<Long> DateList = new ArrayList<Long>(); 
		
		try
		{
			if(!this.FilePath.endsWith("/") & !this.FilePath.endsWith("\\")) { this.FilePath += "/"; }
			
			for(String FileName : new File(this.FilePath).list())
			{ FileDate.put(new File(this.FilePath + FileName).lastModified(), FileName); }
			
			DateList.addAll(FileDate.keySet());
			Collections.sort(DateList);
			
			for(Long Data : DateList)
			{ this.FileList.add(FileDate.get(Data)); }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return this.FileList;
	}
}
