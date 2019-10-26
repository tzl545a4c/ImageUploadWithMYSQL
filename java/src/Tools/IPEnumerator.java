package Tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IPEnumerator 
{
	public IPEnumerator() { }
	
	public String Get()
	{
		String IPList = "";
		
		try 
		{
			Enumeration<NetworkInterface> ENI = NetworkInterface.getNetworkInterfaces();
			InetAddress IP = null;

			while(ENI.hasMoreElements()) 
			{
				NetworkInterface NI = ENI.nextElement();
				Enumeration<InetAddress> EIP = NI.getInetAddresses();
				
				while(EIP.hasMoreElements())
				{
					IP = EIP.nextElement();
					if(!IP.isLoopbackAddress() & !IP.getHostAddress().contains(":")) 
					{ IPList += IP.getHostAddress() + "\r\n"; }
				}
			}
		} 
		catch (Exception e) 
		{ e.printStackTrace(); }
		
		return IPList;
	}
}
