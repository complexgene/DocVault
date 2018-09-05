package com.docvault;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;
import com.docvault.pojo.UserDetails;
import com.docvault.service.PreferenceService;
import com.docvault.service.ValidationService;
import com.snatik.storage.Storage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class UploadDocActivity extends AppCompatActivity {

    Storage storage = null;
    private EditText etDoctorsName, etHospitalsName, etUploadDateTime;
    private ValidationService validationService;
    private PreferenceService preferenceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_doc);
        init();
        storage = new Storage(this);
    }

    private void init() {
        etDoctorsName = findViewById(R.id.etDoctorsName);
        etHospitalsName = findViewById(R.id.etHospitalsName);
        etUploadDateTime = findViewById(R.id.etUploadedTime);
        Calendar calendar = Calendar.getInstance();
        DateFormat simpleDateFormat = new SimpleDateFormat("MMM dd hh:mm aa");
        etUploadDateTime.setText(simpleDateFormat.format(calendar.getTime()));
        validationService = new ValidationService();
        preferenceService = new PreferenceService();
    }

    public void addDocument(View view) {
        String doctorsName = etDoctorsName.getText().toString(),
                hospitalsName = etHospitalsName.getText().toString(),
                uploadDateTime = etUploadDateTime.getText().toString();
        if(validationService.checkDetailsFilledTextView(this, doctorsName, hospitalsName, uploadDateTime)) {
            PrescriptionDetailsPojo prescriptionDetailsPojo = new PrescriptionDetailsPojo();
            prescriptionDetailsPojo.setDoctorName(doctorsName);
            prescriptionDetailsPojo.setHospitalName(hospitalsName);
            prescriptionDetailsPojo.setPrescriptionDate(uploadDateTime);
            UserDetails userDetails = AppClass.getInstance().getUserDetails();
            userDetails.getPrescriptionDetailsPojoList().add(prescriptionDetailsPojo);
            preferenceService.storeUserDataDetails();
            Toast.makeText(this, "New Entry Added! Add Images now!!", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}