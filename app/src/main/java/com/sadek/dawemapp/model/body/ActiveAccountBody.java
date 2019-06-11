package com.sadek.dawemapp.model.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveAccountBody {

    @Expose
    @SerializedName("FireBaseToken")
    private String firebasetoken;
    @Expose
    @SerializedName("Code")
    private String code;
    @Expose
    @SerializedName("UserId")
    private String userid;

    public String getFirebasetoken() {
        return firebasetoken;
    }

    public void setFirebasetoken(String firebasetoken) {
        this.firebasetoken = firebasetoken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
