package com.ifree.uu.uubuy.listener;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ifree.uu.uubuy.uitls.SPUtil;
import com.ifree.uu.uubuy.uitls.Util;

import butterknife.internal.Utils;

/**
 * Author: 小火
 * Email:1403241630@qq.com
 * Created by 2018/8/17.
 * Description:
 */
public class GaoDeLocationListener {

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private Context context;
    private OnQuestResultListener onQuestResultListener;
    //初始化client
    public GaoDeLocationListener(Context context,OnQuestResultListener onQuestResultListener){
        this.context = context;
        this.onQuestResultListener = onQuestResultListener;
        locationClient = new AMapLocationClient(context.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }
    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);//可选，设置逆地理信息的语言，默认值为默认语言（根据所在地区选择语言）
        return mOption;
    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {
                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    SPUtil.putString(context, "city", location.getCity());
                    SPUtil.putString(context, "district", location.getDistrict());
                    SPUtil.putString(context, "townAdCode", location.getAdCode());
                    SPUtil.putString(context, "cityCode", location.getCityCode());
                    SPUtil.putString(context, "latitude", location.getLatitude() + "");
                    SPUtil.putString(context, "longitude", location.getLongitude() + "");
                    onQuestResultListener.success(location.getCity());
                    Log.i("TAG", "定位成功" + "\n" +"定位类型: " + location.getLocationType() + "\n"
                    +"经    度    : " + location.getLongitude() + "\n"+"纬    度    : " + location.getLatitude() + "\n"
                    +"精    度    : " + location.getAccuracy() + "米" + "\n"+"提供者    : " + location.getProvider() + "\n"
                    +"速    度    : " + location.getSpeed() + "米/秒" + "\n"+"角    度    : " + location.getBearing() + "\n"
                    + "星    数    : " + location.getSatellites() + "\n" +"国    家    : " + location.getCountry() + "\n"
                    +"省            : " + location.getProvince() + "\n"+"市            : " + location.getCity() + "\n"
                    +"城市编码 : " + location.getCityCode() + "\n"+"区            : " + location.getDistrict() + "\n"
                            +"区域 码   : " + location.getAdCode() + "\n"+"地    址    : " + location.getAddress() + "\n"
                    +"兴趣点    : " + location.getPoiName() + "\n");
                    stopLocation();
                } else {
                    startLocation();
                    onQuestResultListener.error("北京");
                }
            } else {
                startLocation();
                onQuestResultListener.error("北京");
            }
        }
    };


    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    public void startLocation(){
        //根据控件的选择，重新设置定位参数
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void stopLocation(){
        // 停止定位
        locationClient.stopLocation();
    }

    public interface OnQuestResultListener {
        void success(String result);
        void error(String result);
    }
}
