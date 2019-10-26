package MySQL.CallFunciton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import MySQL.Config.MySQLBasicConfig;

public class MySQLConnector {

	private Connection MySQLConnection = null;
	private String MySQLConnectIP = "";
	private String MySQLConnectPort = "";
	private String MySQLConnectDataBase = "";

	public MySQLConnector() {

	}

	public MySQLConnector(String MySQLConnectDataBase) {
		this.MySQLConnectIP = MySQLBasicConfig.getMySQLConnectIP();
		this.MySQLConnectPort = MySQLBasicConfig.getMySQLConnectPort();
		this.MySQLConnectDataBase = MySQLConnectDataBase;
	}

	public MySQLConnector(String MySQLConnectIP, String MySQLConnectPort, String MySQLConnectDataBase) {
		this.MySQLConnectIP = MySQLConnectIP;
		this.MySQLConnectPort = MySQLConnectPort;
		this.MySQLConnectDataBase = MySQLConnectDataBase;
	}

	public Connection doConnect() {
		String MySQLDriverClassName = "";
		String MySQLConnectURL = "";
		String MySQLConnectAccount = "";
		String MySQLConnectPassword = "";

		MySQLDriverClassName = MySQLBasicConfig.getMySQLDriverClassName();
		MySQLConnectURL = "jdbc:mysql://" + this.MySQLConnectIP + ":" + this.MySQLConnectPort + "/" + this.MySQLConnectDataBase + "?serverTimezone=UTC";
		MySQLConnectAccount = MySQLBasicConfig.getMySQLConnectAccount();
		MySQLConnectPassword = MySQLBasicConfig.getMySQLConnectPassword();

		try {
			Class.forName(MySQLDriverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		try {
			this.MySQLConnection = DriverManager.getConnection(MySQLConnectURL, MySQLConnectAccount, MySQLConnectPassword);
			return this.MySQLConnection;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
