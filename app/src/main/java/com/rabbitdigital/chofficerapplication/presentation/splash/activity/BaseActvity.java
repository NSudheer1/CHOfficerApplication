package com.rabbitdigital.chofficerapplication.presentation.splash.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setLayoutResuourse());


        initGUI();
        initData();
    }

    public abstract int setLayoutResuourse();

    public abstract void initGUI();

    public abstract void initData();
}
