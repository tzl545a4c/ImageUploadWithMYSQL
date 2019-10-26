package WebServer.PostRequestHandle;

import java.util.Map;

import CallFunction.SceneTypeCheck;

import java.util.HashMap;
import java.io.File;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import FileUpload.Config.FileUploadPathConfig;
import MySQL.CallFunciton.MySQLTableDataAdd;
import MySQL.Config.SceneTypeTableModel;
import System.Config.SystemBasicConfig;
import Tools.GetSystemDateTime;
import Tools.TypeNameConvert;

public class TypeAddFileSettingsUpload {

	private byte[] PostData = null;
	
	public TypeAddFileSettingsUpload() {
		
	}
	
	public TypeAddFileSettingsUpload(byte[] PostData) {
		this.PostData = PostData;
	}
	
	public String doUpload() {
		String FileSettings = "";
		String SettingsDataValue = "";
		String UploadPicturePath = new File(FileUploadPathConfig.getUploadFilePath()).getAbsolutePath() + "/";
		Map<String, Object> FileSettingsObject = new HashMap<String, Object>();
		
		// Decode Data with UTF8
		try {
			FileSettings = URLDecoder.decode(new String(this.PostData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FileSettings = FileSettings.subSequence(1, FileSettings.length() - 1).toString();
		
		// Create Field with Model
		for (Field DataName : SceneTypeTableModel.class.getDeclaredFields()) {
			FileSettingsObject.put(DataName.getName(), new TypeNameConvert(DataName.getType().getSimpleName(), "").doConvert());
		}
		
		for (String DataName : FileSettingsObject.keySet()) {
			String SplitDataName = "\"" + DataName + "\":\"";
			
			if (FileSettings.contains(SplitDataName)) {
				String SplitDataValue = FileSettings.split(SplitDataName)[1].split("\"")[0];
				FileSettingsObject.replace(DataName, new TypeNameConvert(DataName, SplitDataValue).doConvert());
			}
		}

		SystemBasicConfig.setSceneTypeIDMaximum(SystemBasicConfig.getSceneTypeIDMaximum() + 1);
		FileSettingsObject.replace("id", SystemBasicConfig.getSceneTypeIDMaximum());
		FileSettingsObject.replace("home_id", 1);
		FileSettingsObject.replace("icon", UploadPicturePath + FileSettingsObject.get("home_id") + "-" + FileSettingsObject.get("id") + "-" + FileSettingsObject.get("icon").toString());
		FileSettingsObject.replace("created_at", new GetSystemDateTime().CustomGet("yyyy-MM-dd HH:mm:ss"));
		FileSettingsObject.replace("updated_at", new GetSystemDateTime().CustomGet("yyyy-MM-dd HH:mm:ss"));
		
		for (Field DataName : SceneTypeTableModel.class.getDeclaredFields()) {
			if (DataName.getType().getSimpleName().toLowerCase().equals("string") | DataName.getType().getSimpleName().toLowerCase().equals("timestamp")) {
				SettingsDataValue += "'" + FileSettingsObject.get(DataName.getName()) + "', ";
			} else {
				SettingsDataValue += FileSettingsObject.get(DataName.getName()) + ", ";
			}
		}
		SettingsDataValue = SettingsDataValue.substring(0, SettingsDataValue.length() - 2).replaceAll("\\\\", "/");

		if(new MySQLTableDataAdd("wrapper", "scene_type", SettingsDataValue).doAdd()) {
			new SceneTypeCheck().doCheck();
		} else {
			SystemBasicConfig.setSceneTypeIDMaximum(SystemBasicConfig.getSceneTypeIDMaximum() - 1);			
		}
		
		return "OK";
	}
}