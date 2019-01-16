package com.ifree.uu.uubuy.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.app.MyApplication;
import com.ifree.uu.uubuy.common.CommonLazyFragment;
import com.ifree.uu.uubuy.ui.activity.MyBrowseRecordActivity;
import com.ifree.uu.uubuy.ui.activity.MyCollectionActivity;
import com.ifree.uu.uubuy.ui.activity.MyCustomerServiceActivity;
import com.ifree.uu.uubuy.ui.activity.MyFocusActivity;
import com.ifree.uu.uubuy.ui.activity.MyPersonalInformationActivity;
import com.ifree.uu.uubuy.ui.activity.MyPlayVIPActivity;
import com.ifree.uu.uubuy.ui.activity.MySettingActivity;
import com.ifree.uu.uubuy.ui.activity.MySignUpActivity;
import com.ifree.uu.uubuy.widget.XCollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/3 0003
 * Description: 我的页面
 */
public class MineFragment extends CommonLazyFragment implements XCollapsingToolbarLayout.OnScrimsListener {

    @BindView(R.id.my_icon_img)
    CircleImageView ivUserIcon;
    @BindView(R.id.iv_mine_setting)
    ImageView ivSetting;
    @BindView(R.id.ll_mine_focus)
    LinearLayout llFocus;
    @BindView(R.id.linear_mine_collection)
    LinearLayout llCollection;
    @BindView(R.id.ll_mine_sign_up)
    LinearLayout llSignUp;
    @BindView(R.id.ll_mine_browse_record)
    LinearLayout llBrowseRecord;
    @BindView(R.id.tv_mine_play_vip)
    ImageView tvPlayVip;
    @BindView(R.id.tv_mine_customer_service)
    ImageView tvCustomerService;
    @BindView(R.id.ll_join_vip_center)
    LinearLayout llVipCenter;
    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onScrimsStateChange(boolean shown) {

    }

    @OnClick({R.id.my_icon_img,R.id.iv_mine_setting,R.id.ll_mine_focus,R.id.linear_mine_collection,
            R.id.ll_mine_sign_up,R.id.ll_mine_browse_record,R.id.tv_mine_play_vip,R.id.ll_join_vip_center,R.id.tv_mine_customer_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_icon_img:
//                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
//                    MyApplication.openActivity(context, LoginActivity.class);
//                } else {
//
//                }
                MyApplication.openActivity(context, MyPersonalInformationActivity.class);
                break;
            case R.id.iv_mine_setting:
                MyApplication.openActivity(context, MySettingActivity.class);
                break;
            case R.id.ll_mine_focus:
                MyApplication.openActivity(context, MyFocusActivity.class);
                break;
            case R.id.linear_mine_collection:
                MyApplication.openActivity(context, MyCollectionActivity.class);
                break;
            case R.id.ll_mine_sign_up:
                MyApplication.openActivity(context, MySignUpActivity.class);
                break;
            case R.id.ll_mine_browse_record:
                MyApplication.openActivity(context, MyBrowseRecordActivity.class);
                break;
            case R.id.ll_join_vip_center:
            case R.id.tv_mine_play_vip:
//                if (TextUtils.isEmpty(SPUtil.getUid(context))) {
//                    ToastUtils.show("请先登录！");
//                } else {
//                    MyApplication.openActivity(context, MyPlayVIPActivity.class);
//                }
                MyApplication.openActivity(context, MyPlayVIPActivity.class);
                break;
            case R.id.tv_mine_customer_service:
                MyApplication.openActivity(context,MyCustomerServiceActivity.class);
                break;

        }
    }
}
