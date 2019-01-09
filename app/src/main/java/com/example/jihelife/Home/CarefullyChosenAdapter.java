package com.example.jihelife.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jihelife.R;
import com.example.jihelife.gson.Merchant;

import java.util.List;

/**
 * Created by jihelife on 2019/1/9.
 */

public class CarefullyChosenAdapter extends RecyclerView.Adapter<CarefullyChosenAdapter.ViewHolder> {

    private Context mContext;

    private List<Merchant> mMerchantList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View merchantView;
        ImageView merchantImage;
        TextView merchantName;

        public ViewHolder(View view) {
            super(view);
            merchantView = view;
            merchantImage = (ImageView)view.findViewById(R.id.merchant_image_view);
            merchantName = (TextView)view.findViewById(R.id.merchant_name_text_view);
        }
    }

    public CarefullyChosenAdapter(List<Merchant> merchantList) {
        mMerchantList = merchantList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.merchant, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.merchantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Merchant merchant = mMerchantList.get(position);
                Toast.makeText(mContext, merchant.hotelCname, Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Merchant merchant = mMerchantList.get(position);
        Glide.with(mContext).load(merchant.listImg).into(holder.merchantImage);
        holder.merchantName.setText(merchant.hotelCname);
    }

    @Override
    public int getItemCount() {
        return mMerchantList.size();
    }
}
