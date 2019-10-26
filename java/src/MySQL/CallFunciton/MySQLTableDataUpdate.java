package MySQL.CallFunciton;

import java.sql.Connection;
import java.sql.Statement;

public class MySQLTableDataUpdate {

	private String DataBaseName = "";
	private String TableName = "";
	private String UpdateDataValue = "";
	private String UpdataCondition = "";

	public MySQLTableDataUpdate() {
		
	}

	public MySQLTableDataUpdate(String DataBaseName, String TableName, String UpdateDataValue, String UpdataCondition) {
		this.DataBaseName = DataBaseName;
		this.TableName = TableName;
		this.UpdateDataValue = UpdateDataValue;
		this.UpdataCondition = UpdataCondition;
	}

	public boolean doUpdate() {
		Connection MySQLConnection = new MySQLConnector(this.DataBaseName).doConnect();
		String SQLCommand = "";
		Statement MySQLState = null;

		SQLCommand = "UPDATE `" + this.TableName + "` SET " + this.UpdateDataValue + " WHERE " + this.UpdataCondition;

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
