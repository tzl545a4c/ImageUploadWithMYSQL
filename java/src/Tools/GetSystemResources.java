package Tools;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import com.sun.management.OperatingSystemMXBean;

public class GetSystemResources 
{
	// CPU Core(s), CPU Usage(%), Memory Free(MB), Memory Usage(MB), Memory Total(MB), DiskSpace Path, DiskSpace Free(MB), DiskSpace Usage(MB), DiskSpace Total(MB) 
	private int TryTimes = 100;
	
	public GetSystemResources() { }
	
	public String Get() throws InterruptedException
	{
		String ResourcesValue = "";
		try
		{
			OperatingSystemMXBean OSMB = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
			DecimalFormat DF = new DecimalFormat("#.##");
			
			ResourcesValue += OSMB.getAvailableProcessors() + ", ";
			
			for(int i = 0; i < this.TryTimes; i++)
			{
				double CPUUsage = OSMB.getSystemCpuLoad();
				if(CPUUsage > 0.0)
				{ ResourcesValue += DF.format(CPUUsage * 100) + ", "; break; }
				else if(i == this.TryTimes - 1)
				{ ResourcesValue += "null, "; }
				Thread.sleep(100);
			}
			
			ResourcesValue += OSMB.getFreePhysicalMemorySize() / 1024 / 1024 + ", ";
			ResourcesValue += (OSMB.getTotalPhysicalMemorySize() - OSMB.getFreePhysicalMemorySize()) / 1024 / 1024 + ", ";
			ResourcesValue += OSMB.getTotalPhysicalMemorySize() / 1024 / 1024 + ", ";
			
			File[] RootPath = File.listRoots();
	        for(File FilePath : RootPath) 
	        {
	        	if(FilePath.getTotalSpace() > 0)
	        	{
	        		ResourcesValue += FilePath.getPath() + ", "
    							    + FilePath.getFreeSpace() / 1024 / 1024 + ", "
    							    + (FilePath.getTotalSpace() - FilePath.getFreeSpace()) / 1024 / 1024 + ", "
    							    + FilePath.getTotalSpace() / 1024 / 1024 + ", ";
	        	}
	        }
	        
	        ResourcesValue = ResourcesValue.substring(0, ResourcesValue.length() - 2) + ", " + ResourcesValue.split(", ")[1];
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ResourcesValue;
	}
	
	public int GetDiskSpaceFree(String AssignPath)
	{
		int DiskSpaceUsage = -1;
		
		try
		{
			File[] RootPath = File.listRoots();
	        for(File FilePath : RootPath) 
	        {
	        	if(AssignPath.equals(""))
	        	{
		        	if(FilePath.getTotalSpace() > 0)
		        	{ DiskSpaceUsage = (int) (FilePath.getFreeSpace() * 100 / FilePath.getTotalSpace()); break; }
	        	}
	        	else if(AssignPath.equals(FilePath.getPath()))
	        	{
		        	if(FilePath.getTotalSpace() > 0)
		        	{ DiskSpaceUsage = (int) (FilePath.getFreeSpace() * 100 / FilePath.getTotalSpace()); break; }
	        	}
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return DiskSpaceUsage;
	}
}
