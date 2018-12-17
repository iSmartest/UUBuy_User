package com.ifree.uu.uubuy.config;

/**
 * Created by 小火
 * Create time on  2017/10/25
 * My mailbox is 1403241630@qq.com
 */

public class Constant {
    public static Boolean FIRST_LOCATION = false;
    /**联网类型*/
    public static final class NetState {
        public static final int WIFI = 10030;
        public static final int Mobile = 10031;
        public static final int NOWAY = 10032;
    }
    public static final String FIRST_COME = "first_come";
    public static int badgeCount = 0;

    /**
     * Unknown network class
     */
    public static final int NETWORK_CLASS_UNKNOWN = 0;

    /**
     * wifi net work
     */
    public static final int NETWORK_WIFI = 1;

    /**
     * "2G" networks
     */
    public static final int NETWORK_CLASS_2_G = 2;

    /**
     * "3G" networks
     */
    public static final int NETWORK_CLASS_3_G = 3;

    /**
     * "4G" networks
     */
    public static final int NETWORK_CLASS_4_G = 4;

    public static final int FIRST_CLASSIFY_REQUEST = 1001;

    public static final int HOT_CITY_REQUEST = 1002;

    public static final int SELECT_PROVINCE_REQUEST = 1003;

    public static final int SELECT_CITY_REQUEST = 1004;

    public static final int SELECT_AREA_REQUEST = 1005;

    public static final int LOGIN_BINDING_PHONE = 1006;

}
