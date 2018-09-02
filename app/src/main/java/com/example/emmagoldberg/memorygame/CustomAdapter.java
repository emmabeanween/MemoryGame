package com.example.emmagoldberg.memorygame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    List<Image> mPics;
    Context context;


    public CustomAdapter(Context mContext, List<Image> pictures) {

        mPics = pictures;
        context = mContext;

    }


    @Override
    public int getCount() {
        return mPics.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void refresh(List<Image> thesepics){

        thesepics = mPics;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View ourView = LayoutInflater.from(context).inflate(R.layout.image_gallery, parent, false);
        ImageView mImageView = (ImageView)ourView.findViewById(R.id.custom_image_view);
        mImageView.setImageResource(mPics.get(position).getImage());
        mImageView.getLayoutParams().width = 300;
        mImageView.getLayoutParams().height = 280;

        if (mPics.get(position).getVisibility() == false){

            mImageView.setVisibility(View.INVISIBLE);
        }


         return ourView;

        //

    }




}


