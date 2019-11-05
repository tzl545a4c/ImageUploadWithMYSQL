package WebServer.PostRequestHandle;

import java.util.Map;
import java.util.HashMap;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import MySQL.CallFunciton.MySQLTableDataUpdate;
import MySQL.CallFunciton.MySQLTableQuery;
import MySQL.Config.ScenesTableModel;
import Tools.GetSystemDateTime;
import Tools.TypeNameConvert;

public class ImageEditFileSettingsUpload {

	private byte[] PostData = null;

	public ImageEditFileSettingsUpload() {

	}

	public ImageEditFileSettingsUpload(byte[] PostData) {
		this.PostData = PostData;
	}

	public String doUpload() {
		String FileSettings = "";
		String UpdateDataValue = "";
		String UpdataCondition = "";
		Map<String, Object> FileSettingsObjectNew = new HashMap<String, Object>();
		Map<String, Object> FileSettingsObjectQueryResult = new HashMap<String, Object>();

		// Decode Data with UTF8
		try {
			FileSettings = URLDecoder.decode(new String(this.PostData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		FileSettings = FileSettings.subSequence(1, FileSettings.length() - 1).toString();

		// Create Field with Model
		for (Field DataName : ScenesTableModel.class.getDeclaredFields()) {
			String SplitDataName = "\"" + DataName.getName() + "\":\"";

			if (FileSettings.contains(SplitDataName)) {
				String SplitDataValue = FileSettings.split(SplitDataName)[1].split("\"")[0];
				FileSettingsObjectNew.put(DataName.getName(), new TypeNameConvert(DataName.getType().getSimpleName(), SplitDataValue).doConvert());
			}
		}
		FileSettingsObjectNew.remove("card_img");
		FileSettingsObjectNew.put("updated_at", new GetSystemDateTime().CustomGet("yyyy-MM-dd HH:mm:ss"));
		FileSettingsObjectQueryResult.putAll(new MySQLTableQuery("wrapper", "scenes", "*", "`card_img` = \"" + FileSettingsObjectNew.get("card_img") + "\"").doQuery().get(0));

		for (Field DataName : ScenesTableModel.class.getDeclaredFields()) {
			if (FileSettingsObjectNew.containsKey(DataName.getName())) {
				if (DataName.getType().getSimpleName().toLowerCase().equals("string") | DataName.getType().getSimpleName().toLowerCase().equals("timestamp")) {
					UpdateDataValue += "`" + DataName.getName() + "` = '" + FileSettingsObjectNew.get(DataName.getName()) + "', ";
				} else if (DataName.getType().getSimpleName().toLowerCase().equals("double")) {
					UpdateDataValue += "`" + DataName.getName() + "` = " + new BigDecimal(Double.parseDouble(FileSettingsObjectNew.get(DataName.getName()).toString())).toPlainString() + ", ";
				} else {
					UpdateDataValue += "`" + DataName.getName() + "` = " + FileSettingsObjectNew.get(DataName.getName()) + ", ";
				}
			}
		}
		UpdateDataValue = UpdateDataValue.substring(0, UpdateDataValue.length() - 2);
		UpdataCondition = "`id` = \"" + FileSettingsObjectQueryResult.get("id") + "\"";

		if (new MySQLTableDataUpdate("wrapper", "scenes", UpdateDataValue, UpdataCondition).doUpdate()) {
			return "OK";
		} else {
			return "Fail";
		}
	}
}