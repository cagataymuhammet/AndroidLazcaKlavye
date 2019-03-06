package com.cagataymobiles.lazcaklayve;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cagataymobiles.lazcaklayve.di.Constants;

import static android.inputmethodservice.Keyboard.KEYCODE_DONE;

/**
 * Created by Muhammet ÇAĞATAY on 04/03/19.
 */


public class LazuriInputMethodService extends InputMethodService {


    boolean isCapsOpen=false;
    private KeyboardView keyboardView;
    private Keyboard mSymbolsKeyboard;
    private Keyboard mSymbolsShiftedKeyboard;
    private Keyboard mQwertyKeyboard;
    private int mLastDisplayWidth;


    @Override
    public View onCreateInputView()
    {
      return getKeyboard();
    }


    View getKeyboard()
    {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        keyboardView.setKeyboard(mQwertyKeyboard);
        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {

                InputConnection ic = getCurrentInputConnection();
                playClick(primaryCode);

                if(primaryCode==Keyboard.KEYCODE_DELETE)
                {
                    ic.deleteSurroundingText(1, 0);
                }
                else if(primaryCode==KEYCODE_DONE)
                {
                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                }
                else if(primaryCode==Keyboard.KEYCODE_SHIFT)
                {
                    handleShift();
                }
                else if(primaryCode== Keyboard.KEYCODE_MODE_CHANGE)
                {
                    Keyboard current = keyboardView.getKeyboard();
                    if (current == mSymbolsKeyboard || current == mSymbolsShiftedKeyboard) {
                        keyboardView.setKeyboard(mQwertyKeyboard);
                    }
                    else {
                        keyboardView.setKeyboard(mSymbolsKeyboard);
                        mSymbolsKeyboard.setShifted(false);
                    }
                }
                else
                {
                    boolean isLogin=true;
                    for (int i = 0; i < Constants.LAZURURI_INDEX.length; i++) {

                        if(primaryCode==Constants.LAZURURI_INDEX[i])
                        {
                            if(isCapsOpen)
                            {
                                ic.commitText(Constants.LAZURURI_LETERS_UPPERS[i], 1);
                            }
                            else
                            {
                                ic.commitText(Constants.LAZURURI_LETERS_LOWERS[i], 1);
                            }

                            isLogin=false;
                            break;
                        }
                    }

                    if(isLogin)
                    {
                        char code = (char) primaryCode;
                        if (Character.isLetter(code) && isCapsOpen) {
                            code = Character.toUpperCase(code);
                        }

                        String string = String.valueOf(code);
                        ic.commitText(string, 1);
                    }
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


    @Override public void onInitializeInterface() {

        super.onInitializeInterface();
        if (mQwertyKeyboard != null) {
            // Configuration changes can happen after the keyboard gets recreated,
            // so we need to be able to re-build the keyboards if the available
            // space has changed.
            int displayWidth = getMaxWidth();
            if (displayWidth == mLastDisplayWidth) return;
            mLastDisplayWidth = displayWidth;
        }

        mQwertyKeyboard = new Keyboard(this, R.xml.qwerty);
        mSymbolsKeyboard = new Keyboard(this, R.xml.symbols);
        mSymbolsShiftedKeyboard = new Keyboard(this, R.xml.symbols_shift);
    }


    private void playClick(int keyCode) {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }


    private void handleShift() {
        if (keyboardView == null) {
            return;
        }

        Keyboard currentKeyboard = keyboardView.getKeyboard();
        if (mQwertyKeyboard == currentKeyboard) {
            // Alphabet keyboarD
            isCapsOpen=!isCapsOpen;
            keyboardView.setShifted(isCapsOpen || !keyboardView.isShifted());
        } else if (currentKeyboard == mSymbolsKeyboard) {
            mSymbolsKeyboard.setShifted(true);
            keyboardView.setKeyboard(mSymbolsShiftedKeyboard);
            mSymbolsShiftedKeyboard.setShifted(true);
        } else if (currentKeyboard == mSymbolsShiftedKeyboard) {
            mSymbolsShiftedKeyboard.setShifted(false);
            keyboardView.setKeyboard(mSymbolsKeyboard);
            mSymbolsKeyboard.setShifted(false);
        }
    }



}





