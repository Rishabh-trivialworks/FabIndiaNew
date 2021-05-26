package com.fabindia.shopping.storage;

import android.content.Context;


import com.preference.PowerPreference;
import com.preference.Preference;


public class SharedPreferenceUtil {
    private static SharedPreferenceUtil instance;
    private final Preference prefrence;
    private Context context;

    public static SharedPreferenceUtil getInstance(Context context){
        if(instance == null){
            instance = new SharedPreferenceUtil(context);
        }
        return instance;
    }
    private SharedPreferenceUtil(Context context){
        this.context = context;
        prefrence = PowerPreference.getDefaultFile();


    }



    public void clearAllData(){
        prefrence.clear();
    }




}
