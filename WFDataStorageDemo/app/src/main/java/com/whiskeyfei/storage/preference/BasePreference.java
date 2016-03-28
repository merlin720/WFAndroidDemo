package com.whiskeyfei.storage.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *  preference implements base method
 */
public class BasePreference {

	private SharedPreferences mSharedPreferences;

	/**
	 * 构造函数，初始化
	 * 
	 * @param context
	 * @param name
	 */
	public BasePreference(Context context, String name) {
		if (context != null) {
			mSharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
		}
	}

	/**
	 * save string
	 * 
	 * @param key
	 * @param value
	 */
	public void save(String key, String value) {
		if (mSharedPreferences != null) {
			mSharedPreferences.edit().putString(key, value).commit();
		}
	}

	/**
	 * save long
	 * 
	 * @param key
	 * @param value
	 */
	public void save(String key, long value) {
		if (mSharedPreferences != null) {
			mSharedPreferences.edit().putLong(key, value).commit();
		}
	}

	/**
	 * save int
	 * 
	 * @param key
	 * @param value
	 */
	public void save(String key, int value) {
		if (mSharedPreferences != null) {
			mSharedPreferences.edit().putInt(key, value).commit();
		}
	}

	/**
	 * save boolean
	 * 
	 * @param key
	 * @param value
	 */
	public void save(String key, boolean value) {
		if (mSharedPreferences != null) {
			mSharedPreferences.edit().putBoolean(key, value).commit();
		}
	}

	public void save(String key,Float value){
		if(mSharedPreferences != null){
			mSharedPreferences.edit().putFloat(key,value).commit();
		}
	}

	public String getString(String key, String defaultValue) {
		if (mSharedPreferences == null) {
			return defaultValue;
		}
		return mSharedPreferences.getString(key, defaultValue);
	}

	public Float getFloat(String key, Float defaultValue) {
		if (mSharedPreferences == null) {
			return defaultValue;
		}
		return mSharedPreferences.getFloat(key, defaultValue);
	}

	public int getInt(String key, int defaultValue) {
		if (mSharedPreferences == null) {
			return defaultValue;
		}
		return mSharedPreferences.getInt(key, defaultValue);
	}

	public long getLong(String key, long defaultValue) {
		if (mSharedPreferences == null) {
			return defaultValue;
		}
		return mSharedPreferences.getLong(key, defaultValue);
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		if (mSharedPreferences == null) {
			return defaultValue;
		}
		return mSharedPreferences.getBoolean(key, defaultValue);
	}
	
	/**
	 * remove key value
	 * @param key
	 */
	public void remove(String key){
	    if (mSharedPreferences != null) {
            mSharedPreferences.edit().remove(key).commit();
        }
	}
	
	/**
	 * clear all
	 */
	public void clear(){
	    if (mSharedPreferences != null) {
	        mSharedPreferences.edit().clear().commit();
        }
	}

}
