package System.Config;

import java.util.ArrayList;

public class SystemBasicConfig {

	private static int SceneIDMaximum = 0;
	private static int SceneTypeIDMaximum = 0;
	private static ArrayList<String> SceneTypeList = new ArrayList<String>();

	public static void setSceneIDMaximum(int SceneIDMaximum) {
		SystemBasicConfig.SceneIDMaximum = SceneIDMaximum;
	}
	
	public static int getSceneIDMaximum() {
		return SystemBasicConfig.SceneIDMaximum;
	}

	public static void setSceneTypeIDMaximum(int SceneTypeIDMaximum) {
		SystemBasicConfig.SceneTypeIDMaximum = SceneTypeIDMaximum;
	}
	
	public static int getSceneTypeIDMaximum() {
		return SystemBasicConfig.SceneTypeIDMaximum;
	}
	
	public static void setSceneTypeList(ArrayList<String> SceneTypeList) {
		SystemBasicConfig.SceneTypeList.clear();
		SystemBasicConfig.SceneTypeList.addAll(SceneTypeList);
	}
	
	public static ArrayList<String> getSceneTypeList() {
		return SystemBasicConfig.SceneTypeList;
	}
}
