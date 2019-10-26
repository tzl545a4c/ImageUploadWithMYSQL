package WebServer.PostRequestHandle;

import System.Config.SystemBasicConfig;

public class GetTypeList {
	
	public GetTypeList() {
		
	}
	
	public String doGet() {
		String TypeList = SystemBasicConfig.getSceneTypeList().toString();
		
		TypeList = TypeList.substring(1, TypeList.length() - 1);
		return TypeList;
	}
}
