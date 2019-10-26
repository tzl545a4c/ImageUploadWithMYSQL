package A1_Laucher;

import B1_SystemManager.B1_1_SystemInitail;
import B1_SystemManager.B1_2_SystemEnable;
import FileUpload.Config.FileUploadPathConfig;
import WebServer.Config.WebServerBasicConfig;
import WebServer.Config.WebServerPathConfig;

public class StartUp {

	public static void main(String[] args) {
		WebServerBasicConfig.setWebServerListenPort(Integer.parseInt(args[0]));
		WebServerPathConfig.setWebServerRootPath(args[1]);
		FileUploadPathConfig.setUploadFilePath(WebServerPathConfig.getWebServerRootPath() + args[2]);
		
		new B1_1_SystemInitail().doInitial();
		new B1_2_SystemEnable().doEnable();
	}
}
