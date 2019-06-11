package com.sadek.dawemapp.utils;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Common {


    public static final String Registration_Device = "Registration_Device";
    public static final String language = "language";
    public static final String ACCOUNT_STATUS_PENDING = "Pending";
    public static final String USER_ID = "USER_ID";
    public static final String USER_PHONE = "USER_PHONE";
    public static final String ACCOUNT_STATUS_ACTIVE = "Active";
    public static final String USER_TOKEN = "USER_TOKEN";
    public static final String ACCOUNT_STATUS_NOT_FOUND = "NotFound";

    public static void autoScrollRecycler(final RecyclerView recyclerView, final int dataListSize){
        final int speedScroll = 4000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (recyclerView != null) {
                    if (count > dataListSize + 1) {
                        count = 0;
                    }
                    if (count <= dataListSize + 1) {
                        recyclerView.scrollToPosition(count);
                        count++;
                        handler.postDelayed(this, speedScroll);
                    }
                }
            }
        };
        handler.postDelayed(runnable, speedScroll);
    }
    public static void keyHash(Activity activity) {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(
                    "com.sadek.dawemapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

}
