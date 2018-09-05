package com.docvault;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.docvault.base.AppClass;
import com.docvault.pojo.UserDetails;
import com.docvault.service.PreferenceService;
import com.docvault.service.ValidationService;
import com.jackandphantom.blurimage.BlurImage;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class LoginActivity extends AppCompatActivity {

    private TextView tvUserName, tvPassword, tvConfirmPassword, tvSignIn, tvRegister, tvWantToregister;
    private View vSignInUnderLine, vRegisterUnderLine;
    private FrameLayout flConfirmPwdSection;
    private Button btnContinueOrRegister;
    private ImageView logInBG;
    private ValidationService validationService = new ValidationService();
    private PreferenceService preferenceService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        tvSignIn.setOnClickListener(v -> {
            setSignInViewStyles();
        });

        tvRegister.setOnClickListener(v -> {
            setRegisterViewStyles();
        });

        tvWantToregister.setOnClickListener(v -> {
            setRegisterViewStyles();
        });
    }

    private void setRegisterViewStyles() {
        tvUserName.setText("");
        tvPassword.setText("");
        vSignInUnderLine.setVisibility(View.GONE);
        tvWantToregister.setVisibility(View.GONE);
        vRegisterUnderLine.setVisibility(View.VISIBLE);
        flConfirmPwdSection.setVisibility(View.VISIBLE);
        btnContinueOrRegister.setText(R.string.btnTxtregister);
    }

    private void setSignInViewStyles() {
        tvUserName.setText("");
        tvPassword.setText("");
        vSignInUnderLine.setVisibility(View.VISIBLE);
        tvWantToregister.setVisibility(View.VISIBLE);
        vRegisterUnderLine.setVisibility(View.GONE);
        flConfirmPwdSection.setVisibility(View.GONE);
        btnContinueOrRegister.setText(R.string.btnTxtContinue);
    }

    private void init() {
        tvUserName = findViewById(R.id.tvUserName);
        tvPassword = findViewById(R.id.tvPassword);
        tvConfirmPassword = findViewById(R.id.tvConfirmPassword);
        tvSignIn = findViewById(R.id.tvSignIn);
        tvRegister = findViewById(R.id.tvRegister);
        logInBG = findViewById(R.id.logInBG);
        tvWantToregister = findViewById(R.id.tvWantToregister);
        vSignInUnderLine = findViewById(R.id.vSignInUnderLine);
        flConfirmPwdSection = findViewById(R.id.flConfirmPwdSection);
        btnContinueOrRegister = findViewById(R.id.btnContinueOrRegister);
        vRegisterUnderLine = findViewById(R.id.vRegisterUnderLine);
        preferenceService = new PreferenceService();
        Bitmap bitmap = BlurImage.with(this).load(R.drawable.bg3).Async(false).getImageBlur();
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        logInBG.setBackground(drawable);
    }

    /* For supporting custom font */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    public void login(View view) {
        String currentButtonText = btnContinueOrRegister.getText().toString();
        switch (currentButtonText) {
            case "Continue" : {
                tryLogin();
            }
            case "Register" : {
                tryRegister();
            }
        }

    }

    private void tryRegister() {
        String userName = tvUserName.getText().toString(),
                passWord = tvPassword.getText().toString(),
                confirmPassword = tvConfirmPassword.getText().toString();

        if(validationService.checkDetailsFilledTextView(this, userName, passWord, confirmPassword) && validationService.checkPasswordEquality(this, passWord, confirmPassword)) {
            UserDetails userDetails = new UserDetails(userName, passWord);
            preferenceService.writeUserDetailsToPrefs(userDetails);
            Toast.makeText(this, "!! New User Added !!", Toast.LENGTH_LONG).show();
            new Handler().postDelayed(() ->  setSignInViewStyles(), 1500);
        }
    }

    private void tryLogin() {
        UserDetails userDetails = new UserDetails(tvUserName.getText().toString(), tvPassword.getText().toString());
        if(validationService.checkIfUserAvailable(this, userDetails)) {
            UserDetails retrievedUserDetailsFromPrefs = preferenceService.readUserDetailsFromPrefs(userDetails);
            AppClass.getInstance().setUserDetails(retrievedUserDetailsFromPrefs);
            preferenceService.setLoggedInStatus(1);
            preferenceService.updateLastLoggedInUserData(retrievedUserDetailsFromPrefs);
            Intent intent = new Intent(this, DocListingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}