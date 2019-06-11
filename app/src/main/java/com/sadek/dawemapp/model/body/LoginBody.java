package com.sadek.dawemapp.model.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @Expose
    @SerializedName("FireBaseToken")
    private String firebasetoken;
    @Expose
    @SerializedName("Password")
    private String password;
    @Expose
    @SerializedName("LoginName")
    private String loginname;

    public String getFirebasetoken() {
        return firebasetoken;
    }

    public void setFirebasetoken(String firebasetoken) {
        this.firebasetoken = firebasetoken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
}
