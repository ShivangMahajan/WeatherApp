package com.sabbey.weatherapp;

public class CardView {

    private String mDTemp;
    private String mDDate;
    private String mDTime;
    private String mCondtion;
    private int mImage;

    public CardView(String mDTemp, String mDDate, String mDTime, String mCondtion, int mImage) {
        this.mDTemp = mDTemp;
        this.mDDate = mDDate;
        this.mDTime = mDTime;
        this.mCondtion = mCondtion;
        this.mImage = mImage;
    }

    public String getmDTemp() {
        return mDTemp;
    }

    public String getmDDate() {
        return mDDate;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmDTime() {
        return mDTime;
    }

    public String getmCondtion() {
        return mCondtion;
    }
}
