package com.tir.ocinio.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tir.ocinio.exception.JsonSerializerFormatException;


public class JsonSerializer {

	private String capitalize(String src) {
		return src.substring(0, 1).toUpperCase() + src.substring(1);
	}
	
	public static List<String> splitString(String input) {
        List<String> parts = new ArrayList<>();
        StringBuilder currentPart = new StringBuilder();
        int bracketCount = 0;

        for (char c : input.toCharArray()) {
            if (c == '{') {
                bracketCount++;
            } else if (c == '}') {
                bracketCount--;
            }
            if (c == ',' && bracketCount == 0) {
                parts.add(currentPart.toString().trim());
                currentPart = new StringBuilder();
            } else {
                currentPart.append(c);
            }
        }
        parts.add(currentPart.toString().trim());
        return parts;
    }

	private Method getGetter(Object obj, String attribute) {

		String methodName = "get" + capitalize(attribute);

		Method method = null;
		try {
			method = obj.getClass().getMethod(methodName);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("Metodo non trovato: " + methodName);
			e.printStackTrace();
		}
		return method;
	}
	
	public <T> List<HashMap<String,Object>> serializeAll(String format, List<T> list) throws JsonSerializerFormatException{
		List<HashMap<String, Object>> result = new ArrayList<>();
		for(var element : list) {
			result.add(serialize(format, element));
		}
		return result;
	}

	public HashMap<String, Object> serialize(String format, Object object) throws JsonSerializerFormatException{
		
		var attributes = new HashMap<String, Object>();
		
		format = format.substring(1, format.length() - 1);
		format = format.replaceAll("\\s+", "");
		var attrKeys = splitString(format);
		
		for(var element : attrKeys) {
			if(!element.contains(":")) {
				try {
					attributes.put(element, getGetter(object, element).invoke(object));
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new JsonSerializerFormatException("Cannot invoke getter method for attribute: " + element);
				} 
			}
			else {
				var parts = element.split(":", 2);
				var attr = parts[0];
				var obj = parts[1];
				
				Object serialized = null;
				Class<?> type = null;
				try {
					Method getter = getGetter(object, attr);
					serialized = getter.invoke(object);
					type = getter.getReturnType();
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new JsonSerializerFormatException("Cannot invoke getter method for attribute: " + attr);
				} 
				if(Iterable.class.isAssignableFrom(type)) {
					var list = new ArrayList<>();
					try {
						for(var el : (Iterable<?>) serialized) {
							list.add(serialize(obj, el));
						}
					} catch (NullPointerException e) {}
					attributes.put(attr, list);
				}else {
					try {
						attributes.put(attr, serialize(obj, serialized));
					} catch(NullPointerException e) {
						attributes.put(attr, null);
					}
				}
			}
		}
		return attributes;
	}

}

