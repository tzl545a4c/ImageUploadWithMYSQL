package Tools;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteArrayObjectConvert 
{
	public ByteArrayObjectConvert() { }
	
	public byte[] toByteArray(Object _Object)
	{
		byte[] ByteArray = null;
		
		try 
		{
			ByteArrayOutputStream ByteArrayOS = new ByteArrayOutputStream();
			ObjectOutputStream ObjectOS = new ObjectOutputStream(ByteArrayOS);
			ObjectOS.writeObject(_Object);
			ByteArray = ByteArrayOS.toByteArray(); 
			return ByteArray;
		} 
		catch (IOException e) { return null; }
	}
	
	public Object toObject(byte[] _ByteArray)
	{		
		Object Object = null;
		
		try 
		{
			ByteArrayInputStream ByteArrayIS = new ByteArrayInputStream(_ByteArray);
			ObjectInputStream ObjectOS = new ObjectInputStream(ByteArrayIS);
			Object = ObjectOS.readObject();
			return Object;
		} 
		catch (IOException | ClassNotFoundException e) { return null; } 
	}
}
