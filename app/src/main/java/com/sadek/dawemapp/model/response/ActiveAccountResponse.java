package com.sadek.dawemapp.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveAccountResponse {


    @Expose
    @SerializedName("Token")
    private String token;
    @Expose
    @SerializedName("AccountState")
    private String accountstate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountstate() {
        return accountstate;
    }

    public void setAccountstate(String accountstate) {
        this.accountstate = accountstate;
    }
}
