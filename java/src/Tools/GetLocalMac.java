package Tools;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GetLocalMac 
{
	private String MAC = "";
	private InetAddress IP = null;
	private NetworkInterface NI = null;
	
	public GetLocalMac() { }
	
	public String Get(String _IP) throws SocketException, UnknownHostException
	{
		this.IP = InetAddress.getByName(_IP);
		this.NI = NetworkInterface.getByInetAddress(this.IP);
		
		for(int i = 0; i < this.NI.getHardwareAddress().length; i++)
		{ this.MAC += String.format("%02X%s", this.NI.getHardwareAddress()[i], (i < this.NI.getHardwareAddress().length - 1) ? "-" : ""); }
		
		return this.MAC;
	}
}
