package com.sadek.dawemapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResturantResponse {

    @Expose
    @SerializedName("AccountState")
    private String accountstate;
    @Expose
    @SerializedName("UserId")
    private int userid;

    public String getAccountstate() {
        return accountstate;
    }

    public void setAccountstate(String accountstate) {
        this.accountstate = accountstate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
