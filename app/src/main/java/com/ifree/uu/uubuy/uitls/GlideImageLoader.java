package com.ifree.uu.uubuy.uitls;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.config.BaseUrl;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        String imageHttp = BaseUrl.IMAGE_HTTP + path;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(imageHttp).apply(requestOptions).into(imageView);
    }

    public static void imageLoader(Context context, String image, ImageView imageView){
        String imageHttp = BaseUrl.IMAGE_HTTP + image;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(imageHttp).apply(requestOptions).into(imageView);
    }

    public static void headerImageLoader(Context context, String image, ImageView imageView){
        String imageHttp = BaseUrl.HEADER_HTTP + image;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(imageHttp).apply(requestOptions).into(imageView);
    }


    public static void imageLoader(Context context, Uri image, ImageView imageView){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_error);
        requestOptions.error(R.drawable.loading_error);
        Glide.with(context).load(image).apply(requestOptions).into(imageView);
    }
}
