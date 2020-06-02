package cn.book.comm.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {
	public static final ObjectMapper MP=new ObjectMapper();
	
	/**
	 * beanTohash
	 * @param <T>
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static <T> Map<String,String> BeanToHash(T obj) throws IllegalArgumentException, IllegalAccessException{
		Map<String,String> map = new HashMap<String,String>(); 
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields){
			field.setAccessible(true);
			map.put(field.getName(),(String) field.get(obj));
		}
		return map;
	}
	
	/**
	 * mapTobean
	 * @param map
	 * @return bean
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T HashToBean(Map<String,String> map,Class<T> clazz) throws InstantiationException, IllegalAccessException{
		Object obj = clazz.newInstance(); 
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields){
			int mod = field.getModifiers(); 
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
		}
		return (T) obj;
	}
}
