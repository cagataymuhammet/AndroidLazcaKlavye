package com.cagataymobile.lazurikeyboard.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.cagataymobile.lazurikeyboard.R;
import com.cagataymobile.lazurikeyboard.di.Constants;
import com.cagataymobile.lazurikeyboard.util.Utils;

public class InfoActivity extends AppCompatActivity  {
    Button selectKeyboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView tx1=(TextView)findViewById(R.id.txtMc);
        TextView tx2=(TextView)findViewById(R.id.txtLazcaFacebook);
        TextView tx3=(TextView)findViewById(R.id.txtLazcaOrg);
        TextView tx4=(TextView)findViewById(R.id.txtAxeTolikcet);

        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utils.openUrlInChrome(Constants.MUHAMMET_CAGATAY_WEBSITE,InfoActivity.this);
            }
        });

        tx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrlInChrome(Constants.LAZCA_FACEBBOK_WEBSITE,InfoActivity.this);
            }
        });

        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrlInChrome(Constants.LAZCA_ORG_WEBSITE,InfoActivity.this);
            }
        });

        tx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrlInChrome(Constants.ATXE_TOLIKCETI_WEBSITE,InfoActivity.this);
            }
        });

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
        InputMethodManager mImm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        mImm.showInputMethodPicker();
    }


}
