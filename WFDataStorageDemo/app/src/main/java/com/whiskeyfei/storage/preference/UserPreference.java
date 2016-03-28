package com.whiskeyfei.storage.preference;

import android.content.Context;

import com.whiskeyfei.storage.bean.User;

/**
 * 特殊实现，只针对User信息
 */
public class UserPreference {

    private static final String TAG = "UserPreference";
    private static final String ID = "id";
    private static final String KEYNAME = "name";
    private static final String MONEY = "money";

    private static String NAME = "user_info";

    public static void saveUser(Context context, User user) {
        BasePreference preference = new BasePreference(context, NAME);
        preference.save(ID, user.getId());
        preference.save(KEYNAME, user.getName());
        preference.save(MONEY, user.getMoney());
    }

    public static User getUser(Context context) {
        User user = new User();
        BasePreference preference = new BasePreference(context, NAME);
        user.setId(preference.getInt(ID, -1));
        user.setName(preference.getString(KEYNAME, ""));
        user.setMoney(preference.getLong(MONEY, -1));
        return user;
    }

}
