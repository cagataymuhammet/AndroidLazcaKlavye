package com.cagataymobiles.lazcaklayve;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Muhammet ÇAĞATAY on 04/03/19.
 */


public class LazuriInputMethodService extends InputMethodService {


    String[] uppersSet0 = new String[]{ "1","2","3","4","5","6","7","8","9","0"};
    String[] uppersSet1 = new String[]{ "ç̌","ǩ","p̌","t̆","ž","ʒ", "ǯ"};
    String[] uppersSet2 = new String[]{ "q","w","e","r","t","y","u","ı","o","p","ğ","ü"};
    String[] uppersSet3 = new String[]{ "a","s","d","f","g","h","j","k","l","ş","i"};
    String[] uppersSet4 = new String[]{  "z","x","c","v","b","n","m","ö","ç"};

    String[] uppers = new String[]{"A", "B", "C", "Ç", "Ç̌", "D", "E", "F", "G", "Ğ", "H", "X", "İ", "J", "K", "Ǩ", "Q", "L", "M", "N", "O", "P", "P̌", "R", "S", "Ş", "T", "Ť", "U", "V", "Y", "Z", "Ž", "Ʒ", "Ǯ" };

    String[] lowers = new String[]{"a", "b", "c", "ç", "ç̌", "d", "e", "f", "g", "ğ", "h", "x", "i", "j", "k", "ǩ", "q", "l", "m", "n", "o", "p", "p̌", "r", "s", "ş", "t", "t̆", "u", "v", "y", "z", "ž", "ʒ", "ǯ" };
    LinearLayout layout;
    int width = 0;
    int buttonwidth=0;



    @Override
    public View onCreateInputView()
    {

      return getKeyboard();

    }


    View getCustomUI()
    {

        layout = (LinearLayout) getLayoutInflater().inflate(R.layout.klavye_view, null);
        WindowManager wm = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;

        addViews(uppersSet0);
        addViews(uppersSet1);
        addViews(uppersSet2);
        addViews(uppersSet3);
        addViews(uppersSet4);

        return layout;

    }



    void  addViews(String[] arrayOfSet){

        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.klavye_row, null);
        buttonwidth=width/arrayOfSet.length;
        for (int i = 0; i < arrayOfSet.length; i++)
        {
            final TextView myButton = new TextView(this);
            myButton.setGravity(Gravity.CENTER);
            myButton.setBackground(getBaseContext().getResources().getDrawable(R.drawable.key_background));
            myButton.setTextColor(Color.WHITE);
            myButton.setTextSize(30);
            myButton.setText(arrayOfSet[i]);
            myButton.setId(i);
            myButton.setWidth(buttonwidth);
            myButton.setHeight(100);
            linearLayout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    InputConnection ic = getCurrentInputConnection();
                    if (ic == null) return;
                    ic.commitText(String.valueOf(myButton.getText()), 1);

                }
            });
        }

        layout.addView(linearLayout);

    }



    View getKeyboard()
    {

        final KeyboardView keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        final Keyboard keyboard = new Keyboard(this, R.xml.lazca_klavye);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {

                Log.e("CODE",keyCodes[0]+"");

                InputConnection ic = getCurrentInputConnection();
                if (ic == null) return;
                switch (primaryCode) {
                    case Keyboard.KEYCODE_DELETE:
                        CharSequence selectedText = ic.getSelectedText(0);
                        if (TextUtils.isEmpty(selectedText)) {
                            // no selection, so delete previous character
                            ic.deleteSurroundingText(1, 0);
                        } else {
                            // delete the selection
                            ic.commitText("", 1);
                        }
                        ic.deleteSurroundingText(1, 0);
                        break;
                    default:
                        char code = (char) primaryCode;
                        ic.commitText(String.valueOf(code), 1);
                }
            }

            @Override
            public void onPress(int primaryCode) {
            }

            @Override
            public void onRelease(int primaryCode) {

            }

            @Override
            public void onText(CharSequence text) {

            }

            @Override
            public void swipeLeft() {
            }

            @Override
            public void swipeRight() {
            }

            @Override
            public void swipeDown() {
            }

            @Override
            public void swipeUp() {
            }
        });


        return keyboardView;
    }


}





