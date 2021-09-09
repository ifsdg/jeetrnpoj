package com.situ.util;

import java.util.List;

import org.json.JSONArray;

public class JsonUtil {

	@SuppressWarnings("rawtypes")
	public static String parseJson(List list) {
		JSONArray jsonArray = new JSONArray(list);
		
		String aString =jsonArray.toString();
	
		return aString;
		
	}
}
