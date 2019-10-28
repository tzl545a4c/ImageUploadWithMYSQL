package CallFunction;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import MySQL.CallFunciton.MySQLTableQuery;
import System.Config.SystemBasicConfig;

public class ScenesIDCheck {

	public ScenesIDCheck() {
		
	}
	
	public void doCheck() {
		ArrayList<Integer> ScenesIDList = new ArrayList<Integer>();
		ArrayList<Map<String, Object>> QueryResultList = new ArrayList<Map<String, Object>>();
		
		QueryResultList.addAll(new MySQLTableQuery("wrapper", "scenes", "id", "").doQuery());
		
		for(Map<String, Object> QueryResult : QueryResultList) {
			ScenesIDList.add((Integer) QueryResult.get("id"));
		}
		
		Collections.sort(ScenesIDList);
		SystemBasicConfig.setSceneIDMaximum(ScenesIDList.get(ScenesIDList.size() - 1));
		System.out.println("Current Scene ID: " + SystemBasicConfig.getSceneIDMaximum());
	}
}
