package com.example.jihelife.gson;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by jihelife on 2019/1/9.
 */

public class Merchant {

    public String listImg;

    public String hotelCname;

    public int jiheBusinessMember;

    public List tagList;

    public ProductPrice productPrice;

    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    public String getHotelCname() {
        return hotelCname;
    }

    public void setHotelCname(String hotelCname) {
        this.hotelCname = hotelCname;
    }

    public int getJiheBusinessMember() {
        return jiheBusinessMember;
    }

    public void setJiheBusinessMember(int jiheBusinessMember) {
        this.jiheBusinessMember = jiheBusinessMember;
    }

    public List getTagList() {
        return tagList;
    }

    public void setTagList(List tagList) {
        this.tagList = tagList;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }
}
