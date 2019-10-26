package CallFunction;

import java.util.ArrayList;
import java.util.Map;

import MySQL.CallFunciton.MySQLTableQuery;
import System.Config.SystemBasicConfig;

public class SceneTypeCheck {

	public SceneTypeCheck() {
		
	}
	
	public void doCheck() {
		ArrayList<String> SceneTypeList = new ArrayList<String>();
		ArrayList<Map<String, Object>> QueryResultList = new ArrayList<Map<String, Object>>();
		
		QueryResultList.addAll(new MySQLTableQuery("wrapper", "scene_type", "name", "").doQuery());
		
		for(Map<String, Object> QueryResult : QueryResultList) {
			SceneTypeList.add(QueryResult.get("name").toString());
		}
		
		SystemBasicConfig.setSceneTypeList(SceneTypeList);
	}
}
