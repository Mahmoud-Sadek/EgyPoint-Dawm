package com.sadek.dawemapp.model.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class SocialLoginBody {

    @Expose
    @SerializedName("FireBaseToken")
    private String firebasetoken;
    @Expose
    @SerializedName("ProviderName")
    private String providername;
    @Expose
    @SerializedName("ProviderId")
    private String providerid;

    public String getFirebasetoken() {
        return firebasetoken;
    }

    public void setFirebasetoken(String firebasetoken) {
        this.firebasetoken = firebasetoken;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public String getProviderid() {
        return providerid;
    }

    public void setProviderid(String providerid) {
        this.providerid = providerid;
    }
}
