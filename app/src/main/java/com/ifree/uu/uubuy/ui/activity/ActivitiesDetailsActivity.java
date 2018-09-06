package com.ifree.uu.uubuy.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/24.
 * Description:
 */
public class ActivitiesDetailsActivity extends BaseActivity {
    private String url = "http://wap.jnsbyk.com/uploads/allimg/160808/10-160PQ210031X.jpg";
    @BindView(R.id.ll_activities_background)
    RelativeLayout mBackground;
    @BindView(R.id.iv_activities_dec_picture)
    ImageView mPicture;
    @BindView(R.id.tv_enter_for_activities)
    TextView tvEnter;
    private String rgb;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_activities_details;
    }
    @Override
    protected void loadData() {
        Glide.with(this).load(url).listener(GlidePalette.with(url)
                .use(GlidePalette.Profile.VIBRANT_LIGHT)
                .crossfade(true)
                .intoCallBack(new BitmapPalette.CallBack() {
                    @Override
                    public void onPaletteLoaded(@Nullable Palette palette) {
                        Palette.Swatch vibrant = palette.getVibrantSwatch();
                        rgb = changeColor(vibrant.getRgb());
                        mBackground.setBackgroundColor(Color.parseColor(rgb));
                    }
                })
        ).into(mPicture);

    }

    @Override
    protected void initView() {
        hideBack(6);
        setTitleText("奥德莱斯上地店");
        setRightText("收藏");
    }
    @OnClick({R.id.tv_base_rightText,R.id.tv_enter_for_activities})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_base_rightText:
                ToastUtils.makeText(context,"你点击了收藏");
                break;
            case R.id.tv_enter_for_activities:
                Bundle bundle = new Bundle();
                bundle.putString("color",rgb);
                MyApplication.openActivity(context,EnterForActivitiesActivity.class,bundle);
                break;
        }

    }

    private String changeColor(int rgb) {
        int rgbR = (rgb & 0xff0000) >> 16;
        int rgbG = (rgb & 0xff00) >> 8;
        int rgbB = (rgb & 0xff);
        String r = checkColorValue(rgbR);
        String g = checkColorValue(rgbG);
        String b = checkColorValue(rgbB);
        String str = "#26"+r+g+b;
        return str;
    }
    private String checkColorValue(int value){
        String str = "";
        if(value<16){
            str ="0" + Integer.toHexString(value);
            return str;
        }
        return Integer.toHexString(value);
    }
}
