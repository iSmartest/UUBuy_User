package com.ifree.uu.uubuy.ui.activity;

import android.widget.TextView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.mvp.entity.UserInfoEntity;
import com.ifree.uu.uubuy.mvp.presenter.PrizeCodePresenter;
import com.ifree.uu.uubuy.mvp.view.ProjectView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/12/12 0012
 * Description:
 */
public class ReceivePrizeCenterActivity extends BaseActivity {
    private PrizeCodePresenter mPrizeCodePresenter;
    @BindView(R.id.tv_prize_code)
    TextView tvPrizeCode;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_prize;
    }

    @Override
    protected void initView() {
        hideBack(9);
        setTitleText("我要领奖");
        mPrizeCodePresenter = new PrizeCodePresenter(context);
    }

    @Override
    protected void loadData() {
        mPrizeCodePresenter.onCreate();
        mPrizeCodePresenter.attachView(mPrizeCodeView);
        mPrizeCodePresenter.searchPrizeCode(uid,"加载中");
    }

    private ProjectView<UserInfoEntity> mPrizeCodeView = new ProjectView<UserInfoEntity>() {
        @Override
        public void onSuccess(UserInfoEntity userInfoEntity) {
            if (userInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,userInfoEntity.getMsg());
                tvPrizeCode.setText("你未参与活动");
                return;
            }
            tvPrizeCode.setText(userInfoEntity.getData().getIndex());
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
            tvPrizeCode.setText("你未参与活动");
        }
    };
}
