package com.ifree.uu.uubuy.uitls;

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
        Glide.with(context).load(getImagePath(context,(String)path)).apply(requestOptions).into(imageView);
    }

    public static void imageLoader(Context context, String image, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(getImagePath(context,image)).apply(requestOptions).into(imageView);
    }

    public static void headerImageLoader(Context context, String image, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(getImagePath(context,image)).apply(requestOptions).into(imageView);
    }

    public static void adTypeImageLoader(Context context, String image, ImageView imageView, String type) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        switch (type) {
            case "1":
                requestOptions.error(R.drawable.zongheshangchang_home);
                break;
            case "2":
                requestOptions.error(R.drawable.zonghechangshi_home);
                break;
            case "3":
                requestOptions.error(R.drawable.jiajujiancai_home);
                break;
            case "4":
                requestOptions.error(R.drawable.qichezhanting_home);
                break;
            case "5":
                requestOptions.error(R.drawable.pinpaizhanshi_home);
                break;
            case "6":
                requestOptions.error(R.drawable.jiaoyu_home);
                break;
        }
        Glide.with(context).load(image).apply(requestOptions).into(imageView);
    }

    @SuppressLint("NewApi")
    public static String getImagePath(Context context, String image){
        String imagePath = image;
        switch (GlobalMethod.getNetState(context)){
            case Constant.NetState.Mobile:
                imagePath = image + "?w=500";
                break;
            case Constant.NetState.WIFI:
                imagePath = image;
                break;
        }
        return imagePath;
    }
}
