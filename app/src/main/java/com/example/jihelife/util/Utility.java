package com.example.jihelife.util;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jihelife on 2019/1/10.
 */

public class Utility {

    public static String handleResponse(String response, String key) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            return jsonArray.getJSONObject(0).toString();
//             new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
