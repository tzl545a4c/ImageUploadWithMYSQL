package T1_TestFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMySQLTableQuery {

	public TestMySQLTableQuery() {

	}

	public void doQuery() {
		Connection MySQLConnector = null;
		Statement MySQLState = null;
		ResultSet MySQLResult = null;
		String SQLCommand = "";

		try {
			SQLCommand = "SELECT * FROM `TESTTABLE001`";
			Class.forName("com.mysql.cj.jdbc.Driver");
			MySQLConnector = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb001?serverTimezone=UTC", "root", "root");
			MySQLState = MySQLConnector.createStatement();
			MySQLState.executeQuery(SQLCommand);
			MySQLResult = MySQLState.getResultSet();
     		while(MySQLResult.next()) {
     			System.out.println(MySQLResult.getRow());
     			System.out.println(MySQLResult.getString(1));
     			System.out.println(MySQLResult.getString(2));
     			System.out.println(MySQLResult.getString(3));
     			System.out.println(MySQLResult.getString(4));
     		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
