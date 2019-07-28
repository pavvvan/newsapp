package com.jspavan.newsapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class ProjectUtility extends Application {

    private static ProjectUtility utilityInstance;

    public static synchronized ProjectUtility getUtilityInstance() {
        if (null == utilityInstance) {
            utilityInstance = new ProjectUtility();
        }
        return utilityInstance;
    }

    public void setPreference(Context context, String key, String value) {

        SharedPreferences.Editor newsPrefEdit = context.getSharedPreferences("PavanNewsappPreference", Context.MODE_PRIVATE).edit();
        newsPrefEdit.putString(key, value);
        newsPrefEdit.apply();

    }

    public String getPreference(Context context, String key) {

        SharedPreferences newsGetpref = context.getSharedPreferences("PavanNewsappPreference", Context.MODE_PRIVATE);
        String getPref = newsGetpref.getString(key, "");
        return getPref;
    }

    public void deletePreference(Context context) {

        SharedPreferences.Editor newsPrefClear = context.getSharedPreferences("PavanNewsappPreference", Context.MODE_PRIVATE).edit();
        newsPrefClear.clear();
        newsPrefClear.apply();
    }


}
