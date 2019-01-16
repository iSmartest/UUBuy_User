package com.ifree.uu.uubuy.ui.activity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.ifree.uu.uubuy.R;
import com.ifree.uu.uubuy.common.CommonActivity;
import com.ifree.uu.uubuy.dialog.SeePictureDialog;
import com.ifree.uu.uubuy.listener.RecyclerItemTouchListener;
import com.ifree.uu.uubuy.ui.adapter.ProductInfoAdapter;
import com.ifree.uu.uubuy.utils.GlideImageLoader;
import com.ifree.uu.uubuy.widget.NoScrollWebView;

import butterknife.BindView;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2019/1/9 0009
 * Description:
 */
public class ProductActivity extends CommonActivity {
    @BindView(R.id.rc_picture)
    RecyclerView mRecyclerView;
    @BindView(R.id.webViewNet)
    NoScrollWebView mWebView;
    @BindView(R.id.iv_commodity_picture)
    ImageView mProductPicture;
    private SeePictureDialog seePictureDialog;
    private ProductInfoAdapter mAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected int getTitleBarId() {
        return R.id.tb_product_info_title;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context, OrientationHelper.HORIZONTAL,false));
        mAdapter = new ProductInfoAdapter(context);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
//                position = vh.getAdapterPosition();
////                if (position < 0 | position >= mList.size()){
////                    return;
////                }
//                mAdapter.setDefSelect(position);
//                GlideImageLoader.imageLoader(context,mList.get(position),mCommodityPicture);
            }
        });
        mProductPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                seePictureDialog = new SeePictureDialog(context,mList,position);
//                seePictureDialog.show();
            }
        });

        // 不显示滚动条
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        WebSettings settings = mWebView.getSettings();
        // 允许文件访问
        settings.setAllowFileAccess(true);
        // 支持javaScript
        settings.setJavaScriptEnabled(true);
        // 允许网页定位
        settings.setGeolocationEnabled(true);
        // 允许保存密码
        settings.setSavePassword(true);

        // 支持播放gif动画
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 解决Android 5.0上Webview默认不允许加载Http与Https混合内容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //两者都可以
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 加快HTML网页加载完成的速度，等页面finish再加载图片
        if(Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
    }

    @Override
    protected void initData() {
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.loadUrl("https://www.baidu.com");
    }

    private class MyWebViewClient extends WebViewClient {

        // 网页加载错误时回调，这个方法会在onPageFinished之前调用
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, final String failingUrl) {

        }

        // 开始加载网页
        @Override
        public void onPageStarted(final WebView view, final String url, Bitmap favicon) {
        }

        // 完成加载网页
        @Override
        public void onPageFinished(WebView view, String url) {
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //super.onReceivedSslError(view, handler, error);注意一定要去除这行代码，否则设置无效。
            // handler.cancel();// Android默认的处理方式
            handler.proceed();// 接受所有网站的证书
            // handleMessage(Message msg);// 进行其他处理
        }

        // 跳转到其他链接
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, final String url) {

            String scheme = Uri.parse(url).getScheme();
            if (scheme != null) {
                scheme = scheme.toLowerCase();
            }
            if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                mWebView.loadUrl(url);
            }
            // 已经处理该链接请求
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {

        // 收到网页标题
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (title != null) {
                setTitle(title);
            }
        }

        // 收到加载进度变化
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }
    }
}
