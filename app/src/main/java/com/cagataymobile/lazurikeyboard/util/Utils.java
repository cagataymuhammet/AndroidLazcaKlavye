package com.cagataymobile.lazurikeyboard.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

import com.cagataymobile.lazurikeyboard.di.Constants;
import com.cagataymobile.lazurikeyboard.ui.EnableKeyboardActivity;
import com.cagataymobile.lazurikeyboard.ui.InfoActivity;
import com.cagataymobile.lazurikeyboard.ui.SelectKeyboardActivity;
import com.cagataymobile.lazurikeyboard.ui.SplashActivity;

import java.util.List;

public class Utils {


   public static boolean  isLazuriKeyboardActive(Context context)
    {
        InputMethodManager imm = (InputMethodManager)  context.getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> mInputMethodProperties = imm.getEnabledInputMethodList();

        boolean status=false;
        for (int i = 0; i < mInputMethodProperties.size(); i++) {

            InputMethodInfo imi = mInputMethodProperties.get(i);
            if (imi.getPackageName().equals("com.cagataymobile.lazcaklavye")) {
                status =true;
                break;
            }
        }

        return status;
    }




    public static  void  startNextActivity(Activity activity)
    {
        if(!Utils.isLazuriKeyboardActive(activity.getApplicationContext()))
        {
            activity.startActivity(new Intent(activity,EnableKeyboardActivity.class));
            activity.finish();
        }
        else if(!Utils.isLazuriKeyboardSelectedDefault(activity.getApplicationContext()))
        {
            activity.startActivity(new Intent(activity,SelectKeyboardActivity.class));
            activity.finish();
        }
        else
        {
            activity.startActivity(new Intent(activity,InfoActivity.class));
            activity.finish();
        }
    }


    public static boolean isLazuriKeyboardSelectedDefault(Context context) {
        final String defaultIME = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        return isThisKeyboardSetAsDefaultIME(defaultIME,Constants.LAZURI_KEYBOARD_PACKAGE_NAME);
    }


    static boolean isThisKeyboardSetAsDefaultIME(String defaultIME, String myPackageName) {
        if (TextUtils.isEmpty(defaultIME))
            return false;

        ComponentName defaultInputMethod = ComponentName.unflattenFromString(defaultIME);
        if (defaultInputMethod.getPackageName().equals(myPackageName)) {
            return true;
        } else {
            return false;
        }
    }


   public static void openUrlInChrome(String url,Activity activity) {

            try {
                Uri uri = Uri.parse("googlechrome://navigate?url="+ url);
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i);
            } catch (ActivityNotFoundException e) {
                Uri uri = Uri.parse(url);
                // Chrome is probably not installed
                // OR not selected as default browser OR if no Browser is selected as default browser
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(i);
            }
    }

}
