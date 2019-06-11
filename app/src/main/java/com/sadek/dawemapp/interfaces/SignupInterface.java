package com.sadek.dawemapp.interfaces;

import com.sadek.dawemapp.model.response.SignupResponse;

public interface SignupInterface {

    void onSignupSuccess(SignupResponse response);
    void onErorr(String err);
    void showProgress(Boolean isShowing);
}
