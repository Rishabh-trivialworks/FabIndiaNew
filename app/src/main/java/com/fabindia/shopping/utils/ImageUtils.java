package com.fabindia.shopping.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class ImageUtils {
    private static ImageUtils instance;
    private Context mContext;
    public static ImageUtils getInstance(Context context){

        if(instance == null){
            instance = new ImageUtils(context);
        }
        return instance;
    }
    private ImageUtils(Context context){
        mContext =context;
    }
    public void setImage(Context context,ImageView image,int img){
        Glide
                .with(context)
                .load(img)
                .fitCenter()
                .into(image);
    }
    public void setImage(Context context,ImageView image,String img){
        Glide
                .with(context)
                .load(img)
                .centerCrop()
                .into(image);
    }
    public void setImage(Context context, ImageView image, File img){
        Glide
                .with(context)
                .load(img)
                .centerCrop()
                .into(image);
    }
    public void setImage(Context context,AppCompatImageView image, int img){
        Glide
                .with(context)
                .load(img)
                .centerCrop()
                .into(image);
    }

}
