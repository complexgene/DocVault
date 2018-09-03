package com.docvault;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.docvault.adapter.PicAdapter;
import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;

import java.util.List;

public class PicListingActivity extends AppCompatActivity {

    private RecyclerView rvImageListing;
    List<PrescriptionDetailsPojo> prescriptionDetailsPojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_listing);
        init();
        setUpPicListing();
    }

    private void init() {
        rvImageListing = findViewById(R.id.rvImageListing);
    }

    private void setUpPicListing() {
        prescriptionDetailsPojoList = AppClass.getInstance().getPrescriptionDetailsPojoList();
        PicAdapter picAdapter = new PicAdapter(this, prescriptionDetailsPojoList);
        rvImageListing.setAdapter(picAdapter);
    }
}
