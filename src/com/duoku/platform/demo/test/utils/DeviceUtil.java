package com.duoku.platform.demo.test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import org.json.JSONObject;

/**
 * Created by taoliang on 15/3/26.
 */
public class DeviceUtil {

    private static boolean mUseWap;
    private static String mProxy;
    private static String mPort;

    public static String getAssetStatus(Context context,String tag,int res){

        String connect_type = getConnectionString(context);
        String os = Build.VERSION.RELEASE;
        String ua = Build.MODEL;

        String result = "tag=" + tag + "&connect_type=" + connect_type + "&ua=" + ua.replaceAll(" ","") + "&os=" + os + "&result=" + res + "&timestamp=" + System.currentTimeMillis();

        return result;

    }

    /*
 * Get Current connection type
 *
 * @Return ConnectType have three kind of type
 */
    public static void checkConnectType(Context context) {

        final ConnectivityManager conn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conn != null) {

            NetworkInfo info = conn.getActiveNetworkInfo();

            if (info != null) {
            
                String connStr = info.getTypeName();

                if (connStr.equalsIgnoreCase("WIFI")) {

                    // set member param
                    mUseWap = false;

                } else if (connStr.equalsIgnoreCase("MOBILE")) {

                    String apn = info.getExtraInfo();

                    if (apn.indexOf("wap") > -1) {

                        if (apn.equals("cmwap") || apn.equals("uniwap")
                                || apn.equals("3gwap")) {

                            mUseWap = true;
                            mProxy = "10.0.0.172";
                            mPort = "80";

                        } else if (apn.equals("ctwap")) {

                            mUseWap = true;
                            mProxy = "10.0.0.200";
                            mPort = "80";

                        } else {
                            // not use wap
                            mUseWap = false;
                        }

                    } else {
                        // not use wap
                        mUseWap = false;
                    }
                }
            }
        }
    }

    public static String getConnectionString(Context context) {

        if (isWifi(context)) {
            return "wifi";
        }

        try {

            checkConnectType(context);
        } catch (Exception e) {
            return "2G";
        }

        if (isWapNetwork()) {
            return "3G";
        }

        return "wifi";
    }


    /**
     * make true current connect service is wifi
     *
     * @param context
     *            Application context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isWapNetwork() {
        return mUseWap;
    }
}
