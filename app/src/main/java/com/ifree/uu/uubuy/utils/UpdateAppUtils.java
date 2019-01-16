package com.ifree.uu.uubuy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.ifree.uu.uubuy.dialog.ConfirmDialog;

/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/10/23 0023
 * Description:
 */
public class UpdateAppUtils {
    private final String TAG = "UpdateAppUtils";
    public static final int CHECK_BY_VERSION_NAME = 1001;
    public static final int CHECK_BY_VERSION_CODE = 1002;
    public static final int DOWNLOAD_BY_APP = 1003;
    public static final int DOWNLOAD_BY_BROWSER = 1004;
    private Activity activity;
    private int checkBy = CHECK_BY_VERSION_CODE;
    private int downloadBy = DOWNLOAD_BY_APP;
    private int serverVersionCode = 0;
    private String apkPath="";
    private String serverVersionName="";
    private boolean isForce = false; //是否强制更新
    private int localVersionCode = 0;
    private String localVersionName="";
    public static boolean needFitAndroidN = true; //提供给 整个工程不需要适配到7.0的项目 置为false
    public static boolean showNotification = true;
    private String updateInfo = "";
    private String updateLog = "1.修复已知Bug;\n2.提升用户体验。";
    public UpdateAppUtils needFitAndroidN(boolean needFitAndroidN) {
        UpdateAppUtils.needFitAndroidN = needFitAndroidN;
        return this;
    }

    private UpdateAppUtils(Activity activity) {
        this.activity = activity;
        getAPPLocalVersion(activity);
    }

    public static UpdateAppUtils from(Activity activity){
        return new UpdateAppUtils(activity);
    }

    public UpdateAppUtils checkBy(int checkBy){
        this.checkBy = checkBy;
        return this;
    }

    public UpdateAppUtils apkPath(String apkPath){
        this.apkPath = apkPath;
        return this;
    }

    public UpdateAppUtils downloadBy(int downloadBy){
        this.downloadBy = downloadBy;
        return this;
    }

    public UpdateAppUtils showNotification(boolean showNotification){
        this.showNotification = showNotification;
        return this;
    }

    public UpdateAppUtils updateInfo(String updateInfo){
        this.updateInfo = updateInfo;
        return this;
    }

    public UpdateAppUtils serverVersionCode(int serverVersionCode){
        this.serverVersionCode = serverVersionCode;
        return this;
    }

    public UpdateAppUtils serverVersionName(String serverVersionName){
        this.serverVersionName = serverVersionName;
        return this;
    }

    public UpdateAppUtils isForce(boolean  isForce){
        this.isForce = isForce;
        return this;
    }

    //获取apk的版本号 currentVersionCode
    private  void getAPPLocalVersion(Context ctx) {
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            localVersionName = info.versionName; // 版本名
            localVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        switch (checkBy){
            case CHECK_BY_VERSION_CODE:
                if (serverVersionCode >localVersionCode){
                    toUpdate();
                }else {
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                }
                break;
            case CHECK_BY_VERSION_NAME:
                if (!serverVersionName.equals(localVersionName)){
                    toUpdate();
                }else {
                    Log.i(TAG,"当前版本是最新版本"+serverVersionCode+"/"+serverVersionName);
                }
                break;
        }
    }

    private void toUpdate() {
        realUpdate();
    }

    private void realUpdate() {
        ConfirmDialog dialog = new ConfirmDialog(activity, new ConfirmDialog.Callback() {
            @Override
            public void callback(int position) {
                switch (position){
                    case 0:
                        if (isForce)System.exit(0);
                        break;
                    case 1:
                        if (downloadBy == DOWNLOAD_BY_APP) {
                            if (isWifiConnected(activity)){
                                DownloadAppUtils.download(activity, apkPath, serverVersionName);
                            }else {
                                new ConfirmDialog(activity, new ConfirmDialog.Callback() {
                                    @Override
                                    public void callback(int position) {
                                        if (position==1){
                                            DownloadAppUtils.download(activity, apkPath, serverVersionName);
                                        }else {
                                            if (isForce)activity.finish();
                                        }
                                    }
                                }).setContent("提示","目前手机不是WiFi状态\n确认是否继续下载更新？").show();
                            }

                        }else if (downloadBy == DOWNLOAD_BY_BROWSER){
                            DownloadAppUtils.downloadForWebView(activity,apkPath);
                        }
                        break;
                }
            }
        });

        String content = String.format("是否升级到%s版本？", serverVersionName);
        if (!TextUtils.isEmpty(updateInfo)){
            updateLog = updateInfo;
        }
        dialog .setContent(content,updateLog);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 检测wifi是否连接
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }
}