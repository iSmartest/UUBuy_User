package com.ifree.uu.uubuy.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.GroupEntity;
import com.ifree.uu.uubuy.service.presenter.GroupInfoPresenter;
import com.ifree.uu.uubuy.service.view.GroupInfoView;
import com.ifree.uu.uubuy.ui.adapter.PlayVIPAdapter;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.ui.fragment.ActivitiesFragment;
import com.ifree.uu.uubuy.ui.fragment.AroundFragment;
import com.ifree.uu.uubuy.ui.fragment.HomeFragment;
import com.ifree.uu.uubuy.ui.fragment.MineFragment;
import com.ifree.uu.uubuy.ui.fragment.OrderFragment;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/22.
 * Description:
 */
public class PlayVIPActivity extends BaseActivity {
    private GroupInfoPresenter mGroupInfoPresenter;
    @BindView(R.id.tv_sign_in)
    TextView mSignIn;
    @BindView(R.id.rv_sign)
    RecyclerView recyclerView;
    @BindView(R.id.tv_go_perfect_information)
    TextView mGoPerfectInformation;
    @BindView(R.id.tv_go_share)
    TextView mGoShare;
    private PlayVIPAdapter mAdapter;
    private List<GroupEntity.SignInList> mList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_vip;
    }

    @Override
    protected void loadData() {
        mGroupInfoPresenter.onCreate();
        mGroupInfoPresenter.attachView(mGroupInfoView);
        mGroupInfoPresenter.getSearchGroupInfos(SPUtil.getUid(context),"加载中...");

    }

    private GroupInfoView mGroupInfoView = new GroupInfoView() {
        @Override
        public void onSuccess(GroupEntity mGroupEntity) {
            if (mGroupEntity.equals("1")){
                ToastUtils.makeText(context,mGroupEntity.getResultCode());
                return;
            }
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };

    @Override
    protected void initView() {
        hideBack(7);
        setTitleText("玩转会员");
        mGroupInfoPresenter = new GroupInfoPresenter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
        mAdapter = new PlayVIPAdapter(context,mList);
        recyclerView.setAdapter(mAdapter);
    }


    @OnClick({R.id.tv_go_perfect_information, R.id.tv_go_share, R.id.iv_base_question,R.id.tv_sign_in})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_perfect_information:
                MyApplication.openActivity(context,MyPersonalInformationActivity.class);
                break;
            case R.id.tv_go_share:
                ToastUtils.makeText(context,"跳转三方分享");
                break;
            case R.id.iv_base_question:
                ToastUtils.makeText(context,"玩转会员规则");
                break;
            case R.id.tv_sign_in:
                ToastUtils.makeText(context,"签到成功");
                mSignIn.setBackgroundResource(R.drawable.shape_city_location_background);
                mSignIn.setText("已签到");
                break;
        }
    }

}
