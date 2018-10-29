package com.ifree.uu.uubuy.ui.activity;

import android.widget.TextView;
import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.ui.base.BaseActivity;
import java.io.InputStream;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/8 0008
 * Description:登录协议，常见问题
 */
public class ProtocolActivity extends BaseActivity {
    @BindView(R.id.tv_text)
    TextView text;
    private String title;
    private String type;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_protocal;
    }

    @Override
    protected void loadData() {
        if (type.equals("0")){
            text.setText(readStream(getResources().openRawResource(R.raw.loginknowledge)));
        }else {
            text.setText(readStream(getResources().openRawResource(R.raw.questionknowledge)));
        }
    }

    @Override
    protected void initView() {
        hideBack(5);
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        setTitleText(title);
    }

    private String readStream(InputStream is) {
        // 资源流(GBK汉字码）变为串
        String res;
        try {
            byte[] buf = new byte[is.available()];
            is.read(buf);
            res = new String(buf, "GBK");
            //  必须将GBK码制转成Unicode
            is.close();
        } catch (Exception e) {
            res = "";
        }
        return (res);
        //  把资源文本文件送到String串中
    }
}
