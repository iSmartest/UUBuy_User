package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.CommodityInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.CommodityInfoPresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.adapter.CarCommodityAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/19 0019
 * Description:
 */
public class CarCommodityActivity extends BaseActivity {
    @BindView(R.id.xr_car)
    XRecyclerView xRecyclerView;
    private CommodityInfoPresenter mCommodityInfoPresenter;
    private List<CommodityInfoEntity.DataBean.CarPointList> mList = new ArrayList<>();
    private CarCommodityAdapter mAdapter;
    private String commodityId,type;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_commodity;
    }

    @Override
    protected void initView() {
        hideBack(5);
        commodityId = getIntent().getStringExtra("commodityId");
        type = getIntent().getStringExtra("type");
        mCommodityInfoPresenter = new CommodityInfoPresenter(context);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        xRecyclerView.setPullRefreshEnabled(false);
        xRecyclerView.setLoadingMoreEnabled(false);
        mAdapter = new CarCommodityAdapter(context,mList);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        mCommodityInfoPresenter.onCreate();
        mCommodityInfoPresenter.attachView(mCommodityInfoView);
        mCommodityInfoPresenter.getSearchCommodityInfo(commodityId,type,uid,"加载中...");
    }

    private ProjectView<CommodityInfoEntity> mCommodityInfoView = new ProjectView<CommodityInfoEntity>() {
        @Override
        public void onSuccess(CommodityInfoEntity mCommodityInfoEntity) {
            if (mCommodityInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mCommodityInfoEntity.getMsg());
                return;
            }
            List<CommodityInfoEntity.DataBean.CarPointList> carPointLists = mCommodityInfoEntity.getData().getCarPointList();
            if (carPointLists != null && !carPointLists.isEmpty()){
                mList.addAll(carPointLists);
                mAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

}
