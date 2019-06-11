package com.sadek.dawemapp.model.body;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SocialSignupBody {

    @Expose
    @SerializedName("DeviceId")
    private String deviceid;
    @Expose
    @SerializedName("FireBaseToken")
    private String firebasetoken;
    @Expose
    @SerializedName("Longitude")
    private String longitude;
    @Expose
    @SerializedName("Latitude")
    private String latitude;
    @Expose
    @SerializedName("Address")
    private String address;
    @Expose
    @SerializedName("Language")
    private String language;
    @Expose
    @SerializedName("EmailAddress")
    private String emailaddress;
    @Expose
    @SerializedName("ProviderName")
    private String providername;
    @Expose
    @SerializedName("ProviderId")
    private String providerid;
    @Expose
    @SerializedName("FullName")
    private String fullname;
    @Expose
    @SerializedName("Phone")
    private String phone;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getFirebasetoken() {
        return firebasetoken;
    }

    public void setFirebasetoken(String firebasetoken) {
        this.firebasetoken = firebasetoken;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
