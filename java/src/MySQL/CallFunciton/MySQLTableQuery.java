package MySQL.CallFunciton;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import Tools.TypeNameConvert;

public class MySQLTableQuery {

	private String DataBaseName = "";
	private String TableName = "";
	private String FieldName = "";
	private String QueryCondition = "";

	public MySQLTableQuery() {

	}

	public MySQLTableQuery(String DataBaseName, String TableName, String FieldName, String QueryCondition) {
		this.DataBaseName = DataBaseName;
		this.TableName = TableName;
		this.FieldName = FieldName;
		this.QueryCondition = QueryCondition;
	}

	public ArrayList<Map<String, Object>> doQuery() {
		ArrayList<Map<String, Object>> QueryResultList = new ArrayList<Map<String, Object>>();
		String SQLCommand = "";
		Statement MySQLState = null;
		ResultSet MySQLResult = null;
		Connection MySQLConnection = new MySQLConnector(this.DataBaseName).doConnect();

		if (this.QueryCondition.equals("")) {
			this.QueryCondition = "1";
		}
		
		SQLCommand = "SELECT " + this.FieldName + " FROM `" + this.TableName + "` WHERE " + this.QueryCondition;
		System.out.println("SELECT Command: " + SQLCommand);

		try {
			MySQLState = MySQLConnection.createStatement();
			MySQLState.executeQuery(SQLCommand);
			MySQLResult = MySQLState.getResultSet();

			while (MySQLResult.next()) {
				Map<String, Object> QueryResult = new HashMap<String, Object>();

				for (int i = 0; i < MySQLResult.getMetaData().getColumnCount(); i++) {
					String ResultFieldName = MySQLResult.getMetaData().getColumnLabel(i + 1);
					Object ResultFieldValue = MySQLResult.getObject(i + 1);
					
					if (ResultFieldValue != null) {
						QueryResult.put(ResultFieldName, new TypeNameConvert(ResultFieldValue.getClass().getSimpleName(), ResultFieldValue).doConvert());
					} else {
						QueryResult.put(ResultFieldName, null);
					}
				}

				QueryResultList.add(QueryResult);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return QueryResultList;
	}
}