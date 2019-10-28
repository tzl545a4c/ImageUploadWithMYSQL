package A1_Laucher;

import B1_SystemManager.B1_1_SystemInitail;
import B1_SystemManager.B1_2_SystemEnable;
import FileUpload.Config.FileUploadPathConfig;
import MySQL.Config.MySQLBasicConfig;
import WebServer.Config.WebServerBasicConfig;
import WebServer.Config.WebServerPathConfig;

public class StartUp {

	public static void main(String[] args) {
		WebServerBasicConfig.setWebServerListenPort(Integer.parseInt(System.getProperty("webserverport")));
		MySQLBasicConfig.setMySQLConnectPort(System.getProperty("mysqlport"));
		MySQLBasicConfig.setMySQLConnectAccount(System.getProperty("mysqlusername"));
		MySQLBasicConfig.setMySQLConnectPassword(System.getProperty("mysqlpassword"));
		WebServerPathConfig.setWebServerRootPath(System.getProperty("webserverpath").replaceAll("\\\\", "/"));
		FileUploadPathConfig.setUploadFilePath(System.getProperty("fileuploadpath").replaceAll("\\\\", "/"));
		
		System.out.println("Web Server Listen Port: " + WebServerBasicConfig.getWebServerListenPort());
		System.out.println("MySQL Connect Port: " + MySQLBasicConfig.getMySQLConnectPort());
		System.out.println("MySQL Connect Username: " + MySQLBasicConfig.getMySQLConnectAccount());
		System.out.println("MySQL Connect Password: " + MySQLBasicConfig.getMySQLConnectPassword());
		System.out.println("Web Server Path: " + WebServerPathConfig.getWebServerRootPath());
		System.out.println("File Upload Path: " + FileUploadPathConfig.getUploadFilePath());
		System.out.println("File Upload Folder: " + FileUploadPathConfig.getUploadFilePath().split("/")[FileUploadPathConfig.getUploadFilePath().split("/").length - 1]);
		
		new B1_1_SystemInitail().doInitial();
		new B1_2_SystemEnable().doEnable();
	}
}
