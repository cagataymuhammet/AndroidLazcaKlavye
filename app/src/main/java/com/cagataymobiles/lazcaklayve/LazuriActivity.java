package com.cagataymobiles.lazcaklayve;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class LazuriActivity extends AppCompatActivity {

    String[] uppers =new String[]{"A","B","C","Ç","Ç̌","D","E","F","G","Ğ","H","X","İ","J","K","Ǩ","Q","L","M","N","O","P","P̌","R","S","Ş","T","Ť","U","V","Y","Z","Ž","Ʒ","Ǯ"};

    String[] lowers =new String[]{"a","b","c","ç","ç̌","d","e","f","g","ğ","h","x","i","j","k","ǩ","q","l","m","n","o","p","p̌","r","s","ş","t","t̆","u","v","y","z","ž","ʒ","ǯ"};


    EditText edtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText =(EditText)findViewById(R.id.edtText);


      //  createLayoutDynamically();

    }


    private void createLayoutDynamically() {


        /*
        for (int i = 0; i < uppers.length; i++) {
            final Button myButton = new Button(this);
            myButton.setText(uppers[i]);
            myButton.setId(i);

            final int id_ = myButton.getId();

            GridLayout layout = (GridLayout) findViewById(R.id.ll_buttons);
            layout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    edtText.setText(edtText.getText().toString()+myButton.getText());

                }
            });
        }

        */
    }
}
