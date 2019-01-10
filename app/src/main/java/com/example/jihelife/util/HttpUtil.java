package com.example.jihelife.util;

import android.util.Log;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by jihelife on 2019/1/9.
 */

public class HttpUtil {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void sendOkHttpGetRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkHttpPostRequest(String address, RequestBody body, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder().url(address).post(body).build();
        client.newCall(request).enqueue(callback);
    }
}
