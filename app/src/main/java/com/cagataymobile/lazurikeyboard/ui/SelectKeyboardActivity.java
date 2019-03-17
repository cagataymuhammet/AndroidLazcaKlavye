package com.cagataymobile.lazurikeyboard.ui;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.cagataymobile.lazurikeyboard.R;
import com.cagataymobile.lazurikeyboard.util.Utils;

public class SelectKeyboardActivity extends AppCompatActivity {

    Button selectKeyboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_keyboard);
        setTitle("Klavye Seç");

        selectKeyboard =(Button)findViewById(R.id.btnSelectKeyboard);
        selectKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelectKeyboard();
            }
        });
    }


    void setSelectKeyboard() {
        // Invoke input method picker.
        InputMethodManager  mImm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        mImm.showInputMethodPicker();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(Utils.isLazuriKeyboardSelectedDefault(getApplicationContext()))
        {
            Utils.startNextActivity(SelectKeyboardActivity.this);
        }
    }

}
