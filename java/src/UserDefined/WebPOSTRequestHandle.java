package UserDefined;

import FileUpload.Config.FileUploadPathConfig;
import WebServer.Interface.WebPOSTRequestHandleInterface;
import WebServer.PostRequestHandle.ImageAddFileSettingsUpload;
import WebServer.PostRequestHandle.ImageEditFileSettingsUpload;
import WebServer.PostRequestHandle.TypeAddFileSettingsUpload;
import WebServer.PostRequestHandle.FileUploadCheck;
import WebServer.PostRequestHandle.GetAssignDataTableWithMySQL;
import WebServer.PostRequestHandle.GetPictureListWithMySQL;
import WebServer.PostRequestHandle.GetTypeList;

public class WebPOSTRequestHandle implements WebPOSTRequestHandleInterface {

	@Override
	public String POSTRequestHandleReturn(String PostRequest, byte[] PostData) {
		switch (PostRequest) {
			case ("GetTypeList"): {
				return new GetTypeList().doGet();
			}
			case ("FileUploadCheck"): {
				return new FileUploadCheck(PostData).doCheck();
			}
			case ("GetPictureList"): {
				return new GetPictureListWithMySQL(FileUploadPathConfig.getUploadFilePath()).doGet();
			}
			case ("GetAssignDataTable"): {
				return new GetAssignDataTableWithMySQL(PostData).doGet();
			}
			case ("ImageAddFileSettingsUpload"): {
				return new ImageAddFileSettingsUpload(PostData).doUpload();
			}
			case ("TypeAddFileSettingsUpload"): {
				return new TypeAddFileSettingsUpload(PostData).doUpload();
			}
			case ("ImageEditFileSettingsUpload"): {
				return new ImageEditFileSettingsUpload(PostData).doUpload();
			}
			default: {
				return "";
			}
		}
	}
}
