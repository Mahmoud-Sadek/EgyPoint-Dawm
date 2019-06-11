package com.sadek.dawemapp.service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() == 400||response.code() == 404) {
            try {
            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                String ErrorMessage = jsonObject.getString("Message");
                onErrorBadRequest(ErrorMessage);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (response.code() == 200) {
            onResponse(call, response,true);
        }else if (response.code() == 401) {
            try {
                onErrorUnauthorized(response.errorBody().string());
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public abstract void onErrorBadRequest(String response);
    public abstract void onErrorUnauthorized(String response);

    public abstract void onResponse(Call<T> call, Response<T> response, boolean isSuccessful);

}