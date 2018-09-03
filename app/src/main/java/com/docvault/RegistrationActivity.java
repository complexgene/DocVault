package com.docvault;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.docvault.pojo.UserDetails;
import com.pixplicity.easyprefs.library.Prefs;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class RegistrationActivity extends AppCompatActivity {

    private TextView tvUserName, tvPassWord, tvConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
    }

    /* For supporting custom font */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private void init() {
        tvUserName = findViewById(R.id.tvUserName);
        tvPassWord = findViewById(R.id.tvPassword);
        tvConfirmPassword = findViewById(R.id.tvConfirmPassword);
    }

    public void createAccount(View view) {
        String userName = tvUserName.getText().toString(),
                passWord = tvPassWord.getText().toString(),
                confirmPassword = tvConfirmPassword.getText().toString();

        if(passWord.equals(confirmPassword)) {
            UserDetails userDetails = new UserDetails(userName, passWord);
            Prefs.putString(userDetails.getKey(), userDetails.getValue());
            Toast.makeText(this, "!! New User Added !!", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(null, 2000);
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        }
    }
}
