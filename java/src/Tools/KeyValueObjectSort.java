package Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KeyValueObjectSort 
{
	private Map<String, Object> KeyValueObject = new HashMap<String, Object>(); 
	
	public KeyValueObjectSort(Map<String, Object> _KeyValueObject) 
	{
		this.KeyValueObject = _KeyValueObject;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Object> Execute(boolean ResultSwitch)
	{
		ArrayList<Object> QueueList = new ArrayList<Object>();
		ArrayList<Object> QueueList_Key = new ArrayList<Object>();
		ArrayList<Double> QueueList_Temp = new ArrayList<Double>();
		ArrayList<Object> QueueList_Index = new ArrayList<Object>();

		try
		{
	    	for(String Key : this.KeyValueObject.keySet())
	    	{
	    		Map<String, Object> ObjectTemp = new HashMap<String, Object>();
	    		ObjectTemp.put(Key, this.KeyValueObject.get(Key));
	    		QueueList.add(ObjectTemp);
	    		QueueList_Key.add(Key);
			}
			
	    	for(Object Value : this.KeyValueObject.values())
	    	{ QueueList_Temp.add((Double) Value); }
	    	
	    	Collections.sort(QueueList_Temp);
	    	QueueList_Index.addAll(QueueList_Temp);
	    	
	    	for(int i = 0; i < QueueList_Index.size(); i++)
	    	{
	    		Map<String, Object> ObjectTemp = (Map<String, Object>) QueueList.get(i);
	    		String Key = ObjectTemp.keySet().toString();
	    		Key = Key.toString().substring(1, Key.length() - 1);
	    		int ValueIndex = QueueList_Index.indexOf(ObjectTemp.get(Key));
	    		
	    		QueueList_Index.set(ValueIndex, ObjectTemp);
	    		QueueList_Key.set(ValueIndex, Key);
	    	}

	    	QueueList = QueueList_Index;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(ResultSwitch)
		{ return QueueList; }
		else
		{ return QueueList_Key; }
	}
}
