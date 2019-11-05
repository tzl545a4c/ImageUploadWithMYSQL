package T1_TestFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLTableCreate {

	public MySQLTableCreate() {

	}

	public void doCreate() {
		Connection MySQLConnector = null;
		Statement MySQLState = null;
		ResultSet MySQLResult = null;
		String SQLCommand = "";

		try {
			SQLCommand = "CREATE TABLE TESTTABLE001 (Picture VARCHAR(40), PictureID VARCHAR(40), PictureName VARCHAR(40), Price INT)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			MySQLConnector = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb001?serverTimezone=UTC", "root", "root");
			MySQLState = MySQLConnector.createStatement();
			MySQLState.executeUpdate(SQLCommand);
			MySQLResult = MySQLState.getResultSet();
			while (MySQLResult.next()) {
				System.out.println(MySQLResult);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
