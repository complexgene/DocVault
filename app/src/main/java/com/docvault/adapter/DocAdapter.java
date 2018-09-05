package com.docvault.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.docvault.PicListingActivity;
import com.docvault.R;
import com.docvault.base.AppClass;
import com.docvault.pojo.PrescriptionDetailsPojo;
import com.docvault.pojo.UserDetails;
import com.docvault.service.PreferenceService;

import java.util.List;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.DocAdapterViewHolder>{

    private Context mContext;
    private List<PrescriptionDetailsPojo> prescriptionDetailsPojoList;
    PreferenceService preferenceService;

    public DocAdapter(Context mContext, List<PrescriptionDetailsPojo> prescriptionDetailsPojoList) {
        this.mContext = mContext;
        this.prescriptionDetailsPojoList = prescriptionDetailsPojoList;
        preferenceService = new PreferenceService();
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
        holder.tvSymptoms.setText(prescriptionDetails.getSymptoms());
        holder.llPrescriptionDetails.setOnClickListener(v -> goInsidePicListingActivity(position));
        holder.ivDeleteDocEntry.setOnClickListener(v->{
            prescriptionDetailsPojoList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();
            updateUserDetailsInPrefs();
        });
    }

    private void updateUserDetailsInPrefs() {
        UserDetails userDetails = AppClass.getInstance().getUserDetails();
        userDetails.setPrescriptionDetailsPojoList(prescriptionDetailsPojoList);
        preferenceService.writeUserDetailsToPrefs(userDetails);
    }

    private void goInsidePicListingActivity(int position) {
        Intent intent = new Intent(mContext, PicListingActivity.class);
        intent.putExtra("ITEM_POS", position);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return prescriptionDetailsPojoList.size();
    }

    public class DocAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDoctorsName, tvHospitalName, tvUploadedDateTime, tvSymptoms;
        private LinearLayout llPrescriptionDetails;
        private ImageView ivDeleteDocEntry;
        public DocAdapterViewHolder(View docView) {
            super(docView);
            tvDoctorsName = docView.findViewById(R.id.tvDoctorsName);
            tvHospitalName = docView.findViewById(R.id.tvHospitalName);
            tvUploadedDateTime = docView.findViewById(R.id.tvUploadedDateTime);
            tvSymptoms = docView.findViewById(R.id.tvSymptoms);
            ivDeleteDocEntry = docView.findViewById(R.id.ivDeleteDocEntry);
            llPrescriptionDetails = docView.findViewById(R.id.llPrescriptionDetails);
        }
    }
}
