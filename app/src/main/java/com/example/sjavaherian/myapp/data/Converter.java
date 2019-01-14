package com.example.sjavaherian.myapp.data;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converter {

    @TypeConverter
    public static String listToString(List<String> strings) {
        Gson gson = new Gson();
        return gson.toJson(strings);
    }

    @TypeConverter
    public static List<String> stringToList(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,new TypeToken<List<String>>(){}.getType());
    }

}
