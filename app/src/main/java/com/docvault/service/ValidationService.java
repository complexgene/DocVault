package com.docvault.service;

import android.content.Context;
import android.widget.Toast;

import com.docvault.base.AppClass;
import com.docvault.pojo.UserDetails;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

public class ValidationService {
    private Gson gson = new Gson();
    public boolean checkDetailsFilledTextView(Context context, String... values) {
        for(String value : values) {
            if(value.toString().equals("")) {
                Toast.makeText(context, "Please fill in all the details", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
    public boolean checkPasswordEquality(Context context, String pass1, String pass2) {
        if(!pass1.equals(pass2)) {
            Toast.makeText(context, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean checkIfUserAvailable(Context context, UserDetails userDetails) {
        String userDetailsFromPrefs = Prefs.getString(userDetails.getKey(), null);
        if(userDetailsFromPrefs != null) {
            return true;
        }
        else {
            Toast.makeText(context, "User Details doesn't exists", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
