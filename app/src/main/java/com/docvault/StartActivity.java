package com.docvault;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jackandphantom.blurimage.BlurImage;
import com.pixplicity.easyprefs.library.Prefs;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class StartActivity extends AppCompatActivity {

    private ImageView start_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        openNextActivity();
    }

    private void init() {
        start_bg = findViewById(R.id.start_bg);
        Bitmap bitmap = BlurImage.with(this).load(R.drawable.docvault_splash2).Async(false).getImageBlur();
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        start_bg.setBackground(drawable);
    }

    /* For supporting custom font */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private void openNextActivity() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent logInIntent = new Intent(getApplicationContext(), LoginActivity.class);
            logInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            logInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            logInIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(logInIntent);
        }, 4000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
