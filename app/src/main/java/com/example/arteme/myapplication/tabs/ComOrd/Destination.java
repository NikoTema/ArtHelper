package com.example.arteme.myapplication.tabs.ComOrd;


public class Destination {

    private String mRic;
    private String mMax;
    private String mMor;

    public Destination(String ric, String max, String mor) {
        mRic = ric;
        mMax = max;
        mMor = mor;
    }
    public Destination() {

    }

    public void setRic(String ric) {
        mRic = ric;
    }

    public void setMax(String max) {
        mMax = max;
    }

    public void setMor(String mor) {
        mMor = mor;
    }

    public String getRic() {
        return mRic;
    }

    public String getMax() {
        return mMax;
    }

    public String getMor() {
        return mMor;
    }
}
