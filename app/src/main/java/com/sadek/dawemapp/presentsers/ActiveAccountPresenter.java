package com.sadek.dawemapp.presentsers;

import android.content.Context;

import com.sadek.dawemapp.interfaces.ActiveAccountInterface;
import com.sadek.dawemapp.model.body.ActiveAccountBody;
import com.sadek.dawemapp.model.response.ActiveAccountResponse;
import com.sadek.dawemapp.service.CCallback;
import com.sadek.dawemapp.service.ServiceBuilder;
import com.sadek.dawemapp.utils.Common;

import org.json.JSONException;
import org.json.JSONObject;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Response;

public class ActiveAccountPresenter {
    private final String TAG = "ActiveAccount";

    public static void sendActiveAccount(Context context, ActiveAccountBody request, final ActiveAccountInterface activeAccountInterface) {
        Paper.init(context);
        activeAccountInterface.showProgress(true);

        request.setFirebasetoken(Paper.book().read(Common.Registration_Device).toString());

        String lang = Paper.book().read(Common.language);
        Call<ActiveAccountResponse> myCall = ServiceBuilder.getRouter(context).getActiveAccountResponse(request, lang);
        myCall.enqueue(new CCallback<ActiveAccountResponse>() {
            @Override
            public void onErrorBadRequest(String response) {
                activeAccountInterface.showProgress(false);
                activeAccountInterface.onErorr(response);
            }

            @Override
            public void onErrorUnauthorized(String response) {
                String AccountStateMessage = "", userId = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    AccountStateMessage = jsonObject.getString("AccountState");
                    userId = jsonObject.getString("UserId");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                activeAccountInterface.onUnauthorized(AccountStateMessage, userId);
                activeAccountInterface.showProgress(false);

            }

            @Override
            public void onResponse(Call<ActiveAccountResponse> call, Response<ActiveAccountResponse> response, boolean isSuccessful) {
                activeAccountInterface.showProgress(false);
                if (response.isSuccessful()) {
                    activeAccountInterface.onActiveAccountSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ActiveAccountResponse> call, Throwable t) {
                activeAccountInterface.showProgress(false);
                activeAccountInterface.onErorr(t.getMessage());
            }
        });
    }

   public static void sendReSendActivationCode(Context context,String userId, final ActiveAccountInterface activeAccountInterface) {
        Paper.init(context);
        activeAccountInterface.showProgress(true);
       String lang = Paper.book().read(Common.language);

        Call<ActiveAccountResponse> myCall = ServiceBuilder.getRouter(context).getReSendActivationCodeResponse( lang,userId );
        myCall.enqueue(new CCallback<ActiveAccountResponse>() {
            @Override
            public void onErrorBadRequest(String response) {
                activeAccountInterface.showProgress(false);
                activeAccountInterface.onErorr(response);
            }

            @Override
            public void onErrorUnauthorized(String response) {
                String AccountStateMessage = "", userId = "";
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    AccountStateMessage = jsonObject.getString("AccountState");
                    userId = jsonObject.getString("UserId");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                activeAccountInterface.onUnauthorized(AccountStateMessage, userId);
                activeAccountInterface.showProgress(false);

            }

            @Override
            public void onResponse(Call<ActiveAccountResponse> call, Response<ActiveAccountResponse> response, boolean isSuccessful) {
                activeAccountInterface.showProgress(false);
                if (response.isSuccessful()) {
                    activeAccountInterface.onActiveAccountSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ActiveAccountResponse> call, Throwable t) {
                activeAccountInterface.showProgress(false);
                activeAccountInterface.onErorr(t.getMessage());
            }
        });
    }


}
