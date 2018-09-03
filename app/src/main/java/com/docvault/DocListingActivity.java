package com.docvault;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.docvault.adapter.DocAdapter;
import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;

import java.util.List;

public class DocListingActivity extends AppCompatActivity {

    private RecyclerView rvDocListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_listing);
        init();
        setUpDocListing();
    }

    private void init() {
        rvDocListing = findViewById(R.id.rvDocListing);
    }

    private void setUpDocListing() {
        List<PrescriptionDetailsPojo> prescriptionDetailsPojoList = AppClass.getInstance().getPrescriptionDetailsPojoList();
        DocAdapter docAdapter = new DocAdapter(this, prescriptionDetailsPojoList);
        rvDocListing.setAdapter(docAdapter);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
