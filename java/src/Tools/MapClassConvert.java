package Tools;

import java.util.Map;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class MapClassConvert {

	private Class<?> ModelClass = null;
	private Map<String, Object> MapObject = null;
	
	public MapClassConvert() {
		
	}
	
	public <T> MapClassConvert(Class<T> ModelClass, Map<String, Object> MapObject) {
		this.ModelClass = ModelClass;
		this.MapObject = MapObject;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Class<T> ToClass() {
		for(String FieldName : this.MapObject.keySet()) {
			try {
				Method ClassMethod = this.ModelClass.getMethod("set" + FieldName, this.MapObject.get(FieldName).getClass());
				Object STM = this.ModelClass.newInstance();

				ClassMethod.invoke(STM, this.MapObject.get(FieldName));
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return (Class<T>) this.ModelClass;
	}
}
