package Tools;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

import com.sun.management.OperatingSystemMXBean;

public class SystemResources 
{
	private String ResourcesValue = "";
	private int    TryTimes = 100;
	
	public String Get() throws InterruptedException
	{
		OperatingSystemMXBean OSMB = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		DecimalFormat DF = new DecimalFormat("#.##");
		
		this.ResourcesValue += OSMB.getAvailableProcessors() + ", ";
		
		for(int i = 0; i < this.TryTimes; i++)
		{
			double CPUUsage = OSMB.getSystemCpuLoad();
			if(CPUUsage > 0.0)
			{ this.ResourcesValue += DF.format(CPUUsage * 100) + ", "; break; }
			else if(i == this.TryTimes - 1)
			{ this.ResourcesValue += "null, "; }
			Thread.sleep(100);
		}
		
		this.ResourcesValue += OSMB.getFreePhysicalMemorySize() / 1024 / 1024 + ", ";
		this.ResourcesValue += (OSMB.getTotalPhysicalMemorySize() - OSMB.getFreePhysicalMemorySize()) / 1024 / 1024 + ", ";
		this.ResourcesValue += OSMB.getTotalPhysicalMemorySize() / 1024 / 1024 + ", ";
		
		File[] RootPath = File.listRoots();
        for(File FilePath : RootPath) 
        {
        	if(FilePath.getTotalSpace() > 0)
        	{
        		this.ResourcesValue += FilePath.getPath() + ", "
        							 + FilePath.getFreeSpace() / 1024 / 1024 / 1024 + ", "
        							 + FilePath.getUsableSpace() / 1024 / 1024 / 1024 + ", "
        							 + FilePath.getTotalSpace() / 1024 / 1024 / 1024 + ", ";
        	}
        }
        
        this.ResourcesValue = this.ResourcesValue.substring(0, this.ResourcesValue.length() - 2);
		
		return this.ResourcesValue;
	}
}
