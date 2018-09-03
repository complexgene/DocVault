package com.docvault.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.docvault.R;
import com.docvault.pojo.PrescriptionDetailsPojo;

import java.util.List;

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.PicAdapterViewHolder>{

    private Context mContext;
    private List<PrescriptionDetailsPojo> prescriptionDetailsPojoList;

    public PicAdapter(Context mContext, List<PrescriptionDetailsPojo> prescriptionDetailsPojoList) {
        this.mContext = mContext;
        this.prescriptionDetailsPojoList = prescriptionDetailsPojoList;
    }

    @NonNull
    @Override
    public PicAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic, parent, false);
        return new PicAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PicAdapterViewHolder holder, int position) {
        PrescriptionDetailsPojo prescriptionDetails = prescriptionDetailsPojoList.get(position);

        String imageURL = prescriptionDetails.getPrescriptionImageFileName();

        Glide.with(mContext).load(imageURL).apply(new RequestOptions().placeholder(R.drawable.ic_login))
                .into(holder.ivPrescription);

        holder.ivPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Will zoom the image next time", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return prescriptionDetailsPojoList.size();
    }

    public class PicAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPrescription;
        public PicAdapterViewHolder(View docView) {
            super(docView);
            ivPrescription = docView.findViewById(R.id.ivPrescription);
        }
    }
}
