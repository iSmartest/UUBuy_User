package com.ifree.uu.uubuy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.ifree.uu.uubuy.uitls.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 小火
 * Create time on  2017/6/1
 * My mailbox is 1403241630@qq.com
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.i("MyReceiver", "接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            String message = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String title = null;
            try {
                JSONObject obj = new JSONObject(message);
                title = obj.getString("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            System.out.println("[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.i("MyReceiver", "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            System.out.println("[MyReceiver] 用户点击打开了通知");
            String title = bundle.getString(JPushInterface.EXTRA_ALERT);
            openAppOrActivity(context, title);
            Log.i("sdfdf", "onReceive: " + title);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
            Log.i("MyReceiver", "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.i("MyReceiver", "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            System.out.println();
            Log.i("MyReceiver", "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private void openAppOrActivity(Context context, String extra) {
        //判断app进程是否处于前台 此时用户处于登陆状态userId!=0
        Log.i("sdfdf", "openAppOrActivity: " + extra);
        if (isLogin(context)) {
            Intent detailIntent = parseJpushBundle(context, extra);
            if (detailIntent == null)
                return;
            detailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(detailIntent);
        } else {
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntent == null)
                return;
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//            launchIntent.putExtra(Constant.JPUSH_EXTRA, extra);
            context.startActivity(launchIntent);
        }
    }
    public static Intent parseJpushBundle(Context context, String extra) {
        Intent detailIntent = null;
        Log.i("sdfdf", "parseJpushBundle: " + extra);
        String type = null, id = null,url = null,text = null;
        return detailIntent;
    }
    public static boolean isLogin(Context context) {
        if (!TextUtils.isEmpty(SPUtil.getString(context,"uid")))
            return true;
        else
            return false;
    }
}