package WebServer.PostRequestHandle;

import java.util.Map;
import java.util.ArrayList;
import MySQL.CallFunciton.MySQLTableQuery;
import WebServer.Config.WebServerPathConfig;

public class GetPictureListWithMySQL {

	public GetPictureListWithMySQL() {

	}

	public GetPictureListWithMySQL(String PicturePath) {

	}

	public String doGet() {
		ArrayList<String> PictureList = new ArrayList<String>();
		ArrayList<Map<String, Object>> QueryResultList = new ArrayList<Map<String, Object>>();

		QueryResultList.addAll(new MySQLTableQuery("wrapper", "scenes", "card_img", "").doQuery());

		for (Map<String, Object> QueryResult : QueryResultList) {
			String PicturePath = QueryResult.get("card_img").toString();

			if (PicturePath.contains(WebServerPathConfig.getWebServerRootPath())) {
				PictureList.add(PicturePath.split(WebServerPathConfig.getWebServerRootPath())[1]);
			}
		}

		return PictureList.toString().substring(1, PictureList.toString().length() - 1);
	}
}
