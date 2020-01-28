package com.example.e_commgraphe.model;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commgraphe.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
    }


//set details to recyler view\
    public void setDetails(Context cxt,String title,String description,String image){

        TextView mTitleView = mView.findViewById(R.id.rTextView);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionView);
        ImageView mImageTV = mView.findViewById(R.id.rImageView);

        mTitleView.setText(title);
        mDetailTv.setText(description);
        Picasso.get().load(image).into(mImageTV);







    }
}
