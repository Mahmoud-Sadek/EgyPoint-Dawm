package com.sadek.dawemapp.service;

import android.content.Context;
import android.os.SystemClock;

import com.sadek.dawemapp.BuildConfig;
import com.sadek.dawemapp.utils.Common;

import java.io.IOException;

import io.paperdb.Paper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class GenericInterceptor implements Interceptor {
    private Context context;
    public static final String ANDROID = "0";

    public GenericInterceptor(Context context) {
        this.context = context;
        Paper.init(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Delay Requests with development
        SystemClock.sleep(BuildConfig.DELAY);

        return addRequestHeaders(chain, context);
    }

    private static Response addRequestHeaders(Chain chain, Context context) throws IOException {
        Request request;
        String lang = Paper.book().read(Common.language);
        String registration_token = Paper.book().read(Common.Registration_Device);

        if (lang == null)
            lang = "en";
        if (registration_token == null)
            registration_token = "0";

        request = chain.request().newBuilder()
//                .addHeader("Language", lang)
//                .addHeader("FireBaseToken", registration_token)
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .build();
        return chain.proceed(request);
    }
}
