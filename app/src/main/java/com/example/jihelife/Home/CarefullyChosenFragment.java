package com.example.jihelife.Home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jihelife.R;
import com.example.jihelife.gson.Merchant;
import com.example.jihelife.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jihelife on 2019/1/9.
 */

public class CarefullyChosenFragment extends Fragment {

    private RecyclerView recyclerView;

    private CarefullyChosenAdapter adapter;

    private int pageNo;

    private int pageCnt;

    private List<Merchant> merchantList = new ArrayList<Merchant>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carefully_chosen, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        pageNo = 1;
        pageCnt = 5;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CarefullyChosenAdapter(merchantList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestHomeList(getContext().getString(R.string.ServerUrl)+getContext().getString(R.string.url_home_list));
    }

    private void requestHomeList(String address) {
        RequestBody requestBody = new FormBody.Builder().add("sortType","1").add("solrType", "1").add("pageno", ""+pageNo).add("pagecnt", ""+pageCnt).build();
        HttpUtil.sendOkHttpPostRequest(address, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Log.d("szr-", "onResponse: "+responseText);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        try {
                            JSONObject jsonObject = new JSONObject(responseText);
                            if ("0".equals(jsonObject.getString("sc"))) {
                                List<Merchant> tempList = gson.fromJson(jsonObject.getString("data"), new TypeToken<List<Merchant>>(){}.getType());
                                merchantList.addAll(tempList);
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(getContext(), jsonObject.getString("ErrorMsg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
