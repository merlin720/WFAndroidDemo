package com.whiskeyfei.storage.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtils {

    public static final String NAME = "PreferencesFile";
    private static final int MODE = Context.MODE_PRIVATE;

    private PreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * save defalut name
     */
    public static boolean saveString(Context context, String key, String value) {
        return saveString(context, NAME, key, value);
    }

    public static boolean saveInt(Context context, String key, int value) {
        return saveInt(context, NAME, key, value);
    }

    public static boolean saveLong(Context context, String key, long value) {
        return saveLong(context, NAME, key, value);
    }

    public static boolean saveFloat(Context context, String key, float value) {
        return saveFloat(context, NAME, key, value);
    }

    public static boolean saveBoolean(Context context, String key, boolean value) {
        return saveBoolean(context, NAME, key, value);
    }


    /**
     * delault local
     */
    public static String getString(Context context, String key, String defaultValue) {
        return getString(context, NAME, key, defaultValue);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getInt(context, NAME, key, defaultValue);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        return getLong(context, NAME, key, defaultValue);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        return getFloat(context, NAME, key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getBoolean(context, NAME, key, defaultValue);
    }


    /*******************************************************************************************************/

    /**
     * save your setting preferences file name!
     */

    public static boolean saveFloat(Context context, String name, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static boolean saveString(Context context, String name, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean saveInt(Context context, String name, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static boolean saveLong(Context context, String name, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(NAME, MODE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static boolean saveBoolean(Context context, String name, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }


    /**
     * get Desired preferences file.
     */

    public static String getString(Context context, String name, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        return settings.getString(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String name, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        return settings.getBoolean(key, defaultValue);
    }

    public static int getInt(Context context, String name, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        return settings.getInt(key, defaultValue);
    }

    public static long getLong(Context context, String name, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        return settings.getLong(key, defaultValue);
    }

    public static float getFloat(Context context, String name, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(name, MODE);
        return settings.getFloat(key, defaultValue);
    }
}
