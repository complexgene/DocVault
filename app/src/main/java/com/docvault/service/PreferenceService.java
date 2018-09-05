package com.docvault.service;

import android.content.Context;
import android.util.Log;

import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;
import com.docvault.pojo.UserDetails;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

public class PreferenceService {
    private Gson gson = new Gson();

    public void writeUserDetailsToPrefs(UserDetails userDetails) {
        Prefs.putString(userDetails.getKey(), gson.toJson(userDetails));
    }

    public UserDetails readUserDetailsFromPrefs(UserDetails userDetails) {
        UserDetails userDetailsFromPrefs = gson.fromJson(Prefs.getString(userDetails.getKey(), null), UserDetails.class);
        return userDetailsFromPrefs;
    }


    public int getLoggedInStatus() {
        return Prefs.getInt("LOGGED_IN", -1);
    }

    public void setLoggedInStatus(int loggedInStatus) {
        Prefs.putInt("LOGGED_IN", loggedInStatus);
    }

    public void updateLastLoggedInUserData(UserDetails userDetails) {
        Prefs.putString("LAST_LOGGED_IN_USER", gson.toJson(userDetails));
    }

    public UserDetails getLastLoggedInUserData() {
        return gson.fromJson(Prefs.getString("LAST_LOGGED_IN_USER", null), UserDetails.class);
    }
}
