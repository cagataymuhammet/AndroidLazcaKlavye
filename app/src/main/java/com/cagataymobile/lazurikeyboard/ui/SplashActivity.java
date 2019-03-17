package com.cagataymobile.lazurikeyboard.ui;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cagataymobile.lazurikeyboard.R;
import com.cagataymobile.lazurikeyboard.util.PrefUtils;
import com.cagataymobile.lazurikeyboard.util.Utils;

import java.util.List;

import in.codeshuffle.typewriterview.TypeWriterView;


public class SplashActivity extends AppCompatActivity {

    Button btnNext;
    ImageView imgIcon;
    TypeWriterView typeWriterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }




    void  init()
    {
        //Create Object and refer to layout view
        typeWriterView=(TypeWriterView)findViewById(R.id.typeWriterView);
        typeWriterView.setVisibility(View.GONE);
        imgIcon =(ImageView)findViewById(R.id.imgIcon);

        btnNext=(Button)findViewById(R.id.btnNext) ;
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utils.startNextActivity(SplashActivity.this);
            }
        });

        CountDownTimer countDownTimer =new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                int count= PrefUtils.getIntPref(getApplicationContext(),PrefUtils.PREF_SPLASH_SHOW_COUNT);

                if(count<2)
                {
                    imgIcon.setVisibility(View.GONE);
                    typeWriterView.setVisibility(View.VISIBLE);
                    count++;
                    PrefUtils.setIntPref(getApplicationContext(),PrefUtils.PREF_SPLASH_SHOW_COUNT,count);
                    initTypeWriter();
                }
                else
                {
                   Utils.startNextActivity(SplashActivity.this);
                }
            }

        }.start();
    }



    void initTypeWriter()
    {

        //Setting each character animation delay
        typeWriterView.setDelay(1100);

        //Setting music effect On/Off
        typeWriterView.setWithMusic(true);

        //Animating Text
        typeWriterView.animateText(getResources().getString(R.string.intro));

        typeWriterView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().length()>=167)
                {
                    btnNext.setVisibility(View.VISIBLE);
                }
            }
        });

        //Remove Animation. This is required to be called when you want to minimize the app while animation is going on. Call this in onPause() or onStop()
        // typeWriterView.removeAnimation();
    }

}
