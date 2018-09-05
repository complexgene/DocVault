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
import com.docvault.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.PicAdapterViewHolder>{

    private Context mContext;
    private List<String> imageURLs;

    public PicAdapter(Context mContext, List<String> imageURLs) {
        this.mContext = mContext;
        this.imageURLs = imageURLs;
    }

    @NonNull
    @Override
    public PicAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic, parent, false);
        return new PicAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PicAdapterViewHolder holder, int position) {
        String imageURL = imageURLs.get(position);

        Glide.with(mContext).asBitmap().load(imageURL)
                .into(holder.ivPrescription);

        holder.ivPrescription.setOnClickListener(v -> Toast.makeText(mContext, "Will zoom the image next time", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return imageURLs.size();
    }

    public class PicAdapterViewHolder extends RecyclerView.ViewHolder {
        private CircularImageView ivPrescription;
        public PicAdapterViewHolder(View docView) {
            super(docView);
            ivPrescription = docView.findViewById(R.id.ivPrescription);
            ivPrescription.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
        }
    }
}
