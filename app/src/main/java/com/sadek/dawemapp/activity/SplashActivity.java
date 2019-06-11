package com.sadek.dawemapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import com.sadek.dawemapp.R;


public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TranslateAnimation animationTrans = new TranslateAnimation(0, 0, 500, 0);
        animationTrans.setInterpolator(new LinearInterpolator());
        animationTrans.setDuration(1000);
        animationTrans.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        findViewById(R.id.logo).startAnimation(animationTrans);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Log.d(SplashActivity.class.getName(), "Token: " + SessionHelper.getPushNotificationToken(SplashActivity.this));
//                if (Paper.book().read(Common.token) != null) {
//                    Common.CURRENT_USSER = Paper.book().read(Common.token);
//                    startActivity(new Intent(SplashActivity.this, OrdersActivity.class));
//                } else
                startActivity(new Intent(SplashActivity.this, IntroActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

