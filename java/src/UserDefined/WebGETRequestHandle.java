package UserDefined;

import FileUpload.Config.FileUploadPathConfig;
import WebServer.Config.WebServerPathConfig;
import WebServer.Interface.WebGETRequestHandleInterface;

public class WebGETRequestHandle implements WebGETRequestHandleInterface {
	
	@Override
	public String GETRequestHandleReturn(String GetRequest) {

		if (GetRequest.contains(WebServerPathConfig.getWebServerRootPath() + "/")) {
			GetRequest = GetRequest.replaceAll(WebServerPathConfig.getWebServerRootPath() + "/", WebServerPathConfig.getWebServerRootPath());
		}
		
		if (GetRequest.contains("/" + FileUploadPathConfig.getUploadFilePath().split("/")[FileUploadPathConfig.getUploadFilePath().split("/").length - 1] + "/")) {
			GetRequest = GetRequest.replaceAll(WebServerPathConfig.getWebServerRootPath(), "");
			GetRequest = GetRequest.replaceAll(FileUploadPathConfig.getUploadFilePath().split("/")[FileUploadPathConfig.getUploadFilePath().split("/").length - 1] + "/", "");
			GetRequest = FileUploadPathConfig.getUploadFilePath() + GetRequest;
		}
		
		return GetRequest;
	}
}
