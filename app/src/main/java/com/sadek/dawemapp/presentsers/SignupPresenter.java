package com.sadek.dawemapp.presentsers;

import android.content.Context;
import android.provider.Settings;

import com.sadek.dawemapp.interfaces.SignupInterface;
import com.sadek.dawemapp.model.body.SignupBody;
import com.sadek.dawemapp.model.response.SignupResponse;
import com.sadek.dawemapp.service.CCallback;
import com.sadek.dawemapp.service.ServiceBuilder;
import com.sadek.dawemapp.utils.Common;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Response;

public class SignupPresenter {
    private final String TAG = "signup";

    public static void sendSignup(Context context, SignupBody request, final SignupInterface signupInterface) {
        Paper.init(context);
        signupInterface.showProgress(true);

        request.setFirebasetoken(Paper.book().read(Common.Registration_Device).toString());
        request.setLanguage("English");
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        request.setDeviceid(android_id);

        String lang = Paper.book().read(Common.language);

        Call<SignupResponse> myCall = ServiceBuilder.getRouter(context).getCreateAccountResponse(request, lang);
        myCall.enqueue(new CCallback<SignupResponse>() {

            @Override
            public void onErrorBadRequest(String response) {
                signupInterface.showProgress(false);
                signupInterface.onErorr(response);
            }

            @Override
            public void onErrorUnauthorized(String response) {
                signupInterface.showProgress(false);
                signupInterface.onErorr(response);
            }

            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response, boolean isSuccessful) {
                signupInterface.showProgress(false);
                if (response.isSuccessful()) {
                    signupInterface.onSignupSuccess(response.body());
                }

            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                signupInterface.showProgress(false);
                signupInterface.onErorr(t.getMessage());
            }
        });
    }


}
