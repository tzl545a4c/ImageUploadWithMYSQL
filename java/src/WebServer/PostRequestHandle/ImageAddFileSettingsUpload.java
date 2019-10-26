package WebServer.PostRequestHandle;

import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import FileUpload.Config.FileUploadPathConfig;
import MySQL.CallFunciton.MySQLTableDataAdd;
import MySQL.CallFunciton.MySQLTableQuery;
import MySQL.Config.ScenesTableModel;
import System.Config.SystemBasicConfig;
import Tools.GetSystemDateTime;
import Tools.TypeNameConvert;

public class ImageAddFileSettingsUpload {

	private byte[] PostData = null;

	public ImageAddFileSettingsUpload() {

	}

	public ImageAddFileSettingsUpload(byte[] PostData) {
		this.PostData = PostData;
	}

	public String doUpload() {
		String FileSettings = "";
		String SettingsDataValue = "";
		String UploadPicturePath = new File(FileUploadPathConfig.getUploadFilePath()).getAbsolutePath() + "/";
		Map<String, Object> FileSettingsObject = new HashMap<String, Object>();
		Map<String, Object> FileTypeSettingsObject = new HashMap<String, Object>();

		// Decode Data with UTF8
		try {
			FileSettings = URLDecoder.decode(new String(this.PostData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FileSettings = FileSettings.subSequence(1, FileSettings.length() - 1).toString();

		// Create Field with Model
		for (Field DataName : ScenesTableModel.class.getDeclaredFields()) {
			FileSettingsObject.put(DataName.getName(), new TypeNameConvert(DataName.getType().getSimpleName(), "").doConvert());
		}

		for (String DataName : FileSettingsObject.keySet()) {
			String SplitDataName = "\"" + DataName + "\":\"";

			if (FileSettings.contains(SplitDataName)) {
				String SplitDataValue = FileSettings.split(SplitDataName)[1].split("\"")[0];
				FileSettingsObject.replace(DataName, new TypeNameConvert(DataName, SplitDataValue).doConvert());
			}
		}
		FileTypeSettingsObject.putAll(new MySQLTableQuery("wrapper", "scene_type", "*", "`name` = \"" + FileSettingsObject.get("type") + "\"").doQuery().get(0));
		SystemBasicConfig.setSceneIDMaximum(SystemBasicConfig.getSceneIDMaximum() + 1);
		FileSettingsObject.replace("id", SystemBasicConfig.getSceneIDMaximum());
		FileSettingsObject.replace("home_id", FileTypeSettingsObject.get("home_id"));
		FileSettingsObject.replace("scene_type_id", FileTypeSettingsObject.get("id"));
		FileSettingsObject.replace("component_img", UploadPicturePath + FileSettingsObject.get("home_id") + "-" + FileSettingsObject.get("card_img").toString());
		FileSettingsObject.replace("card_img", UploadPicturePath + FileSettingsObject.get("card_img").toString());
		FileSettingsObject.replace("created_at", new GetSystemDateTime().CustomGet("yyyy-MM-dd HH:mm:ss"));
		FileSettingsObject.replace("updated_at", new GetSystemDateTime().CustomGet("yyyy-MM-dd HH:mm:ss"));

		for (Field DataName : ScenesTableModel.class.getDeclaredFields()) {
			if (DataName.getType().getSimpleName().toLowerCase().equals("string") | DataName.getType().getSimpleName().toLowerCase().equals("timestamp")) {
				SettingsDataValue += "'" + FileSettingsObject.get(DataName.getName()) + "', ";
			} else if (DataName.getType().getSimpleName().toLowerCase().equals("double")) {
				SettingsDataValue += new BigDecimal(Double.parseDouble(FileSettingsObject.get(DataName.getName()).toString())).toPlainString() + ", ";
			} else {
				SettingsDataValue += FileSettingsObject.get(DataName.getName()) + ", ";
			}
		}
		SettingsDataValue = SettingsDataValue.substring(0, SettingsDataValue.length() - 2).replaceAll("\\\\", "/");

		if (!new MySQLTableDataAdd("wrapper", "scenes", SettingsDataValue).doAdd()) {
			SystemBasicConfig.setSceneIDMaximum(SystemBasicConfig.getSceneIDMaximum() - 1);
		}
		return "OK";
	}
}