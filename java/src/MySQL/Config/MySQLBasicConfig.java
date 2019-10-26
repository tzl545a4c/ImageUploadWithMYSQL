package MySQL.Config;

public class MySQLBasicConfig {

	private static String MySQLDriverClassName = "com.mysql.cj.jdbc.Driver";
	private static String MySQLConnectIP = "127.0.0.1";
	private static String MySQLConnectPort = "3306";
	private static String MySQLConnectAccount = "root";
	private static String MySQLConnectPassword = "root";
	
	public static void setMySQLDriverClassName(String MySQLDriverClassName) {
		MySQLBasicConfig.MySQLDriverClassName = MySQLDriverClassName;
	}
	
	public static String getMySQLDriverClassName() {
		return MySQLBasicConfig.MySQLDriverClassName;
	}
	
	public static void setMySQLConnectIP(String MySQLConnectIP) {
		MySQLBasicConfig.MySQLConnectIP = MySQLConnectIP;
	}
	
	public static String getMySQLConnectIP() {
		return MySQLBasicConfig.MySQLConnectIP;
	}
	
	public static void setMySQLConnectPort(String MySQLConnectPort) {
		MySQLBasicConfig.MySQLConnectPort = MySQLConnectPort;
	}
	
	public static String getMySQLConnectPort() {
		return MySQLBasicConfig.MySQLConnectPort;
	}
	
	public static void setMySQLConnectAccount(String ConnectAccount) {
		MySQLBasicConfig.MySQLConnectAccount = ConnectAccount;
	}
	
	public static String getMySQLConnectAccount() {
		return MySQLBasicConfig.MySQLConnectAccount;
	}
	
	public static void setMySQLConnectPassword(String MySQLConnectPassword) {
		MySQLBasicConfig.MySQLConnectPassword = MySQLConnectPassword;
	}
	
	public static String getMySQLConnectPassword() {
		return MySQLBasicConfig.MySQLConnectPassword;
	}
}
