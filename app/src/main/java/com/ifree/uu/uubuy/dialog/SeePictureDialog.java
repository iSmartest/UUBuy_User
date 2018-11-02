package com.ifree.uu.uubuy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.custom.photoView.PhotoView;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;

import java.util.List;


/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/27.
 * Description: 图片查看器
 */

public class SeePictureDialog extends Dialog {
    private Context mContext;
    private List<String> mList;
    private ViewPager mPager;
    private ImageView mClose;
    private LinearLayout mLlPicture;
    private TextView mTotalItem;
    private ImageAdapter mImageAdapter;
    private boolean isVISIBLE = true;
    private int defPosition; // 默认显示的图片位置
    public SeePictureDialog(Context context, List<String> mList,int defPosition) {
        super(context);
        this.mContext = context;
        this.mList = mList;
        this.defPosition = defPosition;
        setContentView(R.layout.see_picture_tips);
        initView();
        try {
            int dividerID = mContext.getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = findViewById(dividerID);
            divider.setBackgroundColor(Color.TRANSPARENT);
        } catch (Exception e) {
            //上面的代码，是用来去除Holo主题的蓝色线条
            e.printStackTrace();
        }
    }

    private void initView() {
        mPager = findViewById(R.id.see_pager);
        mLlPicture = findViewById(R.id.ll_see_picture);
        mTotalItem = findViewById(R.id.text_total_item);
        mTotalItem.setText((defPosition + 1)+ "/" + mList.size());
        mPager.setPageMargin((int) (mContext.getResources().getDisplayMetrics().density * 15));
        mImageAdapter = new ImageAdapter(mContext, mList);
        mPager.setAdapter(mImageAdapter);
        mPager.setCurrentItem(defPosition);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTotalItem.setText((position + 1) + "/" + mList.size());
            }
        });
        findViewById(R.id.iv_see_picture_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public class ImageAdapter extends PagerAdapter {
        private PhotoView photo_view;
        private Context mContext;
        private List<String> sellPointdscs;

        public ImageAdapter(Context mContext, List<String> sellPointdscs) {
            this.mContext = mContext;
            this.sellPointdscs = sellPointdscs;
        }

        @Override
        public int getCount() {
            return sellPointdscs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_see_picture, null);
            photo_view = view.findViewById(R.id.iv_see_picture);
            photo_view.enable();
            photo_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            photo_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            photo_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isVISIBLE) {
                        mLlPicture.setVisibility(View.GONE);
                        isVISIBLE = false;
                    } else {
                        mLlPicture.setVisibility(View.VISIBLE);
                        isVISIBLE = true;
                    }
                }
            });
            GlideImageLoader.imageLoader(mContext, sellPointdscs.get(position), photo_view);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void show() {
        windowDeploy(0, 0);
        super.show();
    }

    public void windowDeploy(int x, int y) {
        Window window = getWindow(); // 得到对话框
        window.setBackgroundDrawableResource(R.drawable.transpant_bg); // 设置对话框背景为透明
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = (mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels);
        wl.x = x;
        wl.y = y;
        wl.gravity = Gravity.CENTER;// 设置重力
        window.setAttributes(wl);
    }
}

