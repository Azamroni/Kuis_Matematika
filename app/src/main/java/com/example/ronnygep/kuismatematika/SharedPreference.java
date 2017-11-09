package com.example.ronnygep.kuismatematika;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Galang on 11/3/2017.
 */

public class SharedPreference {
    public static final  String PREFS_NAME="AOP_PREF";
    public static final  String PREFS_KEY="AOP_PREF_list";
    public SharedPreference()
    {
        super();
    }

    public void save(Context context,List<hasil> list){
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor=settings.edit();
        Gson gson=new Gson();
        String jsonchat=gson.toJson(list);
        editor.putString(PREFS_KEY,jsonchat);
        editor.commit();
    }

    public ArrayList<hasil> getValue(Context context)
    {
        SharedPreferences settings;
        List<hasil> list;
        settings=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        if(settings.contains(PREFS_KEY))
        {
            String jsonchat=settings.getString(PREFS_KEY,null);
            Gson gson=new Gson();
            hasil[] chatitem=gson.fromJson(jsonchat, hasil[].class);
            list= Arrays.asList(chatitem);
            list=new ArrayList<hasil>(list);
        }
        else
        {
            return null;
        }

        return  (ArrayList<hasil>) list;


    }

    public void clearSharedPrefence(Context context)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor=settings.edit();
        editor.clear();
        editor.commit();
    }

    public void removeSharedPrefence(Context context)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings=context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        editor=settings.edit();
        editor.remove(PREFS_KEY);
        editor.commit();
    }

}
