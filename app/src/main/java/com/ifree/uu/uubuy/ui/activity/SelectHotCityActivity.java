package com.ifree.uu.uubuy.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.listener.GaoDeLocationListener;
import com.ifree.uu.uubuy.service.entity.CityInfoEntity;
import com.ifree.uu.uubuy.service.presenter.CityInfoPresenter;
import com.ifree.uu.uubuy.service.view.CityInfoView;
import com.ifree.uu.uubuy.ui.adapter.SelectHotCityAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectHotCityActivity extends BaseActivity implements View.OnClickListener {
    private CityInfoPresenter mCityInfoPresenter;
    @BindView(R.id.hot_city)
    XRecyclerView xRecyclerView;
    private View headView;
    private TextView tvCurrentCity;
    private String mCurrentCity;
    private List<CityInfoEntity.DataBean.HotCity> mList = new ArrayList<>();
    private List<CityInfoEntity.DataBean.ProvinceList> provinceList = new ArrayList<>();
    private SelectHotCityAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_select_hot_city;
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("选择城市");
        mCityInfoPresenter = new CityInfoPresenter(context);
        xRecyclerView.setLayoutManager(new GridLayoutManager(context,4));
        xRecyclerView.setPullRefreshEnabled(false);
        xRecyclerView.setLoadingMoreEnabled(false);
        headView = LayoutInflater.from(context).inflate(R.layout.header_hot_city,null);
        tvCurrentCity = headView.findViewById(R.id.tv_current_city);
        headView.findViewById(R.id.ly_again_location).setOnClickListener(this);
        headView.findViewById(R.id.tv_more_hot_city).setOnClickListener(this);
        if (headView != null) xRecyclerView.addHeaderView(headView);
        mCurrentCity = SPUtil.getString(context,"city");
        tvCurrentCity.setText(mCurrentCity);
        mAdapter = new SelectHotCityAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void loadData() {
        mCityInfoPresenter.onCreate();
        mCityInfoPresenter.attachView(mCityInfoView);
        mCityInfoPresenter.getSearchCityInfo("加载中...");
    }

    private CityInfoView mCityInfoView = new CityInfoView() {
        @Override
        public void onSuccess(CityInfoEntity mCityInfoEntity) {
            Log.i("TAG", "onCompleted: " + new Gson().toJson(mCityInfoEntity));
            if (mCityInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mCityInfoEntity.getResult());
                return;
            }
            List<CityInfoEntity.DataBean.HotCity> hotCityList = mCityInfoEntity.getData().getHotCity();
            List<CityInfoEntity.DataBean.ProvinceList> provinceLists = mCityInfoEntity.getData().getProvinceList();
            if (hotCityList != null && !hotCityList.isEmpty()){
              mList.addAll(hotCityList);
              mAdapter.notifyDataSetChanged();
            }
            if (provinceLists != null && !provinceLists.isEmpty()){
                provinceList.addAll(provinceLists);
            }
        }

        @Override
        public void onError(String result) {

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_again_location:
                initLocation();
                break;
            case R.id.tv_more_hot_city:
                Bundle bundle = new Bundle();
                bundle.putSerializable("province",(Serializable) provinceList);
                MyApplication.openActivity(context,SelectProvinceActivity.class,bundle);
                break;
        }
    }

    private void initLocation(){
        GaoDeLocationListener gaoDeLocationListener = new GaoDeLocationListener(context, new GaoDeLocationListener.OnQuestResultListener() {
            @Override
            public void success(String result) {
                tvCurrentCity.setText(result);
                Log.i("TAG", "success: " + result);
            }

            @Override
            public void error(String result) {
                tvCurrentCity.setText(result);
            }
        });
        gaoDeLocationListener.startLocation();
    }
}
