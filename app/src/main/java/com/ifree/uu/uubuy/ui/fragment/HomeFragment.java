package com.ifree.uu.uubuy.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.custom.ImageSlideshow;
import com.ifree.uu.uubuy.custom.MarqueeTextView;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.service.entity.HomeEntity;
import com.ifree.uu.uubuy.service.presenter.HomePresenter;
import com.ifree.uu.uubuy.service.view.HomeView;
import com.ifree.uu.uubuy.ui.activity.FirstClassifyActivity;
import com.ifree.uu.uubuy.ui.adapter.AdTypeAdapter;
import com.ifree.uu.uubuy.ui.adapter.CityADAdapter;
import com.ifree.uu.uubuy.ui.adapter.HomeAdapter;
import com.ifree.uu.uubuy.ui.base.BaseFragment;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/17.
 * Description:
 */
public class HomeFragment extends BaseFragment implements OnBannerClickListener {
    private HomePresenter mHomePresenter;
    @BindView(R.id.xr_home)
    XRecyclerView xRecyclerView;
    private ImageSlideshow imageSlideshow;
    private RecyclerView rc_type,rc_city_ad;
    private MarqueeTextView marqueeTv;
    private Banner mSlideshow;
    private View headView;
    int page = 1;
    private List<HomeEntity.ActivitiesList> mList = new ArrayList<>();
    private List<HomeEntity.BannerList> mBannerList = new ArrayList<>();
    private List<HomeEntity.AdTypeList> mAdTypeList = new ArrayList<>();
    private List<HomeEntity.UURecommendNotice> mNotice = new ArrayList<>();
    private List<HomeEntity.CityADList> mCityADList = new ArrayList<>();
    private List<HomeEntity.RotateADList> mRotateADList = new ArrayList<>();
    private HomeAdapter mAdapter;
    private AdTypeAdapter adTypeAdapter;
    private CityADAdapter cityADAdapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
    @Override
    protected void initView() {
        mHomePresenter = new HomePresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        headView = LayoutInflater.from(context).inflate(R.layout.header_home,null);
        imageSlideshow = headView.findViewById(R.id.img_home_gallery);
        marqueeTv = headView.findViewById(R.id.marqueeTv);
        rc_type = headView.findViewById(R.id.rc_type);
        rc_city_ad = headView.findViewById(R.id.rc_city_ad);
        mSlideshow = headView.findViewById(R.id.img_rotate_gallery);
        mSlideshow.setOnBannerClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        rc_type.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
        rc_city_ad.setLayoutManager(new GridLayoutManager(context,2));
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                mAdapter.notifyDataSetChanged();
                initData();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page ++ ;
                initData();
                xRecyclerView.loadMoreComplete();
                mAdapter.notifyDataSetChanged();
                xRecyclerView.setNoMore(true);
            }
        });

        mAdapter = new HomeAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);
        xRecyclerView.setRefreshing(true);

        adTypeAdapter = new AdTypeAdapter(context,mAdTypeList);
        rc_type.setAdapter(adTypeAdapter);

        cityADAdapter = new CityADAdapter(context,mCityADList);
        rc_city_ad.setAdapter(cityADAdapter);

        rc_type.addOnItemTouchListener(new RecyclerItemTouchListener(xRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
//                int position = vh.getAdapterPosition();
//                if (position < 0 | position >= mAdTypeList.size()){
//                    return;
//                }
               MyApplication.openActivity(context,FirstClassifyActivity.class);

            }
        });

    }

    @Override
    protected void initData() {
        mHomePresenter.onCreate();
        mHomePresenter.attachView(mHomeView);
        mHomePresenter.getSearchHomes(longitude,latitude,townAdCode,page,uid,"加载中...");
    }

    private HomeView mHomeView = new HomeView() {
        @Override
        public void onSuccess(HomeEntity mHomeEntity) {
            if (mHomeEntity.getResult().equals("1")){
                ToastUtils.makeText(context,mHomeEntity.getResultCode());
                return;
            }

            List<HomeEntity.BannerList> bannerLists = mHomeEntity.getBannerList();
            if (bannerLists != null && !bannerLists.isEmpty() && bannerLists.size() > 0){
                if (mBannerList.size() == 0){
                    mBannerList.addAll(bannerLists);
                    initTopViewData(mBannerList);
                }
            }

            List<HomeEntity.AdTypeList> adTypeLists = mHomeEntity.getAdTypeList();
            if (adTypeLists != null && !adTypeLists.isEmpty() && adTypeLists.size() > 0){
                if (mAdTypeList.size() == 0){
                    mAdTypeList.addAll(adTypeLists);
                    adTypeAdapter.notifyDataSetChanged();
                }
            }

            List<HomeEntity.UURecommendNotice> uuRecommendNotices = mHomeEntity.getUuRecommendNotice();
            if (uuRecommendNotices != null && !uuRecommendNotices.isEmpty() && uuRecommendNotices.size() > 0){
                if (mNotice.size() == 0){
                    mNotice.addAll(uuRecommendNotices);
                    initSetNotice(mNotice);
                }
            }

            List<HomeEntity.CityADList> cityADLists = mHomeEntity.getCityADList();
            if (cityADLists != null && !cityADLists.isEmpty() && cityADLists.size() > 0){
                if (mCityADList.size() == 0){
                    mCityADList.addAll(cityADLists);
                    cityADAdapter.notifyDataSetChanged();
                }
            }

            List<HomeEntity.RotateADList> rotateADLists = mHomeEntity.getRotateADList();
            if (rotateADLists != null && !rotateADLists.isEmpty() && rotateADLists.size() > 0){
                if (mRotateADList.size() == 0){
                    mRotateADList.addAll(rotateADLists);
                    initRotateViewData(mRotateADList);
                }
            }
            List<HomeEntity.ActivitiesList> activitiesLists = mHomeEntity.getActivitiesList();
            if (activitiesLists != null && !activitiesLists.isEmpty() && activitiesLists.size() > 0){
                mList.addAll(activitiesLists);
                mAdapter.notifyDataSetChanged();
            }


        }
        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };


    private void initTopViewData(final List<HomeEntity.BannerList> mBannerList) {
        for (int i = 0; i < mBannerList.size(); i++) {
            imageSlideshow.addImageTitle(mBannerList.get(i).getBannerPic());
        }
        imageSlideshow.setDotSpace(18);
        imageSlideshow.setDotSize(20);
        imageSlideshow.setDelay(3000);
        imageSlideshow.setOnItemClickListener(new ImageSlideshow.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        imageSlideshow.commit();
    }

    private void initSetNotice(List<HomeEntity.UURecommendNotice> messageLists) {
        List<String> textArrays = new ArrayList<>();
        for (int i = 0; i < messageLists.size(); i++) {
            textArrays.add(messageLists.get(i).getUuRecommentContent());
        }
//        marqueeTv.setTextArraysAndClickListener(context, textArrays, position -> {
//            Bundle bundle = new Bundle();
//
//            MyApplication.openActivity(context, WebDetailsActivity.class, bundle);
//        });
    }

    private void initRotateViewData(List<HomeEntity.RotateADList> rotateADLists) {
        List<String> imag = new ArrayList<>();
        for (int i = 0; i < rotateADLists.size(); i++) {
            imag.add(rotateADLists.get(i).getRotateADIcon());
        }
        mSlideshow.setImages(imag)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                .start();
    }

    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomePresenter.onStop();
    }


}
