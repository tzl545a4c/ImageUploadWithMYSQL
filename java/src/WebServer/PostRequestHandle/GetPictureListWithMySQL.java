package WebServer.PostRequestHandle;

import java.util.Map;
import java.util.ArrayList;
import MySQL.CallFunciton.MySQLTableQuery;
import FileUpload.Config.FileUploadPathConfig;

public class GetPictureListWithMySQL {

	public GetPictureListWithMySQL() {

	}

	public GetPictureListWithMySQL(String PicturePath) {

	}

	public String doGet() {
		ArrayList<String> PictureList = new ArrayList<String>();
		ArrayList<Map<String, Object>> QueryResultList = new ArrayList<Map<String, Object>>();
		String FileUploadFolder = "/" + FileUploadPathConfig.getUploadFilePath().split("/")[FileUploadPathConfig.getUploadFilePath().split("/").length - 1] + "/";
		
		QueryResultList.addAll(new MySQLTableQuery("wrapper", "scenes", "card_img", "").doQuery());

		for (Map<String, Object> QueryResult : QueryResultList) {
			String PicturePath = QueryResult.get("card_img").toString();

			if (PicturePath.contains(FileUploadPathConfig.getUploadFilePath())) {
				PictureList.add(FileUploadFolder + PicturePath.split(FileUploadFolder)[1]);
			}
		}

		return PictureList.toString().substring(1, PictureList.toString().length() - 1);
	}
}
