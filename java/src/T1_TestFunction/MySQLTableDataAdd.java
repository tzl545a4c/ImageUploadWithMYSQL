package T1_TestFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLTableDataAdd {

	public MySQLTableDataAdd() {

	}

	public void doAdd() {
		Connection MySQLConnector = null;
		Statement MySQLState = null;
		ResultSet MySQLResult = null;
		String SQLCommand = "";

		try {
			SQLCommand = "INSERT INTO TESTTABLE001 VALUES ('TestPicture', 'TP001', 'TestPicName', 123)";
			Class.forName("com.mysql.cj.jdbc.Driver");
			MySQLConnector = DriverManager.getConnection("jdbc:mysql://localhost:3306/sence?serverTimezone=UTC", "root", "root");
			MySQLState = MySQLConnector.createStatement();
			MySQLState.executeUpdate(SQLCommand);
			MySQLResult = MySQLState.getResultSet();
			System.out.println(MySQLResult);
			System.out.println(MySQLResult.getMetaData().getColumnCount());
			System.out.println(MySQLResult.getMetaData().getColumnLabel(1));
//     		while(MySQLResult.next()) {
//     			System.out.println(MySQLResult);
//     		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
