package com.ifree.uu.uubuy.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/9/20 0020
 * Description:意见反馈
 */
public class FeedbackActivity extends CommonActivity {
    @BindView(R.id.edit_feedback_dec)
    EditText mFeedback;
    @BindView(R.id.tv_sure_feedback)
    TextView mSure;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_feedback_title;
    }

    @Override
    protected void initView() {}

    @Override
    protected void initData() {}

    @OnClick({R.id.edit_feedback_dec})
    public void onClickView(View view){
        switch (view.getId()){
            case R.id.edit_feedback_dec:
                String mContent = mFeedback.getText().toString().trim();
                if (TextUtils.isEmpty(mContent)){
                    ToastUtils.show("内容不能为空");
                    return;
                }
                submitFeedback(mContent);
                break;
        }
    }

    private void submitFeedback(String mContent) {

    }
}
