package com.docvault.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.docvault.PicListingActivity;
import com.docvault.R;
import com.docvault.pojo.PrescriptionDetailsPojo;

import java.util.List;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.DocAdapterViewHolder>{

    private Context mContext;
    private List<PrescriptionDetailsPojo> prescriptionDetailsPojoList;

    public DocAdapter(Context mContext, List<PrescriptionDetailsPojo> prescriptionDetailsPojoList) {
        this.mContext = mContext;
        this.prescriptionDetailsPojoList = prescriptionDetailsPojoList;
    }

    @NonNull
    @Override
    public DocAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doc, parent, false);
        return new DocAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocAdapterViewHolder holder, int position) {
        PrescriptionDetailsPojo prescriptionDetails = prescriptionDetailsPojoList.get(position);
        holder.tvDoctorsName.setText(prescriptionDetails.getDoctorName());
        holder.tvHospitalName.setText(prescriptionDetails.getHospitalName());
        holder.tvUploadedDateTime.setText(prescriptionDetails.getPrescriptionDate());
        holder.llPrescriptionDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PicListingActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return prescriptionDetailsPojoList.size();
    }

    public class DocAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDoctorsName, tvHospitalName, tvUploadedDateTime;
        private LinearLayout llPrescriptionDetails;
        public DocAdapterViewHolder(View docView) {
            super(docView);
            tvDoctorsName = docView.findViewById(R.id.tvDoctorsName);
            tvHospitalName = docView.findViewById(R.id.tvHospitalName);
            tvUploadedDateTime = docView.findViewById(R.id.tvUploadedDateTime);
            llPrescriptionDetails = docView.findViewById(R.id.llPrescriptionDetails);
        }
    }
}
