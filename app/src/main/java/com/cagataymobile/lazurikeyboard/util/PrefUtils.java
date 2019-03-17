package com.cagataymobile.lazurikeyboard.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {

    public  static final  String PREF_SPLASH_SHOW_COUNT="splash_show_count";

    public static void setIntPref(Context context,String prefName,int value)
    {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt(prefName, value);
        editor.apply();
    }


    public  static  int getIntPref(Context context,String prefName)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getInt(prefName,0);
    }


}
