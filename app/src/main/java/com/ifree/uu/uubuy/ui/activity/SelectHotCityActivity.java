package com.ifree.uu.uubuy.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.listener.GaoDeLocationListener;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.mvp.modle.CityInfoBean;
import com.ifree.uu.uubuy.mvp.persenter.CityInfoPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.SelectHotCityAdapter;
import com.ifree.uu.uubuy.utils.SPUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ifree.uu.uubuy.config.Constant.HOT_CITY_REQUEST;
import static com.ifree.uu.uubuy.config.Constant.SELECT_PROVINCE_REQUEST;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/9/6.
 * Description:
 */
public class SelectHotCityActivity extends CommonActivity{
    private CityInfoPresenter mCityInfoPresenter;
    @BindView(R.id.hot_city)
    RecyclerView recyclerView;
    @BindView(R.id.tv_current_city)
    TextView tvCurrentCity;
    @BindView(R.id.ly_again_location)
    LinearLayout llAgainLocation;
    @BindView(R.id.tv_more_hot_city)
    TextView tvMoreHotCity;
    private List<CityInfoBean.DataBean.HotCity> mList = new ArrayList<>();
    private List<CityInfoBean.DataBean.ProvinceList> provinceList = new ArrayList<>();
    private SelectHotCityAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_select_hot_city;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_select_city_title;
    }

    @Override
    protected void initView() {
        MyApplication.addActivity(SelectHotCityActivity.this);
        mCityInfoPresenter = new CityInfoPresenter(context);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        mAdapter = new SelectHotCityAdapter(context, mList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int position = vh.getAdapterPosition() - 2;
                if (position < 0 | position >= mList.size()) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("area", (Serializable) mList.get(position).getTownList());
                bundle.putString("city", mList.get(position).getCity());
                MyApplication.openActivityForResult(SelectHotCityActivity.this, SelectHotAreaActivity.class, bundle,HOT_CITY_REQUEST);
            }
        });
        initLocation();
    }

    @Override
    protected void initData() {
        mCityInfoPresenter.onCreate();
        mCityInfoPresenter.attachView(mCityInfoView);
        mCityInfoPresenter.getSearchCityInfo("加载中...");
    }


    private ProjectView<CityInfoBean> mCityInfoView = new ProjectView<CityInfoBean>() {
        @Override
        public void onSuccess(CityInfoBean mCityInfoBean) {
            if (mCityInfoBean.getResultCode().equals("1")) {
                ToastUtils.show(mCityInfoBean.getResult());
                return;
            }

            List<CityInfoBean.DataBean.HotCity> hotCityList = mCityInfoBean.getData().getHotCity();
            List<CityInfoBean.DataBean.ProvinceList> provinceLists = mCityInfoBean.getData().getProvinceList();
            if (hotCityList != null && !hotCityList.isEmpty()) {
                mList.addAll(hotCityList);
                mAdapter.notifyDataSetChanged();
            }
            if (provinceLists != null && !provinceLists.isEmpty()) {
                provinceList.addAll(provinceLists);
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.show(result);
        }
    };

    @OnClick({R.id.ly_again_location,R.id.tv_current_city,R.id.tv_more_hot_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_again_location:
                initLocation();
                break;
            case R.id.tv_current_city:
                Intent intent = new Intent();
                intent.setAction("com.ifree.uu.location.changed");
                getApplicationContext().sendBroadcast(intent);
                setResult(Activity.RESULT_OK);
                finish();
                break;
            case R.id.tv_more_hot_city:
                Bundle bundle = new Bundle();
                bundle.putSerializable("province", (Serializable) provinceList);
                MyApplication.openActivityForResult(SelectHotCityActivity.this, SelectProvinceActivity.class, bundle,SELECT_PROVINCE_REQUEST);
                break;
        }
    }

    private void initLocation() {
        GaoDeLocationListener gaoDeLocationListener = new GaoDeLocationListener(context, new GaoDeLocationListener.OnQuestResultListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void success(String result) {
                tvCurrentCity.setText(result);
                Log.i("UUGO", "success: " + SPUtil.getString(context,"city") + "-" + SPUtil.getString(context,"district"));
            }
            @Override
            public void error(String result) {
                tvCurrentCity.setText(result);
            }
        });
        gaoDeLocationListener.startLocation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case HOT_CITY_REQUEST:
                    setResult(Activity.RESULT_OK);
                    finish();
                    break;
                case SELECT_PROVINCE_REQUEST:
                    setResult(Activity.RESULT_OK);
                    finish();
                    break;
            }
        }
    }
}
