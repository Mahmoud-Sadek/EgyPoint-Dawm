package com.sadek.dawemapp.interfaces;

import com.sadek.dawemapp.model.response.ActiveAccountResponse;

public interface ActiveAccountInterface {

    void onActiveAccountSuccess(ActiveAccountResponse response);

    void onErorr(String err);
    void onUnauthorized(String accountStateMessage, String userId);

    void showProgress(Boolean isShowing);
}
