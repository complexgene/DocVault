package com.docvault.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.docvault.DisplayPic;
import com.docvault.R;

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

        Glide.with(mContext).load(imageURL)
                .into(holder.ivPrescription);

        holder.ivPrescription.setOnClickListener(v -> openImageDisplayActivity(imageURL));
    }

    private void openImageDisplayActivity(String imgURL) {
        Intent intent = new Intent(mContext, DisplayPic.class);
        intent.putExtra("IMG_URL", imgURL);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return imageURLs.size();
    }

    public class PicAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageButton ivPrescription;
        public PicAdapterViewHolder(View docView) {
            super(docView);
            ivPrescription = docView.findViewById(R.id.ivPrescription);
        }
    }
}
