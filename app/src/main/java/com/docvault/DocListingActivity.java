package com.docvault;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.docvault.adapter.DocAdapter;
import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;
import com.docvault.service.PreferenceService;
import com.jackandphantom.blurimage.BlurImage;

import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class DocListingActivity extends AppCompatActivity {

    private RecyclerView rvDocListing;
    private ImageView docListingBG;
    private PreferenceService preferenceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_listing);
        init();
        setUpDocListing();
    }

    /* For supporting custom font */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private void init() {
        rvDocListing = findViewById(R.id.rvDocListing);
        docListingBG = findViewById(R.id.docListingBG);
        preferenceService = new PreferenceService();
//        Bitmap bitmap = BlurImage.with(this).load(R.drawable.bg3).Async(false).getImageBlur();
//        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//        docListingBG.setBackground(drawable);
    }

    private void setUpDocListing() {
        List<PrescriptionDetailsPojo> prescriptionDetailsPojoList = AppClass.getInstance().getUserDetails().getPrescriptionDetailsPojoList();
        DocAdapter docAdapter = new DocAdapter(this, prescriptionDetailsPojoList);
        rvDocListing.setAdapter(docAdapter);
    }

    boolean exitApp = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if(!exitApp) {
//            exitApp = !exitApp;
//            Toast.makeText(this, "Press BACK again to close", Toast.LENGTH_SHORT).show();
//        } else {
//            super.onBackPressed();
//            this.finish();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpDocListing();
    }

    public void openUploadDocIntent(View view) {
        Intent uploadDocIntent = new Intent(this, UploadDocActivity.class);
        startActivity(uploadDocIntent);
    }

    public void userSignOut(View view) {
        preferenceService.setLoggedInStatus(0);
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        this.finish();
    }
}
