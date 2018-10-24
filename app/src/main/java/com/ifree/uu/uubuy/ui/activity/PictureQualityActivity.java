package com.ifree.uu.uubuy.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import com.ifree.uu.uubuy.uitls.SPUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/24 0024
 * Description:图片质量
 */
public class PictureQualityActivity extends BaseActivity {
    @BindView(R.id.ll_model_intelligence)
    LinearLayout mModelIntelligence;
    @BindView(R.id.iv_select_intelligence)
    ImageView ivModelIntelligence;
    @BindView(R.id.ll_model_high_quality)
    LinearLayout mModelHighQuality;
    @BindView(R.id.iv_select_high_quality)
    ImageView ivModelHighQuality;
    @BindView(R.id.ll_model_common)
    LinearLayout mModelCommon;
    @BindView(R.id.iv_select_common)
    ImageView ivModelCommon;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_quality;
    }

    @Override
    protected void loadData() {
        if (SPUtil.getString(context,"pictureModel").equals("2")){
            ivModelIntelligence.setVisibility(View.GONE);
            ivModelHighQuality.setVisibility(View.VISIBLE);
            ivModelCommon.setVisibility(View.GONE);
        }else if (SPUtil.getString(context,"pictureModel").equals("3")){
            ivModelIntelligence.setVisibility(View.GONE);
            ivModelHighQuality.setVisibility(View.GONE);
            ivModelCommon.setVisibility(View.VISIBLE);
        }else {
            ivModelIntelligence.setVisibility(View.VISIBLE);
            ivModelHighQuality.setVisibility(View.GONE);
            ivModelCommon.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView() {
        hideBack(5);
        setTitleText("图片质量");
    }

    @OnClick({R.id.ll_model_intelligence,R.id.ll_model_high_quality,R.id.ll_model_common})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.ll_model_intelligence:
                ivModelIntelligence.setVisibility(View.VISIBLE);
                ivModelHighQuality.setVisibility(View.GONE);
                ivModelCommon.setVisibility(View.GONE);
                SPUtil.putString(context,"pictureModel","1");
                break;
            case R.id.ll_model_high_quality:
                ivModelIntelligence.setVisibility(View.GONE);
                ivModelHighQuality.setVisibility(View.VISIBLE);
                ivModelCommon.setVisibility(View.GONE);
                SPUtil.putString(context,"pictureModel","2");
                break;
            case R.id.ll_model_common:
                ivModelIntelligence.setVisibility(View.GONE);
                ivModelHighQuality.setVisibility(View.GONE);
                ivModelCommon.setVisibility(View.VISIBLE);
                SPUtil.putString(context,"pictureModel","3");
                break;
        }
    }

}
