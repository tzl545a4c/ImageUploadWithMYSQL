package WebServer.PostRequestHandle;

import java.util.Map;

import FileUpload.Config.FileUploadPathConfig;
import MySQL.CallFunciton.MySQLTableQuery;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class GetAssignDataTableWithMySQL {

	private byte[] PostData = null;
	
	public GetAssignDataTableWithMySQL() {
		
	}

	public GetAssignDataTableWithMySQL(byte[] PostData) {
		this.PostData = PostData;
	}
	
	public String doGet() {
		String DataTableValue = "";
		Map<String, Object> DataTable = new HashMap<String, Object>();
		String FilePath = new File(FileUploadPathConfig.getUploadFilePath()).getAbsolutePath().replace("\\", "/") + "/";

		try {
			FilePath += URLDecoder.decode(new String(this.PostData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		DataTableValue = "{ ";
		DataTable.putAll(new MySQLTableQuery("wrapper", "scenes", "*", "`card_img` = \"" + FilePath + "\"").doQuery().get(0));
		
		for(String DataTableKey : DataTable.keySet()) {
			DataTableValue += "\"" + DataTableKey + "\":\"" + DataTable.get(DataTableKey) + "\", ";
		}
		
		DataTableValue = DataTableValue.substring(0, DataTableValue.length() - 2);
		DataTableValue += "}";
		
		return DataTableValue;
	}
}
