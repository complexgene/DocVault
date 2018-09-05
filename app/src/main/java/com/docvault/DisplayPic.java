package com.docvault;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DisplayPic extends AppCompatActivity {

    private ImageView ivPrescriptionFullScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pic);
        init();

        String imageURL = getIntent().getStringExtra("IMG_URL");

        Glide.with(this).asBitmap().load(imageURL)
                .into(ivPrescriptionFullScreen);

    }

    private void init() {
        ivPrescriptionFullScreen = findViewById(R.id.ivPrescriptionFullScreen);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
