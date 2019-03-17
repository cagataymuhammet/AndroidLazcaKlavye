package com.cagataymobile.lazurikeyboard.ui;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.cagataymobile.lazurikeyboard.R;
import com.cagataymobile.lazurikeyboard.util.Utils;

import java.util.List;

public class EnableKeyboardActivity extends AppCompatActivity {

    Button enableKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_keyboard);
        setTitle("Lazca Klavyeyi Aktif Et");

        enableKeyboard =(Button)findViewById(R.id.btnActivate);
        enableKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnableKeyboard();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Utils.startNextActivity(EnableKeyboardActivity.this);
    }

    void setEnableKeyboard()
    {
        startActivityForResult(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS),101);
    }

}
