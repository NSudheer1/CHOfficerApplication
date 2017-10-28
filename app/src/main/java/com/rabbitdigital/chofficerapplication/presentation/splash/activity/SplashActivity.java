package com.rabbitdigital.chofficerapplication.presentation.splash.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.rabbitdigital.chofficerapplication.R;
import com.rabbitdigital.chofficerapplication.presentation.globalutilities.constants.Constants;
import com.rabbitdigital.chofficerapplication.presentation.globalutilities.constants.GlobalMethods;
import com.rabbitdigital.chofficerapplication.presentation.home.activity.HomeActivity;
import com.rabbitdigital.chofficerapplication.presentation.splash.activity.BaseActvity;

public class SplashActivity extends BaseActvity {


    @Override
    public int setLayoutResuourse() {
        return R.layout.activity_splash;
    }

    @Override
    public void initGUI() {
        Constants.isHomeActivityCalled = false;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    @Override
    public void initData() {
        callActivity();
    }

    private void callActivity() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!Constants.isHomeActivityCalled) {
                    callNextActivity();

                }
            }
        }, Constants.SPLASH_TIME_OUT);
    }

    private void callNextActivity() {

        GlobalMethods.callForWordActivity(SplashActivity.this, HomeActivity.class, null, false, true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Constants.isHomeActivityCalled = true;
        callNextActivity();
        return super.onTouchEvent(event);
    }
}
