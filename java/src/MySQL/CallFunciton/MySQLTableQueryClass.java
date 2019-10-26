package MySQL.CallFunciton;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import Tools.MapClassConvert;
import Tools.TypeNameConvert;

public class MySQLTableQueryClass {

	private String DataBaseName = "";
	private String TableName = "";
	private String FieldName = "";
	private Class<?> ModelClass = null;
	
	public MySQLTableQueryClass() {
		
	}
	
	public <T> MySQLTableQueryClass(String DataBaseName, String TableName, String FieldName, Class<T> ModelClass) {
		this.DataBaseName = DataBaseName;
		this.TableName = TableName;
		this.FieldName = FieldName;
		this.ModelClass = ModelClass;
	}
	
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> doQuery() {
		ArrayList<T> QueryResultList = new ArrayList<T>();
		String QueryCommand = "";
		Statement MySQLState = null;
		ResultSet MySQLResult = null;
		Connection MySQLConnection = new MySQLConnector(this.DataBaseName).doConnect();
		
		QueryCommand = "SELECT " + this.FieldName + " FROM `" + this.TableName + "`";
		
		try {
			MySQLState = MySQLConnection.createStatement();
			MySQLState.executeQuery(QueryCommand);
			MySQLResult = MySQLState.getResultSet();
			
     		while(MySQLResult.next()) {
     			Map<String, Object> QueryResult = new HashMap<String, Object>(); 
     			
     			for(int i = 0; i < MySQLResult.getMetaData().getColumnCount(); i++) {
     				String ResultFieldName = MySQLResult.getMetaData().getColumnLabel(i + 1);
     				Object ResultFieldValue = MySQLResult.getObject(i + 1);

     				QueryResult.put(ResultFieldName, new TypeNameConvert(ResultFieldValue.getClass().getSimpleName(), ResultFieldValue).doConvert());
     			}
     			
     			QueryResultList.add((T) new MapClassConvert(this.ModelClass, QueryResult).ToClass());
     		}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return QueryResultList;
	}
}
