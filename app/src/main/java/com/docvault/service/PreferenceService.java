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
        AppClass.getInstance().setUserDetails(userDetailsFromPrefs);
        return userDetailsFromPrefs;
    }

    public void storeUserDataDetails() {
        UserDetails userDetails = AppClass.getInstance().getUserDetails();
        writeUserDetailsToPrefs(userDetails);
    }
}
