package com.ifree.uu.uubuy.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.listener.MarqueeTextViewClickListener;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/20.
 * Description:
 */
public class MarqueeTextView extends LinearLayout {
    private Context mContext;
    private ViewFlipper viewFlipper;
    private View marqueeTextView;
    private List<String> textArrays;
    private MarqueeTextViewClickListener marqueeTextViewClickListener;
    public MarqueeTextView(Context context) {
        super(context);
        mContext = context;
        initBasicView();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBasicView();
    }

    public void setTextArraysAndClickListener(Context context ,List<String> textArrays, MarqueeTextViewClickListener marqueeTextViewClickListener) {//1.设置数据源；2.设置监听回调（将textView点击事件传递到目标界面进行操作）
        this.mContext = context;
        this.textArrays = textArrays;
        this.marqueeTextViewClickListener = marqueeTextViewClickListener;
        initMarqueeTextView(textArrays, marqueeTextViewClickListener);
    }


    public void initBasicView() {//加载布局，初始化ViewFlipper组件及效果
        marqueeTextView = LayoutInflater.from(mContext).inflate(R.layout.marquee_textview_layout, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(marqueeTextView, layoutParams);
        viewFlipper = (ViewFlipper) marqueeTextView.findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));//设置上下的动画效果（自定义动画，所以改左右也很简单）
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        viewFlipper.startFlipping();
    }



    public void initMarqueeTextView(List<String> textArrays, final MarqueeTextViewClickListener marqueeTextViewClickListener) {
        if (textArrays.size() == 0) {
            viewFlipper.removeAllViews();
            return;
        }
        int i = 0;
        viewFlipper.removeAllViews();
        while (i < textArrays.size()) {
            TextView textView = new TextView(mContext);
            textView.setText(textArrays.get(i));
            textView.setTextSize(12);
            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(mContext.getResources().getColorStateList(R.color.text_fraction_red));
            final int finalI = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    marqueeTextViewClickListener.onClick(finalI);
                }
            });
            LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            viewFlipper.addView(textView, lp);
            i++;
        }
    }

    public void releaseResources() {
        if (marqueeTextView != null) {
            if (viewFlipper != null) {
                viewFlipper.stopFlipping();
                viewFlipper.removeAllViews();
                viewFlipper = null;
            }
            marqueeTextView = null;
        }
    }
}