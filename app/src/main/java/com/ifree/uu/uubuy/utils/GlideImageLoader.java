package com.ifree.uu.uubuy.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.config.Constant;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(path).apply(requestOptions).into(imageView);
    }

    public static void imageLoader(Context context, String image, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(getImagePath(context, image)).apply(requestOptions).into(imageView);
    }

    public static void headerImageLoader(Context context, String image, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(getImagePath(context, image)).apply(requestOptions).into(imageView);
    }


    @SuppressLint("NewApi")
    public static String getImagePath(Context context, String image) {
        String imagePath = image;
        if (SPUtil.getString(context,"pictureModel").equals("2")){
            imagePath = image;
        }else if (SPUtil.getString(context,"pictureModel").equals("3")) {
            switch (NetUtil.getNetWorkStatus(context)) {
                case Constant.NETWORK_WIFI:
                case Constant.NETWORK_CLASS_4_G:
                    imagePath = image + "?w=1200";
                    break;
                case Constant.NETWORK_CLASS_3_G:
                    imagePath = image + "?w=1000";
                    break;
                case Constant.NETWORK_CLASS_2_G:
                    imagePath = image + "?w=500";
                    break;
                case Constant.NETWORK_CLASS_UNKNOWN:
                    imagePath = image + "?w=800";
                    break;
            }
        }else {
            switch (NetUtil.getNetWorkStatus(context)) {
                case Constant.NETWORK_WIFI:
                case Constant.NETWORK_CLASS_4_G:
                    imagePath = image + "?w=1200";
                    break;
                case Constant.NETWORK_CLASS_2_G:
                    imagePath = image + "?w=500";
                    break;
                case Constant.NETWORK_CLASS_3_G:
                    imagePath = image + "?w=1000";
                    break;
                case Constant.NETWORK_CLASS_UNKNOWN:
                    imagePath = image;
                    break;
            }
        }
        return imagePath;
    }
}
