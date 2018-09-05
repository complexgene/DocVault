package com.docvault;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.codekidlabs.storagechooser.StorageChooser;
import com.docvault.adapter.PicAdapter;
import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;
import com.docvault.service.PreferenceService;
import com.docvault.service.ValidationService;
import com.jackandphantom.blurimage.BlurImage;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class PicListingActivity extends AppCompatActivity {

    private RecyclerView rvImageListing;
    List<String> imageURLs;
    private String filePath;
    private PrescriptionDetailsPojo prescriptionDetailsPojo;
    private ValidationService validationService = new ValidationService();
    private PreferenceService preferenceService = new PreferenceService();
    private ImageView ivPicListngBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_listing);
        init();
        setUpPicListing();
    }

    /* For supporting custom font */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private void init() {
        rvImageListing = findViewById(R.id.rvImageListing);
        imageURLs = new ArrayList<>();
        ivPicListngBG = findViewById(R.id.ivPicListingBG);
        Bitmap bitmap = BlurImage.with(this).load(R.drawable.bg1).Async(false).getImageBlur();
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        ivPicListngBG.setBackground(drawable);
    }

    private void setUpPicListing() {
        int itemPos = getIntent().getIntExtra("ITEM_POS", -1);
        if (itemPos == -1) onBackPressed();
        prescriptionDetailsPojo = AppClass.getInstance().getUserDetails().getPrescriptionDetailsPojoList().get(itemPos);
        for(String imageloc : prescriptionDetailsPojo.getPrescriptionImageFiles()) {
            imageURLs.add(imageloc);
        }
        PicAdapter picAdapter = new PicAdapter(this, imageURLs);
        rvImageListing.setAdapter(picAdapter);
    }

    public void chooseFile(View view) {
        // Get Permission
        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if(granted) { permissionGrantedChooseFile(); }
                    else { showPromptAndInformPermissionRequiredForDP(view); }
                });

    }

    private void showPromptAndInformPermissionRequiredForDP(View view) {
        Toast.makeText(this, "Need permission to load file", Toast.LENGTH_SHORT).show();
    }

    public void permissionGrantedChooseFile() {
        // Initialize Builder
        StorageChooser chooser = new StorageChooser.Builder()
                .withActivity(PicListingActivity.this)
                .withFragmentManager(getFragmentManager())
                .withMemoryBar(true)
                .allowCustomPath(true)
                .setType(StorageChooser.FILE_PICKER)
                .filter(StorageChooser.FileType.IMAGES)
                .build();

        // Show dialog whenever you want by
        chooser.show();

        // get path that the user has chosen
        chooser.setOnSelectListener(path -> {
            Log.e("SELECTED_PATH", path);
            filePath = path;
            addImage(filePath);
            setUpPicListing();
        });
    }
    public void addImage(String filePath) {
        if(validationService.checkDetailsFilledTextView(this, filePath)) {
            prescriptionDetailsPojo.getPrescriptionImageFiles().add(filePath);
            preferenceService.storeUserDataDetails();
            Toast.makeText(this, "New Image Added", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}
