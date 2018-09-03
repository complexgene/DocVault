package com.docvault;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.docvault.pojo.UserDetails;
import com.pixplicity.easyprefs.library.Prefs;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class LoginActivity extends AppCompatActivity {

    private TextView tvUserName, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        tvUserName = findViewById(R.id.tvLoginPassword);
        tvPassword = findViewById(R.id.tvLoginPassword);
    }

    /* For supporting custom font */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void openRegistrationPage(View view) {
        Intent registrationIntent = new Intent(this, RegistrationActivity.class);
        startActivity(registrationIntent);
    }

    public void login(View view) {
        UserDetails userDetails = new UserDetails(tvUserName.getText().toString(), tvPassword.getText().toString());
        String userDetailsFromPrefs = Prefs.getString(userDetails.getKey(), null);
        if(userDetailsFromPrefs == null) {
            Toast.makeText(this, "Sorry!! The credentials are wrong!!", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, DocListingActivity.class);
            startActivity(intent);
        }
    }
}
