package MySQL.CallFunciton;

import java.sql.Connection;
import java.sql.Statement;

public class MySQLTableDataAdd {

	private String DataBaseName = "";
	private String TableName = "";
	private String DataValue = "";

	public MySQLTableDataAdd() {
		
	}

	public MySQLTableDataAdd(String DataBaseName, String TableName, String DataValue) {
		this.DataBaseName = DataBaseName;
		this.TableName = TableName;
		this.DataValue = DataValue;
	}

	public boolean doAdd() {
		Connection MySQLConnection = new MySQLConnector(this.DataBaseName).doConnect();
		String SQLCommand = "";
		Statement MySQLState = null;

		SQLCommand = "INSERT INTO `" + this.TableName + "` VALUES (" + this.DataValue + ")";

		try {
			MySQLState = MySQLConnection.createStatement();
			MySQLState.executeUpdate(SQLCommand);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}
