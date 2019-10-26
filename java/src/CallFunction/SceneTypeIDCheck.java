package CallFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import MySQL.CallFunciton.MySQLTableQuery;
import System.Config.SystemBasicConfig;

public class SceneTypeIDCheck {

	public SceneTypeIDCheck() {
		
	}
	
	public void doCheck() {
		ArrayList<Integer> ScenesTypeIDList = new ArrayList<Integer>();
		ArrayList<Map<String, Object>> QueryResultList = new ArrayList<Map<String, Object>>();
		
		QueryResultList.addAll(new MySQLTableQuery("wrapper", "scene_type", "id", "").doQuery());
		
		for(Map<String, Object> QueryResult : QueryResultList) {
			ScenesTypeIDList.add((Integer) QueryResult.get("id"));
		}

		Collections.sort(ScenesTypeIDList);
		SystemBasicConfig.setSceneTypeIDMaximum(ScenesTypeIDList.get(ScenesTypeIDList.size() - 1));
	}
}
