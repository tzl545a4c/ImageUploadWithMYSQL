package T1_TestFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnector {

	public MySQLConnector() {

	}

	public void doConnect() {
		Connection MySQLConnector = null;
		Statement MySQLState = null;
		ResultSet MySQLResult = null;
		String SQLCommand = "";

		try {
			SQLCommand = "SHOW DATABASES";
			Class.forName("com.mysql.cj.jdbc.Driver");
			MySQLConnector = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=UTC", "root", "root");
			MySQLState = MySQLConnector.createStatement();
			MySQLState.executeQuery(SQLCommand);
			MySQLResult = MySQLState.getResultSet();
     		while(MySQLResult.next()) {
     			System.out.println(MySQLResult.getString(1));
     		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
