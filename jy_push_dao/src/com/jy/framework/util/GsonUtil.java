package com.jy.framework.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {

	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static <T> String toJson(T t) {
		if (t != null)
			return gson.toJson(t);
		else
			return null;
	}
	
	public static Gson createGson(){
		return gson;
	}
	
	public static <T> T toEntity(String json, Class<T> clazz){
		return gson.fromJson(json, clazz);
	}
	
	public static <T> List<T> toListEntity(String json, Class<T> clazz){
		return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<T>>(){}.getType());
	}
	
	public static <T> T toEntity(String json, TypeToken<T> typeToken){
		return gson.fromJson(json, typeToken.getType());
	}
	
}
