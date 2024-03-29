package com.sadek.dawemapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.sadek.dawemapp.R;
import com.sadek.dawemapp.utils.Common;
import com.sadek.dawemapp.utils.LocaleUtils;

import io.paperdb.Paper;

public class BaseActitvty extends AppCompatActivity {

//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
////        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Paper.init(this);
        String lang = Paper.book().read(Common.language);
        if (lang == null)
            Paper.book().write(Common.language, LocaleUtils.ARABIC);
        lang = Paper.book().read(Common.language);
        LocaleUtils.initialize(getBaseContext(), lang);

//        overridePendingTransition(R.anim.animation_up, R.anim.animation_down);
//        setContentView(R.layout.activity_base);
//
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/Oswald-Stencbab.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();

//        if (!isNetworkAvailable()) {
//            MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
//            builder.customView(R.layout.network_custom_dialog, false);
//            builder.backgroundColor(getResources().getColor(R.color.transparent));
//            builder.show();
//            return;
//        }

    }

    @SuppressLint("PrivateResource")
    @Override
    protected void onResume() {
        super.onResume();
//        overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
    }

    @SuppressLint("PrivateResource")
    @Override
    protected void onPause() {
        super.onPause();
//        overridePendingTransition(R.anim.animation_down, android.R.anim.slide_out_right);
    }

    @SuppressLint("PrivateResource")
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        overridePendingTransition(R.anim.animation_down, android.R.anim.slide_out_right);
    }


    @SuppressLint("ResourceType")
    public FragmentTransaction getTransaction() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.abc_fade_in,
                R.anim.abc_fade_out);
        return transaction;
    }


    public static void restartApp(Context context, Activity activity) {
        Intent settingIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        settingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        settingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(settingIntent);
        activity.finish();
    }


//    private boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
//    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Fragment f = getSupportFragmentManager().findFragmentById(R.id.head_container);
//        if (f instanceof MainFragment)
//
//        {
//            if (doubleBackToExitPressedOnce) {
//                super.onBackPressed();
//                return;
//            }
//
//            this.doubleBackToExitPressedOnce = true;
//            Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show();
//
//            new Handler().postDelayed(new Runnable() {
//
//                @Override
//                public void run() {
//                    doubleBackToExitPressedOnce = false;
//                }
//            }, 2000);
//        }else {
//            super.onBackPressed();
//        }
    }


    //Animate List
    public static void animate(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

}
