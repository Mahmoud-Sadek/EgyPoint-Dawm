package com.sadek.dawemapp.service;

import android.content.Context;

import com.sadek.dawemapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static Router router;

    public static final String BASIC_URL = "http://dawem11-001-site1.htempurl.com/";

    public static Router getRouter(Context context) {
        if (router == null)
            router = build(context);
        return router;
    }

    private static Router build(final Context context) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(100, TimeUnit.SECONDS);
        httpClient.readTimeout(100,TimeUnit.SECONDS);
        httpClient.addInterceptor(new GenericInterceptor(context));

        // Show Network logging
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASIC_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Router.class);
    }

    public static void validateRouter(Context context) {
        router = build(context);
    }
}
