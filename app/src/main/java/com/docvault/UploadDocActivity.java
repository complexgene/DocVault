package com.docvault;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;
import com.docvault.pojo.UserDetails;
import com.docvault.service.PreferenceService;
import com.docvault.service.ValidationService;
import com.jackandphantom.blurimage.BlurImage;
import com.snatik.storage.Storage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class UploadDocActivity extends AppCompatActivity {

    Storage storage = null;
    private EditText etDoctorsName, etHospitalsName, etUploadDateTime, etSymptoms;
    private ImageView ivUploadDocBG;
    private ValidationService validationService;
    private PreferenceService preferenceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_doc);
        init();
        storage = new Storage(this);
    }
    /* For supporting custom font */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    private void init() {
        ivUploadDocBG = findViewById(R.id.ivUploadDocBG);
        etDoctorsName = findViewById(R.id.etDoctorsName);
        etHospitalsName = findViewById(R.id.etHospitalsName);
        etUploadDateTime = findViewById(R.id.etUploadedTime);
        etSymptoms = findViewById(R.id.etSymptoms);
        Calendar calendar = Calendar.getInstance();
        DateFormat simpleDateFormat = new SimpleDateFormat("MMM dd hh:mm aa");
        etUploadDateTime.setText(simpleDateFormat.format(calendar.getTime()));
        validationService = new ValidationService();
        preferenceService = new PreferenceService();
        Bitmap bitmap = BlurImage.with(this).load(R.drawable.bg3).Async(false).getImageBlur();
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        ivUploadDocBG.setBackground(drawable);
    }

    public void addDocument(View view) {
        String doctorsName = etDoctorsName.getText().toString(),
                hospitalsName = etHospitalsName.getText().toString(),
                uploadDateTime = etUploadDateTime.getText().toString(),
                symptoms = etSymptoms.getText().toString();
        if(validationService.checkDetailsFilledTextView(this, doctorsName, hospitalsName, uploadDateTime, symptoms)) {
            PrescriptionDetailsPojo prescriptionDetailsPojo = new PrescriptionDetailsPojo();
            prescriptionDetailsPojo.setDoctorName(doctorsName);
            prescriptionDetailsPojo.setHospitalName(hospitalsName);
            prescriptionDetailsPojo.setPrescriptionDate(uploadDateTime);
            prescriptionDetailsPojo.setSymptoms(symptoms);
            UserDetails userDetails = AppClass.getInstance().getUserDetails();
            userDetails.getPrescriptionDetailsPojoList().add(prescriptionDetailsPojo);
            preferenceService.writeUserDetailsToPrefs(userDetails);
            preferenceService.updateLastLoggedInUserData(userDetails);
            Toast.makeText(this, "New Entry Added! Add Images now!!", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}