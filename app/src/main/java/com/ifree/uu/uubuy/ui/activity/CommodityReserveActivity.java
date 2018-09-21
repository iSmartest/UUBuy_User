package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.service.entity.UserInfoEntity;
import com.ifree.uu.uubuy.service.presenter.SubmitReservePresenter;
import com.ifree.uu.uubuy.service.view.SubmitReserveView;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.GlideImageLoader;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/28.
 * Description: 商品预订
 */
public class CommodityReserveActivity extends BaseActivity {
    private SubmitReservePresenter mSubmitReservePresenter;
    @BindView(R.id.tv_user_name)
    TextView userName;
    @BindView(R.id.tv_user_phone)
    TextView userPhone;
    @BindView(R.id.tv_user_address)
    TextView userAddress;
    @BindView(R.id.iv_reserve_commodity_picture)
    ImageView mCommodityPicture;
    @BindView(R.id.tv_reserve_commodity_name)
    TextView mCommodityName;
    @BindView(R.id.tv_reserve_commodity_dec)
    TextView mCommodityDes;
    @BindView(R.id.tv_reserve_commodity_price)
    TextView mCommodityPrice;
    @BindView(R.id.tv_reserve_commodity_address)
    TextView mCommodityAddress;
    @BindView(R.id.iv_reserve_commodity_sub)
    LinearLayout mSub;
    @BindView(R.id.tv_reserve_commodity_num)
    TextView mNum;
    @BindView(R.id.iv_reserve_commodity_add)
    LinearLayout mAdd;
    @BindView(R.id.tv_commodity_reserve)
    TextView mReserve;
    private String commodityId,commodityIcon,commodityBrandName,commodityName,commodityPrice,
            commodityAddress,type,shopId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_reserve;
    }


    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("商品预定");
        mSubmitReservePresenter = new SubmitReservePresenter(context);
        commodityId = getIntent().getStringExtra("commodityId");
        commodityIcon = getIntent().getStringExtra("commodityIcon");
        commodityBrandName = getIntent().getStringExtra("commodityBrandName");
        commodityName = getIntent().getStringExtra("commodityName");
        commodityPrice = getIntent().getStringExtra("commodityPrice");
        commodityAddress = getIntent().getStringExtra("commodityAddress");
        type = getIntent().getStringExtra("commodityType");
        shopId = getIntent().getStringExtra("shopId");
    }

    @Override
    protected void loadData() {
        GlideImageLoader.imageLoader(context,commodityIcon,mCommodityPicture);
        mCommodityName.setText(commodityBrandName);
        mCommodityDes.setText(commodityName);
        mCommodityPrice.setText("￥：" + commodityPrice);
        mCommodityAddress.setText(commodityAddress);
        userAddress.setText(address);
    }


    @OnClick({R.id.iv_reserve_commodity_sub,R.id.iv_reserve_commodity_add,R.id.tv_commodity_reserve})
    public void onViewClicked(View view) {
        int temp = Integer.parseInt(mNum.getText().toString().trim());
        switch (view.getId()){
            case R.id.iv_reserve_commodity_sub:
                if (temp <= 1) {
                    mNum.setText("1");
                    ToastUtils.makeText(context,"预订数量不能小于1");
                } else {
                    temp--;
                    mNum.setText("" + temp);
                }
                break;
            case R.id.iv_reserve_commodity_add:
                temp++;
                mNum.setText("" + temp);
                break;
            case R.id.tv_commodity_reserve:
                if (TextUtils.isEmpty(uid)){
                    ToastUtils.makeText(context,"用户未登录，请登录");
                    return;
                }
                if (SPUtil.getString(context,"isPhone").equals("0")){
                    ToastUtils.makeText(context,"请绑定手机号");
                    MyApplication.openActivity(context,BindingPhoneActivity.class);
                    return;
                }
                submitReserve();
                break;
        }
    }

    private void submitReserve() {
        String count = mNum.getText().toString().trim();
        mSubmitReservePresenter.onCreate();
        mSubmitReservePresenter.attachView(mSubmitReserveView);
        mSubmitReservePresenter.getSubmitReserveInfo(commodityId,type,count,shopId,"1","提交中...");
    }

    private SubmitReserveView mSubmitReserveView = new SubmitReserveView() {
        @Override
        public void onSuccess(UserInfoEntity mUserInfoEntity) {
            if (mUserInfoEntity.getResultCode().equals("1")){
                ToastUtils.makeText(context,mUserInfoEntity.getMsg());
                return;
            }
            ToastUtils.makeText(context,"预订成功");
            finish();
        }

        @Override
        public void onError(String result) {
            ToastUtils.makeText(context,result);
        }
    };
}
