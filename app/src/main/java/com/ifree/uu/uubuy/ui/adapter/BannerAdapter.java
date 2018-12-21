package com.ifree.uu.uubuy.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.mvp.entity.SecondActivitiesEntity;
import com.ifree.uu.uubuy.ui.activity.ActivitiesDetailsActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.TimeFormatUtils;

import java.util.List;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/12/20.
 * Description:
 */

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private List<SecondActivitiesEntity.DataBean.MarketListInfo> data;
    public BannerAdapter(Context context, List<SecondActivitiesEntity.DataBean.MarketListInfo> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.item_recyclerview, null);
        RelativeLayout ll_banner = view.findViewById(R.id.ll_banner);
        TextView mTime = view.findViewById(R.id.tv_market_time);
        ImageView adapter_iv_icon = view.findViewById(R.id.tv_market_activities_picture);
        String img = data.get(position).getActivitiesPic();
        GlideImageLoader.imageLoader(context,img,adapter_iv_icon);
        switch (data.get(position).getIsOver()) {
            case "0":
                mTime.setText("活动已结束");
                break;
            case "1":
            case "2":
                mTime.setText("活动时间:" + TimeFormatUtils.modifyDataFormat2(data.get(position).getActivitiesTime()));
                break;
            case "3":
                mTime.setText("暂无活动");
                break;
        }
        ll_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getIsOver().equals("1") || data.get(position).getIsOver().equals("2")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("marketId", data.get(position).getMarketId());
                    bundle.putString("marketName", data.get(position).getMarketName());
                    bundle.putString("type", data.get(position).getType());
                    bundle.putString("advId", data.get(position).getAdvId());
                    MyApplication.openActivity(context, ActivitiesDetailsActivity.class, bundle);
                }
            }
        });
        container.addView(view);
        return view;
    }

}
