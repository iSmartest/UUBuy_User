package com.ifree.uu.uubuy.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;


/**
 * Author：小火
 * Email：1403241630@qq.com
 * Created by 2018/11/26 0026
 * Description:
 */
public class DeviceInfoUtils {

    public static String getMyUUID(Context context) {
        String uniqueId = "";
        String uuid = SPUtil.getString(context, "uuid");
        if (TextUtils.isEmpty(uuid)) {
            UUID deviceUuid = new UUID(getAndroidId(context).hashCode(), ((long) getDeviceId(context).hashCode() << 32) | getSimSerial(context).hashCode() | getLocalMac(context).hashCode()
                    | getIMEI(context).hashCode() | getIMSI(context).hashCode());
            uniqueId = deviceUuid.toString();
            if (TextUtils.isEmpty(uniqueId)) {
                uniqueId = getUUID(context);
            }
            SPUtil.putString(context, "uuid", uniqueId);
        } else {
            uniqueId = uuid;
        }
        return uniqueId;
    }


    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String DeviceId = tm.getDeviceId();
        return DeviceId;
    }

    /**
     * 得到全局唯一UUID
     */
    public static String getUUID(Context context) {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    /**
     * 得到本机Mac地址
     *
     * @return
     */

    public static String getLocalMac(Context context) {
        String macAddress = null;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());
        if (!wifiManager.isWifiEnabled()) {
            //必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            wifiManager.setWifiEnabled(false);
        }
        if (null != info) {
            macAddress = info.getMacAddress();
        }
        return macAddress;
    }

    public static String getAndroidId(Context context) {
        String ANDROID_ID = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return ANDROID_ID;
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public static final String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            @SuppressLint("MissingPermission") String imei = telephonyManager.getDeviceId();
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }


    /**
     * 获取手机IMSI
     */
    public static String getIMSI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMSI号
            @SuppressLint("MissingPermission") String imsi = telephonyManager.getSubscriberId();
            if (null == imsi) {
                imsi = "";
            }
            return imsi;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getSimSerial(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String tmSerial = "" + tm.getSimSerialNumber();
        return tmSerial;
    }


    public static String getChannelData(Context context){
        String CHANNEL_ID = "";
        if (TextUtils.isEmpty(CHANNEL_ID)){
            if (context == null){
                return null;
            }
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null){
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
                    if (applicationInfo != null){
                        if (applicationInfo.metaData != null){
                            CHANNEL_ID = applicationInfo.metaData.getString("BUGLY_APP_CHANNEL");
                        }
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return CHANNEL_ID;
    }
}
