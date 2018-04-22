package team4.softwareengineering.com.cateringsystem.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import team4.softwareengineering.com.cateringsystem.model.HallModel;

public class AppPreferences {

    private static String HALLS = "halls";
    private static String FIRST_TIME = "firstTime";
    private static String UTAID = "utaId";
    // Add uta id to globally access it


    //get name
    public static String getUtaId(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(UTAID, "1001557656");
    }

    //set name
    public static void setUtaId(Context context, String utaID)
    {
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(UTAID, utaID);
        editor.commit();
    }


    public static void setHalls(Context context, ArrayList<HallModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(HALLS, json);
        editor.commit();
    }

    public static ArrayList<HallModel> getHalls(Context context) {
        Gson gson = new Gson();
        ArrayList<HallModel> halls;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonPreferences = sharedPref.getString(HALLS, "");

        Type type = new TypeToken<ArrayList<HallModel>>() {
        }.getType();
        halls = gson.fromJson(jsonPreferences, type);

        return halls;
    }


    /*
*
* To save check if  the app is opened for the first time
*
*
* */
    public static boolean isFirstTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(FIRST_TIME, true);
    }

    public static void setFirstTime(Context context, boolean isFirstTime) {
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putBoolean(FIRST_TIME, isFirstTime);
        editor.commit();
    }


}
