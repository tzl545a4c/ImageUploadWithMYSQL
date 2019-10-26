package Tools;

import java.sql.Timestamp;

public class TypeNameConvert {

	private String TypeName = "";
	private Object DataValue = "";
	
	public TypeNameConvert() {
		
	}
	
	public TypeNameConvert(String TypeName, Object DataValue) {
		this.TypeName = TypeName.toLowerCase();
		this.DataValue = DataValue;
	}
	
	public Object doConvert() {
		switch (this.TypeName) {
		    case ("string"): {
		    	return new String(this.DataValue.toString());
		    }
		    case ("int"): 
		    case ("integer"):
		    case ("biginteger"): {
		    	if (this.DataValue.toString().equals("")) {
		    		return new Integer(0);	
		    	} else {
		    		return new Integer(this.DataValue.toString());
		    	}
		    }
		    case ("float"): {
		    	if (this.DataValue.toString().equals("")) {
		    		return new Float(0.0);	
		    	} else {
		    		return new Float(this.DataValue.toString());
		    	}
		    }
		    case ("double"): {
		    	if (this.DataValue.toString().equals("")) {
		    		return new Double(0.0);	
		    	} else {
		    		return new Double(this.DataValue.toString());
		    	}
		    }
		    case ("timestamp"): {
		    	if (this.DataValue.toString().equals("")) {
		    		return Timestamp.valueOf("2000-01-01 12:00:00");	
		    	} else {
		    		return Timestamp.valueOf(this.DataValue.toString());
		    	}
		    }
		    default: {
		    	return this.DataValue;
		    }
		}
	}
}
